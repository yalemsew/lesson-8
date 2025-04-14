package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(String username, UserRequestDto userRequestDto);
    void deleteUser(String username);
    List<UserResponseDto> findAllUsers();
    UserResponseDto findUserByUsername(String username);
}
