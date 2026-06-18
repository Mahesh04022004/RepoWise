package com.mahesh.repowise.service;

import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.enums.RepositoryStatus;
import com.mahesh.repowise.git.GitCloneService;
import com.mahesh.repowise.repository.RepositoryRepository;
import com.mahesh.repowise.scanner.RepositoryScanner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RepositoryProcessor {

    private final RepositoryRepository repositoryRepository;
    private final GitCloneService gitCloneService;
    private final RepositoryScanner repositoryScanner;

    public RepositoryProcessor(
            RepositoryRepository repositoryRepository,
            GitCloneService gitCloneService, RepositoryScanner repositoryScanner) {

        this.repositoryRepository = repositoryRepository;
        this.gitCloneService = gitCloneService;
        this.repositoryScanner = repositoryScanner;
    }

    @Async
    public void processRepository(Long repositoryId) {

        RepositoryEntity repository =
                repositoryRepository.findById(repositoryId)
                        .orElseThrow();

        try {

            repository.setStatus(
                    RepositoryStatus.CLONING);

            repositoryRepository.save(repository);

            String localPath =
                    gitCloneService.cloneRepository(
                            repository.getGithubUrl(),
                            repositoryId);

            repository.setLocalPath(localPath);

            repository.setStatus(
                    RepositoryStatus.SCANNING);

            repositoryRepository.save(repository);

            int totalFiles =
                    repositoryScanner.scanRepository(
                            repository);

            repository.setTotalFiles(totalFiles);

            repository.setStatus(
                    RepositoryStatus.READY);

            repositoryRepository.save(repository);

        } catch (Exception e) {

            repository.setStatus(
                    RepositoryStatus.FAILED);

            repositoryRepository.save(repository);

            e.printStackTrace();
        }
    }
}
