package com.mahesh.repowise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "code_chunks")
public class CodeChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer chunkIndex;

    private Integer startLine;

    private Integer endLine;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_file_id")
    private CodeFile codeFile;

    public CodeChunk(Long id, Integer chunkIndex, Integer startLine, Integer endLine, String content, CodeFile codeFile) {
        this.id = id;
        this.chunkIndex = chunkIndex;
        this.startLine = startLine;
        this.endLine = endLine;
        this.content = content;
        this.codeFile = codeFile;
    }

    public CodeChunk() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CodeFile getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(CodeFile codeFile) {
        this.codeFile = codeFile;
    }
}
