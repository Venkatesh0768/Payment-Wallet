package org.paymentwallet.userservice.service.impl;

import org.paymentwallet.userservice.Exception.EmailAlreadyExistException;
import org.paymentwallet.userservice.dto.UserRequestDto;
import org.paymentwallet.userservice.dto.UserResponseDto;
import org.paymentwallet.userservice.enums.KYCSTATUS;
import org.paymentwallet.userservice.model.User;
import org.paymentwallet.userservice.repository.UserRepository;
import org.paymentwallet.userservice.service.UserService;
import org.springframework.stereotype.Service;

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

//        User user = User.builder()
//                .sett
//                .setEmail(userRequestDto.getEmail())
//                .setPassword(userRequestDto.getPassword())
//                .setFirstName(userRequestDto.getFirstName())
//                .setLastName(userRequestDto.getLastName())
//                .setRole(userRequestDto.getRole())
//                .setKycstatus(KYCSTATUS.PENDING)
//                .build();

        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .role(userRequestDto.getRole())
                .kycstatus(KYCSTATUS.PENDING)
                .build();

        User savedUser = userRepository.save(user);

    }

    @Override
    public UserResponseDto getUserById(UUID id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return List.of();
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, UUID id) {
        return null;
    }

    @Override
    public String deleteUser(UUID id) {
        return "";
    }
}
