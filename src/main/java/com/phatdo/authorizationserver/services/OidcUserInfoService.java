package com.phatdo.authorizationserver.services;

import com.phatdo.authorizationserver.models.users.User;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OidcUserInfoService {
    public OidcUserInfo loadUser(User user) {
        return new OidcUserInfo(UserInfoRepository.createUser(user));
    }

    static class UserInfoRepository {
        public static Map<String, Object> createUser(User user) {
            return OidcUserInfo.builder()
                    .email(user.getUsername())
                    .name(user.getFullName())
                    .picture(user.getAvatarUrl())
                    .build()
                    .getClaims();
        }
    }
}
