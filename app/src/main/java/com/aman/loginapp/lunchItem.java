package com.aman.loginapp;

// Model class for your data
public class lunchItem {
    private String itemName;
    private String imageUrl;

    public lunchItem() {
        // Required empty public constructor
    }

    public lunchItem(String itemName, String imageUrl) {
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
