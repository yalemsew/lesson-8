package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public String users(Model model) {
        //fetch all users
        List<UserResponseDto> userResponseDtos = userService.findAllUsers();
        //add it to model
        model.addAttribute("userResponseDtos", userResponseDtos);
        return "users";
    }
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute(
                "userRequestDto", new UserRequestDto(null, null, null, null)
        );

        return "signup";
    }
    @PostMapping
    public String createUser(@Valid @ModelAttribute UserRequestDto userRequestDto, RedirectAttributes redirectAttributes) {
        userService.createUser(userRequestDto);
        redirectAttributes.addFlashAttribute("success",userRequestDto.username()+ " has created successfully");
        return "redirect:/api/v1/users";
    }
    @GetMapping("/edit-user/{username}")
    public String editUser(@PathVariable String username, Model model){
        UserRequestDto userResponseDto = new UserRequestDto(null, null, username, null);
        //add userRequestDTO as an attribute to Model object
        model.addAttribute("userRequestDto", userResponseDto);
        return "editUser";
    }

}
