package com.log;

import com.Threads.CopyMessage;
import com.repository.Directories;
import com.repository.RepoManager;
import com.saver.FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LogManager {
    //this class creates log.txt file and writes log object into it

    private static Scanner scanner = new Scanner(System.in);

    public static void commitBackupVersion() {

        boolean finish = false;
        while (!finish) {
            switch (scanner.nextLine().toLowerCase()) {
                case "y":
//                    logExistOrCreateFile(Directories.getRepoDir());
                    finish=true;
                    break;
                case "n":
                    finish = true;
                    break;
                default:
                    System.out.println("Invalid key");
            }
        }
    }

    public static void logExistOrCreateFile(String repo, String username, String description) {
        Path repoPath = Paths.get(repo);
        Path logPath = Paths.get(repo + "\\log.txt");

        if (repoExist(repoPath)) {
            if (logFileExist(logPath)) {
                saveLog(repo + "\\log.txt", new Log(username, description));
            } else {
                try {
                    Files.createFile(logPath);
                    System.out.println("New log file created");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveLog(repo+"\\log.txt", new Log(username, description));
            }
        } else {
            System.out.println("You have to create repo first");
        }
    }

    private static void saveLog(String logPath, Log log){
        try {
            Writer writer = new BufferedWriter(new FileWriter(logPath, true));
            writer.append(log.toString());
            writer.close();
            FileManager.fileSaver(log.getHashcode());
            System.out.println("\nNew backup version created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean logFileExist(Path repoPath) {
        return Files.exists(repoPath);
    }

    private static boolean repoExist(Path repoPath) {
        return Files.exists(repoPath);
    }
}
