package com.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepoManager {
    public static Repo repo;
    //    Maybe inlcude list of repos? To be discussed.
    //    List<Repo> repoList = new ArrayList<>();

    public static void repoInit() {
        Path directoryPath;

        try {
            directoryPath = Paths.get(Directories.getRepoDir());
            repo = new Repo(directoryPath);
            existsOrCreate(repo.getDirectoryPath());
        } catch (IOException e) {
            e.printStackTrace(); //To be improved in future release.
        }
    }

    private static void existsOrCreate(Path path) throws IOException {
        //Throws exception if a path parent directory does not exist. To be improved in future release.
        if(Files.exists(path)){
            System.out.println("Repository already exists.");
        } else {
            System.out.println("Creating a new repository in the folder .repo.");
            Files.createDirectory(path);
        }
    }
}
