package com.collection.service;

import com.collection.dao.SimpleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class SimpleService {
    @Autowired
    private SimpleDAO simpleDAO;
    
    public List<SimpleList> getAllLists() {
        return simpleDAO.getAllLists();
    }

    public List<SimpleItem> getListItems(int id) {
        return simpleDAO.getListItems(id);
    }
    
    public void addList(SimpleList newList) {
        simpleDAO.addList(newList.getName());
        
    }
    
    public void addItem(SimpleItem newItem, int listId) {
        simpleDAO.addItem(newItem.getName(), listId);
    }
    
    public SimpleList getListById(int id) {
          return simpleDAO.getListById(id);
    }
    
    public SimpleItem getItemById(int id) {
        return simpleDAO.getItemById(id);
    }
    
    
}