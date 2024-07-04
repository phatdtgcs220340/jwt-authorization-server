package com.phatdo.authorizationserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    @Value("${oauth2-client.client-id}")
    private String clientId;
    @Value("${oauth2-client.redirect-uri}")
    private String redirectUri;

    @GetMapping
    public String authorize(Authentication authentication) {
        log.info("Authentication : {}", authentication.getName());
        String authorizationUri = String.format("?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
                clientId, redirectUri, "openid", "code");
        return "redirect:/oauth2/authorize" + authorizationUri;
    }
}
