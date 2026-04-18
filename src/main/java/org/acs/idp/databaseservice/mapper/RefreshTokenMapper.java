package org.acs.idp.databaseservice.mapper;

import org.acs.idp.databaseservice.model.dto.RefreshTokenDto;
import org.acs.idp.databaseservice.model.entity.RefreshTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapper {
    public RefreshTokenDto entityToDto(RefreshTokenEntity refreshTokenEntity) {
        return new RefreshTokenDto(refreshTokenEntity.getToken(),
                refreshTokenEntity.getEmail(),
                refreshTokenEntity.getExpiresAt());
    }

    public RefreshTokenEntity dtoToEntity(RefreshTokenDto refreshTokenDto) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setToken(refreshTokenDto.token());
        refreshTokenEntity.setEmail(refreshTokenDto.email());
        refreshTokenEntity.setExpiresAt(refreshTokenDto.expiresAt());

        return refreshTokenEntity;
    }
}
