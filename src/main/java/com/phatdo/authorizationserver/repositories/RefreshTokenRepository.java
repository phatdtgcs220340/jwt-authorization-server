package com.phatdo.authorizationserver.repositories;

import com.phatdo.authorizationserver.models.token.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
