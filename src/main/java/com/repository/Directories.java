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

        System.out.println("Please state how would you like to create a directory:");
        System.out.println("[1] - Name a full path.");
        System.out.println("[2] - Name Directory elements.");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt(); //będzie trzeba zrobić idiotoodporne

        switch (choice) {
            case 1:
                RepoMessages.fullPathInstructions();
                do {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        workingDirectory = scanner1.nextLine();
                        Directories.setWorkingDir(workingDirectory);
                        workingDirectory += "/.repo";
                        Directories.setRepoDir(workingDirectory);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid path.");
                        RepoMessages.fullPathInstructions();
                    }
                } while (true);
                break;
            case 2:
                System.out.println("Please enter directory elements. Press enter after each element to confirm. Type [-] to finish.");
                workingDirectory = "";
                do {
                    Scanner scanner1 = new Scanner(System.in);
                    String temp = scanner1.nextLine();
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
                break;
            default:
                //to be improved in a future release
                System.out.println("wrong choice");
        }
    }
}
