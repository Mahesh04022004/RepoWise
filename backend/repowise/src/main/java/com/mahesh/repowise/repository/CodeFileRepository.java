package com.mahesh.repowise.repository;

import com.mahesh.repowise.entity.CodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CodeFileRepository
        extends JpaRepository<CodeFile, Long> {

    List<CodeFile> findByRepositoryId(Long repositoryId);

}
