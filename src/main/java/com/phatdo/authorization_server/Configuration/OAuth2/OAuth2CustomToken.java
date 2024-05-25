package com.phatdo.authorization_server.Configuration.OAuth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.phatdo.authorization_server.Entity.UserDetails.CustomUserDetails;

@Configuration
public class OAuth2CustomToken {
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            context.getClaims().claims((claims) -> {
                CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
                String fullname = userDetails.getUser().getFullName();
                claims.put("fullName", fullname);
            });
        };
    }
}
