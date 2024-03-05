package com.aman.loginapp.UnderweightPreworkout;
public class UWpreworkoutItem {
    private String itemName;
    private String imageUrl;

    public UWpreworkoutItem() {
        // Required empty public constructor
    }

    public UWpreworkoutItem(String itemName, String imageUrl) {
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
