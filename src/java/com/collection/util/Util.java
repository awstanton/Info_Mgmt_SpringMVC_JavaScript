
package com.collection.util;

import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("util")
@Scope("singleton")
public class Util {
    private HashMap<Integer,SimpleList> lists;
    private HashMap<Integer,SimpleItem> items;
    
    private int nextListId;
    private int nextItemId;
    
    public Util() {
//        System.out.println("util constructor called");
        lists = new HashMap<>();
        items = new HashMap<>();
    }
    
    public int getNextListId() {
        return nextListId;
    }
    public int getNextItemId() {
        return nextItemId;
    }
    
    public void setNextListId(int nextListId) {
        this.nextListId = nextListId;
    }
    public void setNextItemId(int nextItemId) {
        this.nextItemId = nextItemId;
    }
    
    public HashMap<Integer,SimpleList> getLists() {
        return lists;
    }
    public HashMap<Integer,SimpleItem> getItems() {
        return items;
    }
    
//    public void setLists(HashMap<Integer,SimpleList> lists) {
//        this.lists = lists;
//    }
//    public void setItems(HashMap<Integer,SimpleItem> items) {
//        this.items = items;
//    }
    
    public int postIncrNextListId() {
        return nextListId++;
    }
    public int postIncrNextItemId() {
        return nextItemId++;
    }
    
//    public ArrayList<String> getInfoAttributes() {
//        
//        return lists.values();
//    }
    
    public ArrayList<String> getOutlineFieldsByListId(int listId) {
        return lists.get(listId).getOutline();
    }
    
    public ArrayList<String> getInfoAttributesByItemId(int itemId) {
        return items.get(itemId).getInfo();
    }
    

}
