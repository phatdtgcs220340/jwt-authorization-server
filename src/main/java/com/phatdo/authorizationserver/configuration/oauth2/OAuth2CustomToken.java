package com.phatdo.authorizationserver.configuration.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.phatdo.authorizationserver.models.users.User;
import com.phatdo.authorizationserver.authentication.CustomUserDetails;
import com.phatdo.authorizationserver.authentication.CustomUserDetailsService;

@Configuration
public class OAuth2CustomToken {
    private final CustomUserDetailsService userService;

    @Autowired
    public OAuth2CustomToken(CustomUserDetailsService customUserDetailsService) {
        this.userService = customUserDetailsService;
    }
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            context.getClaims().claims((claims) -> {
                String username = context.getPrincipal().getName();
                User user = ((CustomUserDetails) userService
                        .loadUserByUsername(username))
                        .getUser();
                String fullName = user.getFullName();
                claims.put("fullName", fullName);
            });
        };
    }
}
