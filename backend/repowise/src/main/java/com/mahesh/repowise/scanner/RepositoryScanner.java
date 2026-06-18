package com.mahesh.repowise.scanner;

import com.mahesh.repowise.entity.CodeFile;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.repository.CodeFileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class RepositoryScanner {

    private final CodeFileRepository codeFileRepository;
    private final LanguageDetector languageDetector;

    private static final Set<String> IGNORED_DIRECTORIES =
            Set.of(
                    ".git",
                    "node_modules",
                    "target",
                    "build",
                    "dist",
                    ".idea",
                    ".vscode"
            );


    public RepositoryScanner(
            CodeFileRepository codeFileRepository,
            LanguageDetector languageDetector) {

        this.codeFileRepository = codeFileRepository;
        this.languageDetector = languageDetector;
    }

    public int scanRepository(
            RepositoryEntity repository) {
        Path repositoryPath =
                Paths.get(repository.getLocalPath());

        List<CodeFile> files = new ArrayList<>();

        try (Stream<Path> stream =
                     Files.walk(repositoryPath)) {

            stream.filter(Files::isRegularFile)
                    .filter(this::isNotIgnored)
                    .forEach(path -> {

                        CodeFile codeFile =
                                createCodeFile(
                                        path,
                                        repositoryPath,
                                        repository
                                );

                        files.add(codeFile);
                    });

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to scan repository", e);
        }

        codeFileRepository.saveAll(files);

        return files.size();
    }

    private boolean isNotIgnored(Path path) {

        String fullPath =
                path.toString().toLowerCase();

        return IGNORED_DIRECTORIES.stream()
                .noneMatch(fullPath::contains);
    }

    private CodeFile createCodeFile(
            Path filePath,
            Path repositoryRoot,
            RepositoryEntity repository) {

        CodeFile codeFile =
                new CodeFile();

        codeFile.setFileName(
                filePath.getFileName().toString());

        codeFile.setFilePath(
                repositoryRoot
                        .relativize(filePath)
                        .toString());

        codeFile.setLanguage(
                languageDetector.detect(
                        filePath.getFileName().toString()));

        try {

            codeFile.setSize(
                    Files.size(filePath));

        } catch (IOException e) {

            codeFile.setSize(0L);
        }

        codeFile.setCreatedAt(
                LocalDateTime.now());

        codeFile.setRepository(repository);

        return codeFile;
    }
}
