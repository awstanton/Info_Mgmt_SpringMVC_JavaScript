package com.collection.model;

import org.springframework.stereotype.Component;

@Component
public class SimpleItem {
    private int itemid;
    private String name;
    private int listid;
    private int ranking;
    private String description;
    
    
    public SimpleItem() { }
    public SimpleItem(String name) {
        this.name = name;
        description = "";
    }
    
    public int getItemid() {
        return itemid;
    }
    public String getName() {
        return name;
    }
    public int getListid() {
        return listid;
    }
    public int getRanking() {
        return ranking;
    }
    public String getDescription() {
        return description;
    }
    public void setItemid(int itemid) {
        this.itemid = itemid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setListid(int listid) {
        this.listid = listid;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    @Override
    public String toString() {
        return name;
    }
}
