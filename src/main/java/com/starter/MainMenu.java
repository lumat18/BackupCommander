package com.starter;

import com.log.LogManager;
import com.log.LogPrinter;
import com.repository.Directories;
import com.repository.RepoManager;

import java.util.Scanner;

public class MainMenu {

    public static Scanner scanner = new Scanner(System.in);

    public static void start(){
        boolean quit = false;
        Directories.directoryInit();
        printInstructions();
        while (!quit){
            System.out.println("Choose what to do:");
            String choice = scanner.nextLine();
            switch (choice){
                case "0":
                    System.out.println("Quitting app...");
                    quit=true;
                    break;
                case "1":
                    printInstructions();
                    break;
                case "2":
                    credits();
                    break;
                case "3":
                    LogManager.commitBackupVersion();
                    break;
                case "4":
                    LogPrinter.printLog();
                    break;
                case "5":
                    RepoManager.repoInit();
                    break;
                default:
                    System.out.println("Invalid instruction");
                    printInstructions();
                    break;
            }
        }
    }
    public static void printInstructions(){
        System.out.println("Availible options:");
        System.out.println("[0] - quit");
        System.out.println("[1] - print instructions");
        System.out.println("[2] - credits");
        System.out.println("[3] - create backup version");
        System.out.println("[4] - print backup log");
        System.out.println("[5] - initialize repo");
    }
    public static void credits(){
        System.out.println("Here credits");
    }
}
