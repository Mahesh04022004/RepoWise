package com.mahesh.repowise.git;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class GitCloneService {

    public String cloneRepository(
            String githubUrl,
            Long repositoryId) throws Exception {

        String localPath =
                "storage/repo-" + repositoryId;

        File directory = new File(localPath);

        Git.cloneRepository()
                .setURI(githubUrl)
                .setDirectory(directory)
                .call();

        return localPath;
    }
}
