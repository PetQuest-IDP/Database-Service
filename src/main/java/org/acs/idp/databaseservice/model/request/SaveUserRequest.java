package org.acs.idp.databaseservice.model.request;

public record SaveUserRequest(String email,
                              String passwordHash) {}

