package com.phatdo.authorizationserver.controllers;

import com.phatdo.authorizationserver.authentication.CustomUserDetailsService;
import com.phatdo.authorizationserver.exception.CustomException;
import com.phatdo.authorizationserver.dto.request.RegisterDTO;
import com.phatdo.authorizationserver.dto.response.TypeDTO;
import com.phatdo.authorizationserver.mappers.ErrorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final CustomUserDetailsService userService;

    @Autowired
    public RegisterController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TypeDTO> register(@RequestBody @Valid RegisterDTO form) {
        try {
            userService.saveUser(form.fullName(),
                    form.username(),
                    form.password());
            return ResponseEntity.ok().build();
        }
        catch (CustomException e) {
            return new ResponseEntity<>(ErrorMapper.toDto(e), e.getStatus());
        }
    }
}
