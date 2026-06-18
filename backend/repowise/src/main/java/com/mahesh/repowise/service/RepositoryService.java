package com.mahesh.repowise.service;

import com.mahesh.repowise.dto.CreateRepositoryRequest;
import com.mahesh.repowise.dto.RepositoryResponse;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.enums.RepositoryStatus;
import com.mahesh.repowise.repository.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;
    private final RepositoryProcessor repositoryProcessor;

    // Constructor
    public RepositoryService(
            RepositoryRepository repositoryRepository, RepositoryProcessor repositoryProcessor) {

        this.repositoryRepository = repositoryRepository;
        this.repositoryProcessor = repositoryProcessor;
    }

    // Create Repository Method Implementation
    public RepositoryEntity createRepository(
            CreateRepositoryRequest request) {

        RepositoryEntity repository = new RepositoryEntity();

        repository.setGithubUrl(request.getGithubUrl());

        repository.setName(
                extractRepositoryName(
                        request.getGithubUrl()));

        repository.setStatus(
                RepositoryStatus.PENDING);

        repository.setCreatedAt(
                LocalDateTime.now());

        RepositoryEntity savedRepository =
                repositoryRepository.save(repository);

        repositoryProcessor.processRepository(
                savedRepository.getId());

        return savedRepository;
    }

    // helper method for the create repository method
    private String extractRepositoryName(
            String githubUrl) {

        String[] parts = githubUrl.split("/");

        return parts[parts.length - 1];
    }

    // Service Method for Repo by id
    public RepositoryResponse getRepository(
            Long repositoryId) {

        RepositoryEntity repository =
                repositoryRepository.findById(repositoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Repository not found"));

        return mapToResponse(repository);
    }

    public List<RepositoryResponse> getAllRepositories() {

        return repositoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Mapper Method
    private RepositoryResponse mapToResponse(
            RepositoryEntity repository) {

        RepositoryResponse response =
                new RepositoryResponse();

        response.setId(repository.getId());
        response.setName(repository.getName());
        response.setGithubUrl(repository.getGithubUrl());
        response.setStatus(repository.getStatus());
        response.setCreatedAt(repository.getCreatedAt());

        return response;
    }
}
