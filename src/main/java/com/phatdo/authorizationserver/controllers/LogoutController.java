package com.phatdo.authorizationserver.controllers;

import com.phatdo.authorizationserver.dto.request.LogoutDTO;
import com.phatdo.authorizationserver.services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logout")
public class LogoutController {
    private final BlackListService blackListService;

    @Autowired
    public LogoutController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String logout(@RequestBody LogoutDTO dto) {
        blackListService.addToken(dto.refreshToken());
        return "Logged out successfully";
    }
}
