package com.repository;

import java.nio.file.Path;

public class Repo {
    final private Path directoryPath;

    public Repo(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }
}
