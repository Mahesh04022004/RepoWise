package com.mahesh.repowise.repository;

import com.mahesh.repowise.entity.CodeChunk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeChunkRepository
        extends JpaRepository<CodeChunk, Long> {
}