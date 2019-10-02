package com.log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LogManager {
    private static Scanner scanner = new Scanner(System.in);

    public static void commitBackupVersion() {
        System.out.println("Would you like to create new backup? [Y/N]");
        boolean finish = false;
        while (!finish) {
            switch (scanner.nextLine().toLowerCase()) {
                case "y":
                    logExistOrCreateFile("C:\\Users\\Łukasz Matuszewski\\Desktop\\MyBook\\.repo");
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
                saveLog(repo+"\\log.txt", createLog());
            } else {
                try {
                    Files.createFile(logPath);
                    System.out.println("New log file created");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveLog(repo+"\\log.txt", createLog());
            }
        } else {
            System.out.println("You have to create repo first");
        }
    }

    private static Log createLog() {
        return new Log();
    }

    private static void saveLog(String logPath, Log log){
        try {
            Writer writer = new BufferedWriter(new FileWriter(logPath, true));
            writer.append(log.toString());
            writer.close();
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