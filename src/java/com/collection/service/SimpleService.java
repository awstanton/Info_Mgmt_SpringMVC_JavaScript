package com.collection.service;

import com.collection.dao.SimpleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class SimpleService {
    @Autowired
    private SimpleDAO simpleDAO;
    
    /* List Page Methods */
    public List<SimpleList> getAllLists() {
        return simpleDAO.getAllLists();
    }

    public SimpleList getListById(int id) {
          return simpleDAO.getListById(id);
    }
    
    public void addList(String newListName) {
        simpleDAO.addList(newListName);
    }
    
    public void updateListNames(List<Integer> ids, List<String> names) {
        simpleDAO.updateListNames(ids, names);
    }
    
    public void delList(int id) {
        simpleDAO.delList(id);
    }
    
    
    /* Item Page Methods */
    public List<SimpleItem> getListItems(int id) {
        return simpleDAO.getListItems(id);
    }
    
    public SimpleItem getItemById(int id) {
        return simpleDAO.getItemById(id);
    }
    
    public void addItem(String newItemName, int listId) {
        simpleDAO.addItem(newItemName, listId);
    }
    
    public void updateItemNames(List<Integer> ids, List<String> names) {
        simpleDAO.updateItemNames(ids, names);
    }
    
    public void delItem(int id) {
        simpleDAO.delItem(id);
    }
    
    public int getNextListId() {
        return simpleDAO.getNextListId();
    }
    
    public int getNextItemId() {
        return simpleDAO.getNextItemId();
    }
    
    public void updateOutline(int listId, String outline) {
        simpleDAO.updateOutline(listId, outline);
    }
    
    public void updateItemInfo(int itemId, String info) {
        simpleDAO.updateItemInfo(itemId, info);
    }
    
    public String[] getOutline(int listId) {
        return simpleDAO.getOutline(listId).split(":");
    }
    
    public String[] getItemInfo(int itemId) {
        return simpleDAO.getItemInfo(itemId).split(":");
    }

}
