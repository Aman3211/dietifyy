package com.aman.loginapp.NormalBreakfast;

// Model class for your data
public class BreakfastItem {
    private String itemName;
    private String imageUrl;
    private String itemId;

    public BreakfastItem() {
        // Required empty public constructor
    }

    public BreakfastItem(String itemName, String imageUrl, String itemId) {
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
