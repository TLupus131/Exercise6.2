package com.example.btweek5.entities;

public class SpinnerItem {
    private String displayText;
    private int value;

    public SpinnerItem(String displayText, int value) {
        this.displayText = displayText;
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}

