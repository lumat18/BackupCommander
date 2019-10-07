package com.loader;

import com.log.Log;
import com.log.LogPrinter;

import com.repository.Repo;
import com.repository.RepoManager;

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
        //printExistingBackups();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the hashcode of the chosen backup");
        recallBackup(scanner.nextLine());
    }
    public static void printExistingBackups(){
        LogPrinter.printLog();
    }
    private static void createBackupSet(){
        String logPath = "C:\\Users\\bartm\\OneDrive\\Documents\\IntelijiProjects\\BackupCommander\\log.txt";
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
    private static void recallBackup(String hashcode){
        for (Log log:storedBackups) {
            if (log.getHashcode().equalsIgnoreCase(hashcode)){
                Path chosenBackupLocation = Path.of("C:\\Users\\bartm\\OneDrive\\Documents\\IntelijiProjects\\BackupCommander\\.repo\\"+hashcode);
                Path currentVersionLocation = Path.of("C:\\Users\\bartm\\OneDrive\\Documents\\IntelijiProjects\\BackupCommander\\currentVersion");
                try {
                    copyDirectory(chosenBackupLocation,currentVersionLocation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void copyDirectory(Path source, Path destination) throws IOException {
        List<Path> files = Files.walk(source).collect(Collectors.toList());
        for (Path file:files) {
            Files.copy(file, destination.resolve(source.relativize(file)),StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
