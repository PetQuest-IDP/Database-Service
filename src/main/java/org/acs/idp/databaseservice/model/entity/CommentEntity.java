package org.acs.idp.databaseservice.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID questId;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public UUID getQuestId() { return questId; }
    public String getAuthorEmail() { return authorEmail; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }

    public void setQuestId(UUID questId) { this.questId = questId; }
    public void setAuthorEmail(String authorEmail) { this.authorEmail = authorEmail; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
