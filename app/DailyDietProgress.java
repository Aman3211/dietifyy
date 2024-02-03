package com.aman.loginapp;
public class DailyDietProgress {
    private boolean dietUsed;

    public DailyDietProgress() {
        // Default constructor required for Firebase
    }

    public boolean isDietUsed() {
        return dietUsed;
    }

    public void setDietUsed(boolean dietUsed) {
        this.dietUsed = dietUsed;
    }
}
