package com.aman.loginapp.UnderweightBreakfast;

// Model class for your data
public class UWbreakfastItem {
    private String itemName;
    private String imageUrl;

    public UWbreakfastItem() {
        // Required empty public constructor
    }

    public UWbreakfastItem(String itemName, String imageUrl) {
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
