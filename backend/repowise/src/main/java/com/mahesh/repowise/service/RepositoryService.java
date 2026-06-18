package com.mahesh.repowise.service;

import com.mahesh.repowise.dto.CreateRepositoryRequest;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.enums.RepositoryStatus;
import com.mahesh.repowise.repository.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(
            RepositoryRepository repositoryRepository) {

        this.repositoryRepository = repositoryRepository;
    }

    public RepositoryEntity createRepository(
            CreateRepositoryRequest request) {

        RepositoryEntity repository = new RepositoryEntity();

        repository.setGithubUrl(
                request.getGithubUrl());

        repository.setStatus(
                RepositoryStatus.PENDING);

        repository.setCreatedAt(
                LocalDateTime.now());

        return repositoryRepository.save(repository);
    }
}
