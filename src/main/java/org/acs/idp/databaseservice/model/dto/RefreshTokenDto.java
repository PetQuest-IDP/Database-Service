package org.acs.idp.databaseservice.model.dto;

public record RefreshTokenDto(String token,
                              String email,
                              long expiresAt) {}

