package com.collection.model;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class SimpleList {
    private int listid;
    private String name;
    private ArrayList<String> outline;
    
    public SimpleList() {    }
    public SimpleList(String name) {
        this.name = name;
        outline = new ArrayList<>();
    }
    public SimpleList(int listid, String name) {
        this.listid = listid;
        this.name = name;
        outline = new ArrayList<>();
    }
    
    public int getListid() {
        return listid;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getOutline() {
        return outline;
    }
    public void setListid(int listid) {
        this.listid = listid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addOutlineField(String field) {
        outline.add(field);
    }
    
    public void setOutline(ArrayList<String> outline) {
        this.outline = outline;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
