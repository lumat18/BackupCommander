package com.log;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Log {
    private static Scanner scanner = new Scanner(System.in);
    private String user;
    private String description;
    private String dateTime;
    private String hashcode;

    public Log(String user, String description, String dateTime, String hashcode) {
        this.user = user;
        this.description = description;
        this.dateTime = dateTime;
        this.hashcode = hashcode;
    }

    public Log() {
        setHashcode();
        setDateTime();
        setUser();
        setDescription();
    }

    private void setUser() {
        System.out.println("Enter user name:");
        this.user = scanner.nextLine();
    }

    private void setDescription() {
        System.out.println("Enter backup description:");
        this.description = scanner.nextLine();
    }

    private void setDateTime() {
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    private void setHashcode() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[3];
        secureRandom.nextBytes(token);
        this.hashcode = new BigInteger(1, token).toString(16);
    }

    public String getHashcode() {
        return hashcode;
    }

    @Override
    public String toString() {
        return this.hashcode + ";" +
                this.dateTime + ";" +
                this.user + ";" +
                this.description + "\n";
    }
}
