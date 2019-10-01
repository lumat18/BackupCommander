package com.repository;

import java.nio.file.Path;

public class Repo {
    private String name;
    final private Path directoryPath;

    public Repo(String name, Path directoryPath) {
        this.name = name;
        this.directoryPath = directoryPath;
    }

    public String getName() {
        return name;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }
}
