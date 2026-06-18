package com.mahesh.repowise.controller;

import com.mahesh.repowise.dto.ApiResponse;
import com.mahesh.repowise.dto.CreateRepositoryRequest;
import com.mahesh.repowise.dto.RepositoryResponse;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.git.GitCloneService;
import com.mahesh.repowise.repository.RepositoryRepository;
import com.mahesh.repowise.service.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;
    private final GitCloneService  gitCloneService;
    private final RepositoryRepository repositoryRepository;
    public RepositoryController(
            RepositoryService repositoryService, GitCloneService gitCloneService, RepositoryRepository repositoryRepository) {

        this.repositoryService = repositoryService;
        this.gitCloneService = gitCloneService;
        this.repositoryRepository = repositoryRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RepositoryEntity>> createRepository(
            @Valid @RequestBody
            CreateRepositoryRequest request) {

        RepositoryEntity response =
                repositoryService.createRepository(request);

        return ResponseEntity.ok(ApiResponse.success("Repository Created successfully",response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RepositoryResponse>>
    getRepository(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Repository fetched successfully",
                        repositoryService.getRepository(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RepositoryResponse>>>
    getAllRepositories() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Repositories fetched successfully",
                        repositoryService.getAllRepositories()
                )
        );
    }
}
