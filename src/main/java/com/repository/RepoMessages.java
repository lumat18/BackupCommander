package com.repository;

class RepoMessages {
    static void startingInstructions() {
        System.out.println("Please state how would you like to create a directory ([1] by default):");
        System.out.println("[1] - Name a full path.");
        System.out.println("[2] - Name Directory elements.");
    }
    static void fullPathInstructions(){
        System.out.println("Sample directory (for formatting purposes):");
        System.out.println("C:/Users/Jane Doe/My Files/working directory");
        System.out.println("Make sure not to end the directory with '/'.");
        System.out.println("Please enter the working directory:");
    }
}
