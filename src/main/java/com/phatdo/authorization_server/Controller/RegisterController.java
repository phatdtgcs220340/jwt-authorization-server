package com.phatdo.authorization_server.Controller;

import com.phatdo.authorization_server.Entity.UserDetails.CustomUserDetailsService;
import com.phatdo.authorization_server.dto.RegisterDTO;
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
    public ResponseEntity<?> register(@RequestBody RegisterDTO form) {
        try {
            userService.saveUser(form.fullName(),
                    form.username(),
                    form.password());
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
