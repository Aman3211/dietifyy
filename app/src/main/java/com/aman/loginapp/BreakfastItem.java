package com.aman.loginapp;

// Model class for your data
public class BreakfastItem {
    private String itemName;
    private String imageUrl;

    public BreakfastItem() {
        // Required empty public constructor
    }

    public BreakfastItem(String itemName, String imageUrl) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
