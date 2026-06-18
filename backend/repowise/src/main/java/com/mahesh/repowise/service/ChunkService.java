package com.mahesh.repowise.service;

import com.mahesh.repowise.dto.ChunkContentResponse;
import com.mahesh.repowise.dto.ChunkResponse;
import com.mahesh.repowise.entity.CodeChunk;
import com.mahesh.repowise.repository.CodeChunkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChunkService {

    private final CodeChunkRepository codeChunkRepository;

    public ChunkService(
            CodeChunkRepository codeChunkRepository) {

        this.codeChunkRepository = codeChunkRepository;
    }
    public List<ChunkResponse>
    getRepositoryChunks(Long repositoryId) {

        return codeChunkRepository
                .findByCodeFileRepositoryId(
                        repositoryId)
                .stream()
                .map(this::mapToChunkResponse)
                .toList();
    }

    public ChunkContentResponse
    getChunk(Long chunkId) {

        CodeChunk chunk =
                codeChunkRepository.findById(chunkId)
                        .orElseThrow();

        ChunkContentResponse response =
                new ChunkContentResponse();

        response.setId(chunk.getId());

        response.setFileName(
                chunk.getCodeFile()
                        .getFileName());

        response.setChunkIndex(
                chunk.getChunkIndex());

        response.setStartLine(
                chunk.getStartLine());

        response.setEndLine(
                chunk.getEndLine());

        response.setContent(
                chunk.getContent());

        return response;
    }


    private ChunkResponse mapToChunkResponse(
            CodeChunk chunk) {

        ChunkResponse response =
                new ChunkResponse();

        response.setId(chunk.getId());

        response.setFileName(
                chunk.getCodeFile()
                        .getFileName());

        response.setChunkIndex(
                chunk.getChunkIndex());

        response.setStartLine(
                chunk.getStartLine());

        response.setEndLine(
                chunk.getEndLine());

        return response;
    }
}
