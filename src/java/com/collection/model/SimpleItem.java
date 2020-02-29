package com.collection.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class SimpleItem {
    private int itemid;
    private String name;
    private int listid;
    private int ranking;
    private ArrayList<String> info;
    
    public SimpleItem() { }
    public SimpleItem(String name) {
        this.name = name;
        info = new ArrayList<>();
    }
    public SimpleItem(int itemid, String name, int listid, int ranking) {
        this.itemid = itemid;
        this.name = name;
        this.listid = listid;
        this.ranking = ranking;
        info = new ArrayList<>();
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
    public ArrayList<String> getInfo() {
        return info;
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
    public void addInfoAttribute(String attribute) {
        info.add(attribute);
    }
    
    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
