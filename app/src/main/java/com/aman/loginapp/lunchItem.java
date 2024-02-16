package com.aman.loginapp;

// Model class for your data
public class lunchItem {

    private String itemName;
    private String imageUrl;
    private String itemId;

    public lunchItem() {
        // Required empty public constructor
    }

    public lunchItem(String itemName, String imageUrl, String itemId) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.itemId = itemId;

    }
    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
