package com.collection.model;

import org.springframework.stereotype.Component;

@Component
public class SimpleList {
    private int listid;
    private String name;
    
    
    public SimpleList() {    }
    public SimpleList(String name) {
        this.name = name;
    }
    public SimpleList(int id, String name) {
        listid = id;
        this.name = name;
    }
    
    public int getListid() {
        return listid;
    }
    public void setListid(int id) {
        listid = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
