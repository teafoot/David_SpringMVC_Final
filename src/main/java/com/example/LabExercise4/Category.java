package com.example.LabExercise4;

import java.util.List;

public class Category {
    private String catCode;
    private String catDesc;

    private List<Item> items;

    public Category(String catCode, String catDesc) {
        this.catCode = catCode;
        this.catDesc = catDesc;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catcode) {
        this.catCode = catcode;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catdesc) {
        this.catDesc = catdesc;
    }
}
