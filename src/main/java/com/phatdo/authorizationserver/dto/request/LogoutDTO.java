package com.phatdo.authorizationserver.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutDTO(
        @NotBlank
        String refreshToken
) {
}
