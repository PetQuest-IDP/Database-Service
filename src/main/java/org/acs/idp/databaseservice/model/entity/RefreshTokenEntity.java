package org.acs.idp.databaseservice.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private long expiresAt;

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public long getExpiresAt() { return expiresAt; }
    public void setToken(String token) { this.token = token; }
    public void setEmail(String email) { this.email = email; }
    public void setExpiresAt(long expiresAt) { this.expiresAt = expiresAt; }
}