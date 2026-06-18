package com.mahesh.repowise.entity;

import com.mahesh.repowise.enums.RepositoryStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "repositories")
public class RepositoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String githubUrl;

    @Enumerated(EnumType.STRING)
    private RepositoryStatus status;

    private LocalDateTime createdAt;

    private String localPath;

    public RepositoryEntity(Long id, String name, String githubUrl, RepositoryStatus status, LocalDateTime createdAt, String localPath) {
        this.id = id;
        this.name = name;
        this.githubUrl = githubUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.localPath = localPath;
    }

    public RepositoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public RepositoryStatus getStatus() {
        return status;
    }

    public void setStatus(RepositoryStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
