package org.paymentwallet.userservice.service;

import org.paymentwallet.userservice.dto.UserRequestDto;
import org.paymentwallet.userservice.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(UUID id);
    List<UserResponseDto> getAllUser();
    UserResponseDto updateUser(UserRequestDto userRequestDto , UUID id);
    String deleteUser(UUID id);
}
