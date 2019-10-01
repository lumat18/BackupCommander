package com.repository;

class RepoMessages {
    static void startingInstructions() {
        System.out.println("Instructions: ");
        System.out.println("[1] - to set up a working directory.");
        System.out.println("[2] - to roll back to starting screen.");
    }
    static void fullPathInstructions(){
        System.out.println("Sample directory (for formatting purposes):");
        System.out.println("C:/Users/Jane Doe/My Files/working directory");
        System.out.println("Make sure not to end the directory with '/'.");
        System.out.println("Please enter the working directory:");
    }
}
