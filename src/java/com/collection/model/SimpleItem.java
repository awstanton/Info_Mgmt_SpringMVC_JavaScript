package com.collection.model;


public class SimpleItem {
    private String name;
    private String description;
    
    public SimpleItem(String name) {
        this.name = name;
        description = "";
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
