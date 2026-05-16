package org.acs.idp.databaseservice.model.entity;

import jakarta.persistence.*;
import org.acs.idp.databaseservice.model.entity.enums.QuestStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quests")
public class QuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String ownerEmail;

    @Column
    private String helperEmail;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TaskEntity> tasks = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (status == null) status = QuestStatus.OPEN;
    }

    public UUID getId() {
        return id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getHelperEmail() {
        return helperEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setHelperEmail(String helperEmail) {
        this.helperEmail = helperEmail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
