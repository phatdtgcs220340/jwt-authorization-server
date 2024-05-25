package com.phatdo.authorization_server.Configuration.OAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.phatdo.authorization_server.Entity.User.User;
import com.phatdo.authorization_server.Entity.UserDetails.CustomUserDetails;
import com.phatdo.authorization_server.Entity.UserDetails.CustomUserDetailsService;

@Configuration
public class OAuth2CustomToken {
    @Autowired
    private CustomUserDetailsService userService;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            context.getClaims().claims((claims) -> {
                String username = context.getPrincipal().getName();
                User user = ((CustomUserDetails) userService
                        .loadUserByUsername(username))
                        .getUser();
                String fullname = user.getFullName();
                claims.put("fullName", fullname);
            });
        };
    }
}
