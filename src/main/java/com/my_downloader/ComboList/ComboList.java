package com.my_downloader.ComboList;

public class ComboList {

    private String itemName;
    private  String itemId;

    public ComboList(String item, String id) {
        this.itemName = item;
        this.itemId = id;
    }

    public String getItem() {
        return itemName;
    }

    public String getId() {
        return itemId;
    }

}
