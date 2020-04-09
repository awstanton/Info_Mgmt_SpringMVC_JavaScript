package com.collection.service;

import com.collection.dao.SimpleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import com.collection.util.Util;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class SimpleService {
    @Autowired
    private SimpleDAO simpleDAO;
    
    @Autowired
    @Qualifier("util")
    private Util util;
    
    /********** List Page Methods **********/
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
    
    public void updateList(int listId, ArrayList<Integer> addItemIds, ArrayList<String> addItemVals, ArrayList<Integer> editItemIds,
                           ArrayList<String> editItemVals, ArrayList<String> fieldList, ArrayList<Integer> delItemIds) {
        
        System.out.println(delItemIds);
        System.out.println(util.getNextItemId());
        
        for (int i = 0; i < addItemIds.size(); ++i) {
            simpleDAO.addItemToList(listId, addItemIds.get(i), addItemVals.get(i));
            SimpleItem si = new SimpleItem(addItemIds.get(i), addItemVals.get(i), listId, 0);
            util.getItems().put(si.getItemid(), si);
        }
        
        if (!editItemIds.isEmpty()) {
            simpleDAO.updateItemNames(editItemIds, editItemVals);
            
            for (int i = 0; i < editItemIds.size(); ++i) {
                util.getItems().get(editItemIds.get(i)).setName(editItemVals.get(i));
            }
        }
        
        util.setNextItemId(util.getNextItemId() + addItemIds.size());
        
        if (!fieldList.isEmpty()) {
            StringBuffer fields = new StringBuffer();
        
            for (int i = 0; i < fieldList.size() - 1; ++i) {
                fields.append(fieldList.get(i) + ":");
            }

            fields.append(fieldList.get(fieldList.size() - 1));
            
            simpleDAO.setFields(listId, fields.toString());
            util.getLists().get(listId).setOutline(fieldList);
        }
        else {
            simpleDAO.setFields(listId, "");
            util.getLists().get(listId).setOutline(fieldList);
        }        
        
        for (int i = 0; i < delItemIds.size(); ++i) {
            simpleDAO.delItem(delItemIds.get(i));
            util.getItems().remove(delItemIds.get(i));
        }
        
        util.setNextItemId(util.getNextItemId() - delItemIds.size());
        
    }
    
    
    /********** Item Page Methods **********/
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
