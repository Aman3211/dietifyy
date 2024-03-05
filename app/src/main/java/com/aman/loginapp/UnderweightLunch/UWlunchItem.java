package com.aman.loginapp.UnderweightLunch;

// Model class for your data
public class UWlunchItem {

    private String itemName;
    private String imageUrl;
    private String itemId;

    public UWlunchItem() {
        // Required empty public constructor
    }

    public UWlunchItem(String itemName, String imageUrl, String itemId) {
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
