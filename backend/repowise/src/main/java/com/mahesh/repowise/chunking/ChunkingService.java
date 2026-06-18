package com.mahesh.repowise.chunking;

import com.mahesh.repowise.entity.CodeChunk;
import com.mahesh.repowise.entity.CodeFile;
import com.mahesh.repowise.entity.RepositoryEntity;
import com.mahesh.repowise.repository.CodeChunkRepository;
import com.mahesh.repowise.repository.CodeFileRepository;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    private final CodeFileRepository codeFileRepository;
    private final CodeChunkRepository codeChunkRepository;
    private static final int CHUNK_SIZE = 100;
    public ChunkingService(
            CodeFileRepository codeFileRepository,
            CodeChunkRepository codeChunkRepository) {

        this.codeFileRepository = codeFileRepository;
        this.codeChunkRepository = codeChunkRepository;
    }

    public int chunkRepository(
            RepositoryEntity repository) {

        List<CodeFile> files =
                codeFileRepository.findByRepositoryId(
                        repository.getId());

        int totalChunks = 0;

        for (CodeFile file : files) {

            totalChunks += chunkFile(
                    repository,
                    file);
        }

        return totalChunks;
    }

    private int chunkFile(
            RepositoryEntity repository,
            CodeFile codeFile) {

        try {

            Path fullPath =
                    Paths.get(
                            repository.getLocalPath(),
                            codeFile.getFilePath());

            List<String> lines =
                    Files.readAllLines(fullPath);

            return createChunks(
                    lines,
                    codeFile);

        } catch (Exception e) {

            return 0;
        }
    }

    private int createChunks(
            List<String> lines,
            CodeFile codeFile) {

        List<CodeChunk> chunks =
                new ArrayList<>();

        int chunkIndex = 0;

        for (int start = 0;
             start < lines.size();
             start += CHUNK_SIZE) {

            int end =
                    Math.min(
                            start + CHUNK_SIZE,
                            lines.size());

            String content =
                    String.join(
                            "\n",
                            lines.subList(start, end));

            CodeChunk chunk =
                    new CodeChunk();

            chunk.setChunkIndex(chunkIndex++);

            chunk.setStartLine(start + 1);

            chunk.setEndLine(end);

            chunk.setContent(content);

            chunk.setCodeFile(codeFile);

            chunks.add(chunk);
        }

        codeChunkRepository.saveAll(chunks);

        return chunks.size();
    }
}
