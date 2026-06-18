package com.mahesh.repowise.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateRepositoryRequest {
    @NotBlank
    private String githubUrl;

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
