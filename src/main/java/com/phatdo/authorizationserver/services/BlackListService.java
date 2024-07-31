package com.phatdo.authorizationserver.services;

import com.phatdo.authorizationserver.models.token.RefreshToken;
import com.phatdo.authorizationserver.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {
    private final RefreshTokenRepository refreshTokenRepository;

    public BlackListService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public boolean existToken(String token) {
        return refreshTokenRepository.existsByToken(token);
    }

    public void addToken(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }
}
