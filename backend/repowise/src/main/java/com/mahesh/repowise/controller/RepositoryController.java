package com.mahesh.repowise.controller;

import com.mahesh.repowise.dto.ApiResponse;
import com.mahesh.repowise.dto.CreateRepositoryRequest;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.service.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(
            RepositoryService repositoryService) {

        this.repositoryService = repositoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RepositoryEntity>> createRepository(
            @Valid @RequestBody
            CreateRepositoryRequest request) {

        RepositoryEntity response =
                repositoryService.createRepository(request);

        return ResponseEntity.ok(ApiResponse.success("Repository Created successfully",response));
    }
}
