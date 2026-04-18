package org.acs.idp.databaseservice.model.dto;

public record UserDto(String id,
                      String email,
                      String passwordHash) {}

