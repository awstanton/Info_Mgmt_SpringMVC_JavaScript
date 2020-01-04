package com.collection.service;

import com.collection.dao.SimpleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    @Autowired
    private SimpleDAO simpleDAO;
    
    public List<SimpleList> getAllLists() {
        return simpleDAO.getAllLists();
    }
    public List<SimpleItem> getAllItems() {
        return simpleDAO.getAllItems();
    }
}