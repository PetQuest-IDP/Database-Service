package org.acs.idp.databaseservice.service;

import jakarta.transaction.Transactional;
import org.acs.idp.databaseservice.mapper.RefreshTokenMapper;
import org.acs.idp.databaseservice.model.dto.RefreshTokenDto;
import org.acs.idp.databaseservice.model.entity.RefreshTokenEntity;
import org.acs.idp.databaseservice.model.request.SaveRefreshTokenRequest;
import org.acs.idp.databaseservice.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, RefreshTokenMapper refreshTokenMapper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    public void save(SaveRefreshTokenRequest request) {
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setToken(request.token());
        token.setEmail(request.email());
        token.setExpiresAt(request.expiresAt());

        refreshTokenRepository.save(token);
    }

    public RefreshTokenDto findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .map(refreshTokenMapper::entityToDto)
                .orElse(null);
    }

    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
