package com.log;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Log {
    private String user;
    private String description;
    private String dateTime;
    private String hashcode;
    private static Scanner scanner = new Scanner(System.in);

    public Log() {
        this.user = getUserName();
        this.description = getDescription();
        this.dateTime = generateDateTime();
        this.hashcode = generateHashcode();
    }

    private static String generateHashcode() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[3];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }

    private static String generateDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm"));
    }

    private static String getUserName() {
        System.out.println("Enter user name:");
        return scanner.nextLine();
    }

    private static String getDescription() {
        System.out.println("Enter backup description:");
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return this.hashcode + ";" +
                this.dateTime + ";" +
                this.user + ";" +
                this.description + "\n";
    }
}
