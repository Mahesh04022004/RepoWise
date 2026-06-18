package com.mahesh.repowise.service;

import com.mahesh.repowise.dto.CodeFileContentResponse;
import com.mahesh.repowise.dto.CodeFileResponse;
import com.mahesh.repowise.entity.CodeFile;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.repository.CodeFileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public CodeFileContentResponse getFileContent(
            Long fileId) {

        CodeFile codeFile =
                codeFileRepository.findById(fileId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "File not found"));

        RepositoryEntity repository =
                codeFile.getRepository();

        Path fullPath =
                Paths.get(
                        repository.getLocalPath(),
                        codeFile.getFilePath()
                );

        try {

            String content =
                    Files.readString(fullPath);

            CodeFileContentResponse response =
                    new CodeFileContentResponse();

            response.setId(codeFile.getId());
            response.setFileName(codeFile.getFileName());
            response.setFilePath(codeFile.getFilePath());
            response.setLanguage(codeFile.getLanguage());
            response.setContent(content);

            return response;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to read file content", e);
        }
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
