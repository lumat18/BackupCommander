package com.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogPrinter {
    //this class is printing the content of the log.txt file that is in the .repo directory

    private static  List<String> log = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Path repoPath = Path.of("C:\\Users\\Łukasz Matuszewski\\Desktop\\MyBook\\.repo");

    public static void printLog() {
        boolean finish = false;
        while(!finish){
            System.out.println("What kind of log would you like to print? [F] - full, [S] - simple");
            switch (scanner.nextLine().toUpperCase()) {
                case "F":
                    printFullLog();
                    finish = true;
                    break;
                case "S":
                    printSimpleLog();
                    finish = true;
                    break;
                default:
                    System.out.println("Wrong key");
            }
        }
    }

    private static void printFullLog() {
        readLogFile(repoPath);
        log.stream().forEach(x-> {
            for (int i = 0; i < 4; i++) {
                System.out.print(x.split(";")[i] + "  ");
            }
            System.out.println();
        });
    }

    private static void printSimpleLog() {
        readLogFile(repoPath);
        log.stream().forEach(x->{
            System.out.println(x.split(";")[0] + " - " + x.split(";")[3]);
        });
    }

    private static List<String> readLogFile(Path repoPath) {
        Path logPath = Path.of(repoPath.toString()+"\\log.txt");
        if(repoExist(repoPath)){
            if(logFileExist(logPath)){
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(logPath)));
                    String line = bufferedReader.readLine();
                    while (line!=null){
                        log.add(line);
                        line=bufferedReader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("You have to create initial log first");
            }
        }else{
            System.out.println("You have to create repo first");
        }
        return null;
    }

    private static boolean logFileExist(Path repoPath) {
        return Files.exists(repoPath);
    }

    private static boolean repoExist(Path repoPath) {
        return Files.exists(repoPath);
    }
}
