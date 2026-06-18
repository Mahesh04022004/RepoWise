package com.mahesh.repowise.dto;

import com.mahesh.repowise.enums.RepositoryStatus;

import java.time.LocalDateTime;

public class RepositoryResponse {

    private Long id;

    private String name;

    private String githubUrl;

    private RepositoryStatus status;

    private LocalDateTime createdAt;

    // getters and setters

    public RepositoryResponse(Long id, String name, String githubUrl, RepositoryStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.githubUrl = githubUrl;
        this.status = status;
        this.createdAt = createdAt;
    }

    public RepositoryResponse() {
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
}
