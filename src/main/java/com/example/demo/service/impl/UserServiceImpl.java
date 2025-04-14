package com.example.demo.service.impl;


import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.findByUsername(userRequestDto.username()).isPresent()) {
            return null;
        }
        User user = new User(userRequestDto.firstName(), userRequestDto.lastName(), userRequestDto.username(), userRequestDto.password());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getUsername());
    }

    @Override
    public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(userRequestDto.password());
            user.setFirstName(userRequestDto.firstName());
            user.setLastName(userRequestDto.lastName());
            User savedUser = userRepository.save(user);
            return new UserResponseDto(savedUser.getUsername());
        }
        throw new UserNotFoundException(username+ " not found");
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            log.atWarn().log("User not found: {} for deletion.", username);
        }
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            userResponseDtos.add(new UserResponseDto(user.getUsername()));
        }
        return userResponseDtos;
    }

    @Override
    public UserResponseDto findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserResponseDto(user.getUsername());
        }
        throw new UserNotFoundException(username+ " not found");
    }
}
