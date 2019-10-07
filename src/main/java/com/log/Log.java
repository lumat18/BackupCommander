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

    public Log() {
    }

    public Log(String hashcode, String dateTime, String user, String description) {
        this.user = user;
        this.description = description;
        this.dateTime = dateTime;
        this.hashcode = hashcode;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
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
