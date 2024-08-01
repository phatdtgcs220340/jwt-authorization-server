package com.phatdo.authorizationserver.models.token;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("refresh_token")
public class RefreshToken {
    @Id
    private String id;
    private final String token;
}
