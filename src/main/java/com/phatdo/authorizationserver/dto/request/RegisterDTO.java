package com.phatdo.authorizationserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "The full name mustn't be blank")
        String fullName,
        @NotBlank(message = "The username mustn't be blank")
        String username,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) {
}
