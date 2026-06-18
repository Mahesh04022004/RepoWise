package com.mahesh.repowise.controller;

import com.mahesh.repowise.dto.ApiResponse;
import com.mahesh.repowise.dto.CodeFileContentResponse;
import com.mahesh.repowise.dto.CodeFileResponse;
import com.mahesh.repowise.service.CodeFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/repositories")
public class CodeFileController {

    private final CodeFileService codeFileService;

    public CodeFileController(
            CodeFileService codeFileService) {

        this.codeFileService = codeFileService;
    }

    @GetMapping("/{repositoryId}/files")
    public ResponseEntity<ApiResponse<
                    List<CodeFileResponse>>>
    getRepositoryFiles(
            @PathVariable Long repositoryId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Files fetched successfully",
                        codeFileService
                                .getRepositoryFiles(
                                        repositoryId)
                )
        );
    }

    @GetMapping("/{repositoryId}/files/{fileId}")
    public ResponseEntity<ApiResponse<CodeFileContentResponse>>
    getFileContent(
            @PathVariable Long repositoryId,
            @PathVariable Long fileId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "File content fetched successfully",
                        codeFileService.getFileContent(fileId)
                )
        );
    }

}
