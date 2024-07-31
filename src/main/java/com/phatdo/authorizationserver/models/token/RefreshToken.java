package com.phatdo.authorizationserver.models.token;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("refresh_token")
public class RefreshToken {
    @Id String id;
    String token;
}
