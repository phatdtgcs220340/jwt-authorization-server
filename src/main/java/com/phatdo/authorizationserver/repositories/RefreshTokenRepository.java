package com.phatdo.authorizationserver.repositories;

import com.phatdo.authorizationserver.models.token.RefreshToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RefreshTokenRepository extends KeyValueRepository<RefreshToken, String> {
    boolean existsByToken(String refreshToken);
}
