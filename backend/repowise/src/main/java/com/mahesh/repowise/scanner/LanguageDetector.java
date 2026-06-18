package com.mahesh.repowise.scanner;

import org.springframework.stereotype.Service;

@Service
public class LanguageDetector {

    public String detect(String fileName) {

        String lower = fileName.toLowerCase();

        if (lower.endsWith(".java")) return "JAVA";
        if (lower.endsWith(".js")) return "JAVASCRIPT";
        if (lower.endsWith(".ts")) return "TYPESCRIPT";
        if (lower.endsWith(".jsx")) return "REACT";
        if (lower.endsWith(".tsx")) return "REACT_TS";
        if (lower.endsWith(".py")) return "PYTHON";
        if (lower.endsWith(".xml")) return "XML";
        if (lower.endsWith(".properties")) return "PROPERTIES";
        if (lower.endsWith(".json")) return "JSON";

        return "UNKNOWN";
    }
}
