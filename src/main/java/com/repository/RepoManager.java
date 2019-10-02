package com.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RepoManager {

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
        String directory;
        String name;
        Path directoryPath;
        Repo repo;
        System.out.println("Please state how would you like to create a directory:");
        System.out.println("[1] - Name a full path.");
        System.out.println("[2] - Name directory elements.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                RepoMessages.fullPathInstructions();
                do {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        directory = scanner1.nextLine();
                        directory += "/.repo";
                        directoryPath = Paths.get(directory);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid path.");
                        RepoMessages.fullPathInstructions();
                    }
                } while (true);
                name = setRepoName();
                repo = new Repo(name, directoryPath);
                break;

            case 2:
                System.out.println("Please enter directory elements. Press enter after each element to confirm. Type [-] to finish.");
                directory = "";
                do {
                    Scanner scanner1 = new Scanner(System.in);
                    String temp = scanner1.nextLine();
                    if (temp.equals("-")) {
                        break;
                    } else {
                        //Maybe change this for a more optimal method like StringBuffer.append()?
                        directory += temp;
                        directory += "/";
                    }
                } while (true);
                directory += ".repo";
                directoryPath = Paths.get(directory);
                name = setRepoName();
                repo = new Repo(name, directoryPath);
                break;
            default:
                //to be improved in a future release
                repo = new Repo("broken repo - wrong choice", null);
        }
        try {
            existsOrCreate(repo.getDirectoryPath());
        } catch (IOException e) {
            e.printStackTrace(); //To be improved in future release.
        }
    }

    private static String setRepoName() {
        System.out.println("Please enter repository name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

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
