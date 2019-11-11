package com.log;

import com.repository.Directories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogPrinter {
    //this class is printing the content of the log.txt file that is in the .repo directory

    public static List<String> log = new ArrayList<>();
    public static List<Log> logsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Path repoPath = Paths.get(Directories.getRepoDir());

    public static void printLog() {
        boolean finish = false;
        readLogFile(repoPath);
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
        log.clear();
    }
    //hashcode, date, user, description
    public static void printFullLog() {
        log.stream().forEach(x-> {
            for (int i = 0; i < 4; i++) {
                System.out.print(x.split(";")[i] + "  ");
            }
            System.out.println();
        });
    }
    public static List<Log> provideLogsFX(){
        log.stream().forEach(x-> {
            String[] tempArrayList = new String[4];
            for (int i = 0; i < 4; i++) {
                tempArrayList[i] = x.split(";")[i];
            }
            logsList.add(new Log(tempArrayList[0], tempArrayList[1], tempArrayList[2], tempArrayList[3]));
        });
        return logsList;
    }
    private static void printSimpleLog() {
        log.stream().forEach(x->{
            System.out.println(x.split(";")[0] + " - " + x.split(";")[3]);
        });
    }

    public static List<String> readLogFile(Path repoPath) {
        Path logPath = Paths.get(repoPath.toString()+"\\log.txt");
//        if(repoExist(repoPath)){
//            if(logFileExist(logPath)){
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
//            }else {
//                System.out.println("You have to create initial log first");
//            }
//        }else{
//            System.out.println("You have to create repo first");
//        }
        return null;
    }

    public static boolean logFileExist(Path repoPath) {
        return Files.exists(repoPath);
    }

    private static boolean repoExist(Path repoPath) {
        return Files.exists(repoPath);
    }
}
