package com.aman.loginapp;

public class DietProgress {
    private float initialBMI;
    private float finalBMI;
    private String userEmail;
    private String date;

    public DietProgress(float initialBMI, float finalBMI, String date, String userEmail) {
        this.initialBMI = initialBMI;
        this.finalBMI = finalBMI;
        this.date = date;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public float getInitialBMI() {
        return initialBMI;
    }

    public void setInitialBMI(float initialBMI) {
        this.initialBMI = initialBMI;
    }

    public float getFinalBMI() {
        return finalBMI;
    }

    public void setFinalBMI(float finalBMI) {
        this.finalBMI = finalBMI;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
