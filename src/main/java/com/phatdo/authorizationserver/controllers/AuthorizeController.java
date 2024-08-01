package com.phatdo.authorizationserver.controllers;

import com.phatdo.authorizationserver.dto.response.LoginSucceed;
import com.phatdo.authorizationserver.dto.response.TypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/authorization")
public class AuthorizeController {

    @GetMapping
    public ResponseEntity<TypeDTO> authorize(Authentication authentication) {
        String message = String.format("Successfully authorized in with username %s, you are redirecting...", authentication.getName());
        return ResponseEntity.ok(new LoginSucceed(message));
    }
}
