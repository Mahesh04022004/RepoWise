package com.mahesh.repowise.repository;

import com.mahesh.repowise.entity.CodeChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeChunkRepository
        extends JpaRepository<CodeChunk, Long> {
    List<CodeChunk> findByCodeFileRepositoryId(
            Long repositoryId);
}