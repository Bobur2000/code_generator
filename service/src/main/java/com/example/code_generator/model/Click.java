package com.example.code_generator.model;

import java.time.LocalDateTime;

public class Click {
    private LocalDateTime dateTime;
    private int x;
    private int y;

    public Click(LocalDateTime dateTime, int x, int y) {
        this.dateTime = dateTime;
        this.x = x;
        this.y = y;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
