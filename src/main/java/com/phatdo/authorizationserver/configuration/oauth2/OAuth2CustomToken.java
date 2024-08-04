package com.phatdo.authorizationserver.configuration.oauth2;

import com.phatdo.authorizationserver.services.OidcUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.phatdo.authorizationserver.models.users.User;
import com.phatdo.authorizationserver.authentication.CustomUserDetails;
import com.phatdo.authorizationserver.services.CustomUserDetailsService;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class OAuth2CustomToken {
    private final CustomUserDetailsService userService;

    @Autowired
    public OAuth2CustomToken(CustomUserDetailsService customUserDetailsService) {
        this.userService = customUserDetailsService;
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(OidcUserInfoService userInfoService) {
        return context ->
            context.getClaims().claims((claims) -> {
                if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
                    String username = context.getPrincipal().getName();
                    User user = ((CustomUserDetails) userService
                            .loadUserByUsername(username))
                            .getUser();
                    OidcUserInfo userInfo = userInfoService.loadUser(user);
                    claims.putAll(userInfo.getClaims());
                }

                Set<String> authorities = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
                            .stream()
                            .map(c -> c.replaceFirst("^ROLE_", ""))
                            .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                claims.put("roles", authorities);
            });
    }
}
