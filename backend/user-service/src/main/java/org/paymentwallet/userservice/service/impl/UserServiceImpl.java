package org.paymentwallet.userservice.service.impl;

import jakarta.transaction.Transactional;
import org.paymentwallet.userservice.Exception.EmailAlreadyExistException;
import org.paymentwallet.userservice.Exception.UserNotFoundException;
import org.paymentwallet.userservice.dto.UserRequestDto;
import org.paymentwallet.userservice.dto.UserResponseDto;
import org.paymentwallet.userservice.enums.KYCSTATUS;
import org.paymentwallet.userservice.model.User;
import org.paymentwallet.userservice.repository.UserRepository;
import org.paymentwallet.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        Optional<User> existUser = userRepository.findByEmail(userRequestDto.getEmail());

        if (existUser.isPresent()) {
            throw new EmailAlreadyExistException("This email already Register");
        }

        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .role(userRequestDto.getRole())
                .kycstatus(KYCSTATUS.PENDING)
                .build();

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(UUID id) {
        Optional<User> existUser = userRepository.findUserById(id);
        if (existUser.isPresent()) {
            User user = existUser.get();
            return mapToDto(user);
        }

        throw new UserNotFoundException("User not Found");
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(UserRequestDto userRequestDto, UUID id) {
        User existingUser = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // Check for email conflict (excluding current user)
        if (!existingUser.getEmail().equals(userRequestDto.getEmail()) &&
                userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("This email is already registered");
        }

        // Update fields on existing entity (preserve ID and auditing fields)
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setPhoneNumber(userRequestDto.getPhoneNumber());
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        existingUser.setRole(userRequestDto.getRole());
        // Do NOT change kycstatus here unless explicitly requested; auditing will update updatedAt

        User savedUser = userRepository.save(existingUser);
        return mapToDto(savedUser);
    }

    @Override
    @Transactional
    public String deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .kycstatus(user.getKycstatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
