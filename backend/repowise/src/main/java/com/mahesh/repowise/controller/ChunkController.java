package com.mahesh.repowise.controller;

import com.mahesh.repowise.dto.ApiResponse;
import com.mahesh.repowise.dto.ChunkContentResponse;
import com.mahesh.repowise.dto.ChunkResponse;
import com.mahesh.repowise.service.ChunkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChunkController {

    private final ChunkService chunkService;

    public ChunkController(
            ChunkService chunkService) {

        this.chunkService = chunkService;
    }

    @GetMapping(
            "/repositories/{repositoryId}/chunks")
    public ResponseEntity<
            ApiResponse<List<ChunkResponse>>>
    getRepositoryChunks(
            @PathVariable Long repositoryId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Chunks fetched successfully",
                        chunkService
                                .getRepositoryChunks(
                                        repositoryId)
                )
        );
    }

    @GetMapping("/chunks/{chunkId}")
    public ResponseEntity<
            ApiResponse<ChunkContentResponse>>
    getChunk(
            @PathVariable Long chunkId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Chunk fetched successfully",
                        chunkService
                                .getChunk(chunkId)
                )
        );
    }

}
