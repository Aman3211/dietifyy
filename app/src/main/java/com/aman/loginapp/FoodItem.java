package com.aman.loginapp;

public class FoodItem {
    private String name;
    private String calories;
    private String protein;
    private String carbs;
    private String fat;

    public FoodItem(String name, String calories, String protein, String carbs, String fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Add getters for each field

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getFat() {
        return fat;
    }
}
