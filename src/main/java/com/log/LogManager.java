package com.log;

import com.repository.RepoManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LogManager {
    //this class creates log.txt file and writes log object into it

    private static Scanner scanner = new Scanner(System.in);

    public static void commitBackupVersion() {
        System.out.println("Would you like to create new backup? [Y/N]");
        boolean finish = false;
        while (!finish) {
            switch (scanner.nextLine().toLowerCase()) {
                case "y":
                    logExistOrCreateFile(RepoManager.repo.getDirectoryPath().toString());
                    finish=true;
                    break;
                case "n":
                    System.out.println("Going back to main menu");
                    finish = true;
                    break;
                default:
                    System.out.println("Invalid key");
            }
        }
    }

    private static void logExistOrCreateFile(String repo) {
        Path repoPath = Path.of(repo);
        Path logPath = Path.of(repo + "\\log.txt");
        if (repoExist(repoPath)) {
            if (logFileExist(logPath)) {
                saveLog(repo+"\\log.txt", new Log());
            } else {
                try {
                    Files.createFile(logPath);
                    System.out.println("New log file created");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveLog(repo+"\\log.txt", new Log());
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
            //
            //MIEJSCE NA METODĘ OD GRZESIA!!!!
            //

            System.out.println("New backup version created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean logFileExist(Path repoPath) {
        return Files.exists(repoPath);
    }

    private static boolean repoExist(Path repoPath) {
        return Files.exists(repoPath);
    }
}
