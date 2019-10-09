package com.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RepoManager {
    public static Repo repo;
    //    Maybe inlcude list of repos? To be discussed.
    //    List<Repo> repoList = new ArrayList<>();
    public static void startRepoManager() {
        int choice;
        RepoMessages.startingInstructions();
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid choice! Please try again.");
                RepoMessages.startingInstructions();
            }
        } while (true);

        switch (choice) {
            case 1:
                repoInit();
                break;
            case 2:
                System.out.println("This will redirect to main menu in future realeases");
//                mainMenu.start();
                break;
            default:
                //loop here or sth
                break;
        }
    }


    private static void repoInit() {
        String workingDirectory;
        Path directoryPath;

        System.out.println("Please state how would you like to create a directory:");
        System.out.println("[1] - Name a full path.");
        System.out.println("[2] - Name Directory elements.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                RepoMessages.fullPathInstructions();
                do {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        workingDirectory = scanner1.nextLine();

                        workingDirectory += "/.repo";
                        directoryPath = Paths.get(workingDirectory);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid path.");
                        RepoMessages.fullPathInstructions();
                    }
                } while (true);
                repo = new Repo(directoryPath);
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
                        workingDirectory += temp;
                        workingDirectory += "/";
                    }
                } while (true);
                workingDirectory += ".repo";
                directoryPath = Paths.get(workingDirectory);
                repo = new Repo(directoryPath);
                break;
            default:
                //to be improved in a future release
                System.out.println("wrong choice");
        }
        try {
            existsOrCreate(repo.getDirectoryPath());
        } catch (IOException e) {
            e.printStackTrace(); //To be improved in future release.
        }
    }

//    private static String setRepoName() {
//        System.out.println("Please enter repository name:");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
//    }

    private static void existsOrCreate(Path path) throws IOException {
        //Throws exception if a path parent directory does not exist. To be improved in future release.
        if(Files.exists(path)){
            System.out.println("Repository already exists.");
        } else {
            System.out.println("Creating a new repository in the folder .repo.");
            Files.createDirectory(path);
        }
    }
}
