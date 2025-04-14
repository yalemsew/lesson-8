package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> createUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto);
    void deleteUser(String username);
    List<UserResponseDto> findAllUsers();
    Optional<UserResponseDto> findUserByUsername(String username);
}
