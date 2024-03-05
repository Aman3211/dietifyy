package com.aman.loginapp.NormalPostworkout;
public class postworkoutItem {
    private String itemName;
    private String imageUrl;

    public postworkoutItem() {
        // Required empty public constructor
    }

    public postworkoutItem(String itemName, String imageUrl) {
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
