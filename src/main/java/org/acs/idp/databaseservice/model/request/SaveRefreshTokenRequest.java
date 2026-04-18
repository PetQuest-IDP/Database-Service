package org.acs.idp.databaseservice.model.request;

public record SaveRefreshTokenRequest(String email,
                                      String token,
                                      long expiresAt) {}

