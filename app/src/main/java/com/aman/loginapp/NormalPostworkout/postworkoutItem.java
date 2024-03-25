package com.aman.loginapp.NormalPostworkout;
public class postworkoutItem {
    private String itemName;
    private String imageUrl;
    private String itemId;

    public postworkoutItem() {
        // Required empty public constructor
    }

    public postworkoutItem(String itemName, String imageUrl, String itemId) {
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
