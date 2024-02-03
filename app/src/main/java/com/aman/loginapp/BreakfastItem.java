package com.aman.loginapp;

// Model class for your data
public class BreakfastItem {
    private String itemName;
    private String calories;
    private String protien;
    private String carbs;
    private String fat;
    private String imageUrl;

    public BreakfastItem() {
        // Required empty public constructor
    }

    public BreakfastItem(String itemName,String calories,String protien,String carbs,String fat, String imageUrl) {
        this.itemName = itemName;
        this.calories = calories;
        this.protien = protien;
        this.carbs = carbs;
        this.fat = fat;
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }
    public String getcalories() {
        return calories;
    }
    public String getprotien() {
        return protien;
    }
    public String getcarbs() {
        return carbs;
    }
    public String getfat() {
        return fat;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
