package com.collection.model;


public class SimpleList {
    private String name;
    private int rank;
    private int size;
    
    @Override
    public String toString() {
        return "name: " + name + ", rank = " + rank + ", size = " + size;
    }
    
    public SimpleList() {    }
    
    public SimpleList(String name) {
        this.name = name;
        rank = 0;
        size = 0;
    }
    public String getName() {
        return name;
    }
    public int getRank() {
        return rank;
    }
    public int getSize() {
        return size;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
}
