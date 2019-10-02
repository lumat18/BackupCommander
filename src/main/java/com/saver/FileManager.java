package com.saver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileManager {
    public static void copyDir(File repoPath) throws IOException {
        if(!repoPath.exists()){
            new File(String.valueOf(repoPath)).mkdirs();
        }
    }
}
