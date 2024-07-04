package com.phatdo.authorizationserver.configuration.oauth2;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class OAuth2AuthorizationConfig {
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public OAuth2AuthorizationConfig(PasswordEncoder passwordEncoder) {
                this.passwordEncoder = passwordEncoder;
        }
        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
                http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                                .oidc(Customizer.withDefaults());
                http
                                .exceptionHandling((e) -> e.authenticationEntryPoint(
                                                new LoginUrlAuthenticationEntryPoint("/login")));
                return http.build();
        }

        @Bean
        public RegisteredClientRepository registeredClientRepository() {
                RegisteredClient registeredClient = RegisteredClient
                                .withId(UUID.randomUUID().toString())
                                .clientId("client")
                                .clientSecret(passwordEncoder.encode("secret"))
                                .clientAuthenticationMethod(
                                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantType(
                                                AuthorizationGrantType.AUTHORIZATION_CODE)
                                .authorizationGrantType(
                                                AuthorizationGrantType.REFRESH_TOKEN)
                                .redirectUri("http://localhost:5173/authorized")
                                .scope(OidcScopes.OPENID)
                                .tokenSettings(TokenSettings.builder()
                                                .accessTokenTimeToLive(Duration.ofMinutes(15))
                                                .build())
                                .clientSettings(ClientSettings.builder()
                                                .requireProofKey(false)
                                                .build())
                                .build();
                return new InMemoryRegisteredClientRepository(registeredClient);
        }

        @Bean
        public AuthorizationServerSettings authorizationServerSettings() {
                return AuthorizationServerSettings.builder().build();
        }
}
