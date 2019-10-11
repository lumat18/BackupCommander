package com.loader;

import com.log.Log;
import com.log.LogManager;
import com.log.LogPrinter;

import com.repository.Directories;
import com.repository.Repo;
import com.repository.RepoManager;
import com.saver.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class BackupLoader {
    private static Set<Log> storedBackups = new HashSet<>();

    public static void chooseBackup(){
        storedBackups=new HashSet<Log>();
        createBackupSet();
        printExistingBackups();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the hashcode of the chosen backup");
        String choice = scanner.nextLine();
        System.out.println("Do you want to store existing version? [y/n]");
        LogManager.commitBackupVersion();
        recallBackup(choice);
    }
    public static void printExistingBackups(){
        LogPrinter.printLog();
    }
    private static void createBackupSet(){
        String logPath = Directories.getRepoDir()+"/log.txt";
        File logs = new File(logPath);
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(logPath)))){
            scanner.useDelimiter(";");  //here we set the delimiter
           while (scanner.hasNextLine()) {
               String hashcode = scanner.next();
               scanner.skip(scanner.delimiter());
               String dateTime = scanner.next();
               scanner.skip(scanner.delimiter());
               String user = scanner.next();
               scanner.skip(scanner.delimiter());
               String description = scanner.nextLine();
               storedBackups.add(new Log (hashcode,dateTime,user,description));
           }
        }catch (Exception e){
            System.out.println("Exception found while reading in backups");
        }
    }
    private static boolean recallBackup(String hashcode){
        for (Log log:storedBackups) {
            if (log.getHashcode().equalsIgnoreCase(hashcode)){
                File chosenBackupLocation = new File(Directories.getRepoDir() + "/" + hashcode);
                File currentVersionLocation = new File(Directories.getWorkingDir());
                try {
                    FileManager.clearDir(currentVersionLocation,Directories.getRepoDir());
                    FileManager.copyDir(chosenBackupLocation,currentVersionLocation,"");
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }return false;
    }
}
