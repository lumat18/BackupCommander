package com.repository;

import java.util.Scanner;

public class Directories {
    public static String workingDir;
    public static String repoDir;

    public static String getWorkingDir() {
        return workingDir;
    }
    public static void setWorkingDir(String workingDir) {
        Directories.workingDir = workingDir;
    }
    public static String getRepoDir() {
        return repoDir;
    }
    public static void setRepoDir(String repoDir) {
        Directories.repoDir = repoDir;
    }

    public static void directoryInit(){
        String workingDirectory;
        int choice = 1;

        RepoMessages.startingInstructions();
        Scanner scanner = new Scanner(System.in);
        try {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e){
            System.out.println("Wrong input - continue with [1]");
        }
        if (choice == 2){
            System.out.println("Please enter directory elements. Press enter after each element to confirm. Type [-] to finish.");
            workingDirectory = "";
            do {

                String temp = scanner.nextLine();
                if (temp.equals("-")) {
                    break;
                } else {
                    //Maybe change this for a more optimal method like StringBuffer.append()?
                    workingDirectory += temp + "/";
                }
            } while (true);
            Directories.setWorkingDir(workingDirectory);
            workingDirectory += ".repo";
            Directories.setRepoDir(workingDirectory);
        }
        else{
            RepoMessages.fullPathInstructions();
            do {
                try {

                    workingDirectory = scanner.nextLine();
                    Directories.setWorkingDir(workingDirectory);
                    workingDirectory += "/.repo";
                    Directories.setRepoDir(workingDirectory);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid path.");
                    RepoMessages.fullPathInstructions();
                }
            } while (true);
        }
    }
}
