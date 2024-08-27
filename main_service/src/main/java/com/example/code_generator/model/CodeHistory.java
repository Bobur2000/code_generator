package com.example.code_generator.model;

import java.time.LocalDateTime;

public class CodeHistory {
    private String username;
    private String code;
    private LocalDateTime dateTime;

    public CodeHistory(String username, String code, LocalDateTime dateTime) {
        this.username = username;
        this.code = code;
        this.dateTime = dateTime;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
