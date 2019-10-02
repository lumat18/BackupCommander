package com.log;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Logger {
    private static Scanner scanner = new Scanner(System.in);

    public static Log createNewLog() {
        Log newLog = new Log();
        newLog.setHashcode(generateHashcode());
        newLog.setDateTime(generateDateTime());
        newLog.setUser(getUserName());
        newLog.setDescription(getDescription());
        return newLog;
    }

    private static String generateHashcode() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[3];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }

    private static String generateDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    private static String getUserName() {
        System.out.println("Enter user name:");
        return scanner.nextLine();
    }

    private static String getDescription() {
        System.out.println("Enter backup description:");
        return scanner.nextLine();
    }
}
