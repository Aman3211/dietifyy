package com.aman.loginapp.NormalPreworkout;
public class preworkoutItem {
    private String itemName;
    private String imageUrl;
    private String itemId;

    public preworkoutItem() {
        // Required empty public constructor
    }

    public preworkoutItem(String itemName, String imageUrl, String itemId) {
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
