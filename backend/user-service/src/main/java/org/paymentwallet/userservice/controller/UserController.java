package org.paymentwallet.userservice.controller;

import org.paymentwallet.userservice.dto.UserRequestDto;
import org.paymentwallet.userservice.dto.UserResponseDto;
import org.paymentwallet.userservice.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto  userRequestDto){
        return new ResponseEntity<>(userService.registerUser(userRequestDto) , HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserId(@PathVariable UUID userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto , @PathVariable UUID userId){
        return new ResponseEntity<>(userService.updateUser(userRequestDto , userId) , HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId){
        return new ResponseEntity<>(userService.deleteUser(userId) , HttpStatus.OK);
    }

}
