package com.mahesh.repowise.repository;

import com.mahesh.repowise.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository
        extends JpaRepository<RepositoryEntity, Long> {
}