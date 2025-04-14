package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
