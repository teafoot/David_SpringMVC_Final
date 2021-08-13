package com.example.LabExercise4;

public class Item {
    private String itemCode;
    private String itemDesc;

    public Item(String itemCode, String itemDesc) {
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}