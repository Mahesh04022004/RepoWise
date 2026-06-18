package com.mahesh.repowise.service;

import com.mahesh.repowise.dto.CodeFileResponse;
import com.mahesh.repowise.entity.CodeFile;
import com.mahesh.repowise.repository.CodeFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;

    public CodeFileService(
            CodeFileRepository codeFileRepository) {

        this.codeFileRepository = codeFileRepository;
    }

    public List<CodeFileResponse>
    getRepositoryFiles(Long repositoryId) {

        return codeFileRepository
                .findByRepositoryId(repositoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Mapper method
    private CodeFileResponse mapToResponse(
            CodeFile codeFile) {

        CodeFileResponse response =
                new CodeFileResponse();

        response.setId(codeFile.getId());
        response.setFileName(codeFile.getFileName());
        response.setFilePath(codeFile.getFilePath());
        response.setLanguage(codeFile.getLanguage());
        response.setSize(codeFile.getSize());

        return response;
    }
}
