import java.util.Scanner;

public class MainMenu {

    public static Scanner scanner = new Scanner(System.in);

    public static void start(){
        boolean quit = false;
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
    }
    public static void credits(){
        System.out.println("Here credits");
    }
}
