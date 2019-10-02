package com.log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class LogPrinter {
    private static Scanner scanner = new Scanner(System.in);

    public static void printLog() {
        System.out.println("What kind of log would you like to print? [F] - full, [S] - simple");
        switch (scanner.nextLine()) {
            case "F":
                printFullLog();
                break;
            case "S":
                printSimpleLog();
                break;
            default:
                System.out.println("Wrong key");
        }
    }

    private static void printFullLog() {

    }

    private static void printSimpleLog() {

    }

    private static List<String> readLogFile() {
        return null;
    }

    private static boolean logFileExist(Path repoPath) {
        return Files.exists(repoPath);
    }

    private static boolean repoExist(Path repoPath) {
        return Files.exists(repoPath);
    }
}
