package com.collection.dao;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.collection.model.*;
import java.util.List;
import java.util.ArrayList;
import org.springframework.dao.EmptyResultDataAccessException;
import com.collection.util.ListRowMapper;
import com.collection.util.ItemRowMapper;

@Repository
public class SimpleDAO {
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void initService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    /* Lists Page Methods */
    public List<SimpleList> getAllLists() {
        String sql = "SELECT * FROM lists";
        List<SimpleList> simpleLists = jdbcTemplate.query(sql, new ListRowMapper());
        return simpleLists;
    }
    
    public SimpleList getListById(int listId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM lists WHERE listid = " + listId;
        return (SimpleList) jdbcTemplate.query(sql, new ListRowMapper()).get(0);
    }
    
    public void addList(String name) {
        String sql = "INSERT INTO lists VALUES (NULL,'" + name + "','')"; // ADDED EMPTY ITEM OUTLINE
        jdbcTemplate.update(sql);
    }
    
    public void updateListNames(List<Integer> ids, List<String> names) {
        String[] sqls = new String[ids.size()];
        for (int i = 0; i < ids.size(); ++i) {
            sqls[i] = "UPDATE lists SET name = '" + names.get(i) + "' WHERE listid = " + ids.get(i);
        }
        jdbcTemplate.batchUpdate(sqls);
    }
    
    public boolean delList(int id) {
        String sql1 = "DELETE FROM items WHERE listid = ?";
        String sql2 = "DELETE FROM lists WHERE listid = ?";
        jdbcTemplate.update(sql1, id);
        return jdbcTemplate.update(sql2, id) > 0;
    }
    
    
    /* Items Page Methods */
    public List<SimpleItem> getListItems(int listId) {
        String sql = "SELECT * FROM items WHERE listid = " + listId;
        List<SimpleItem> listItems = jdbcTemplate.query(sql, new ItemRowMapper());
        return listItems;
    }
    
    public SimpleItem getItemById(int itemId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM items WHERE itemid = " + itemId;
        return (SimpleItem) jdbcTemplate.query(sql, new ItemRowMapper()).get(0);
    }
    
    public void addItem(String name, int listId) {
        String sql = "INSERT INTO items VALUES (NULL,'" + name + "'," + listId + ",0,'')"; // ADDED EMPTY ITEM INFO
        jdbcTemplate.update(sql);
    }

    public void updateItemNames(List<Integer> ids, List<String> names) {
        String[] sqls = new String[ids.size()];
        for (int i = 0; i < ids.size(); ++i) {
            sqls[i] = "UPDATE items SET name = '" + names.get(i) + "' WHERE itemid = " + ids.get(i);
        }
        jdbcTemplate.batchUpdate(sqls);
    }
    
    public boolean delItem(int id) {
        String sql = "DELETE FROM items WHERE itemid = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    public int getNextListId() {
        String sql = "SELECT MAX(listId) FROM lists";
        return (int) jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }
    
    public int getNextItemId() {
        String sql = "SELECT MAX(itemId) FROM items";
        return (int) jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }
    
    public boolean updateOutline(int listId, String outline) {
        String sql = "UPDATE lists SET itemOutline = '" + outline + "' WHERE listid = " + listId;
        return jdbcTemplate.update(sql) > 0;
    }
    
    public boolean updateItemInfo(int itemId, String info) {
        String sql = "UPDATE items SET info = '" + info + "' WHERE itemid = " + itemId;
        return jdbcTemplate.update(sql) > 0;
    }
    
    public String getOutline(int listId) {
        String sql = "SELECT itemOutline FROM lists WHERE listid = " + listId;
        return jdbcTemplate.queryForObject(sql, String.class);
    }
    
    public String getItemInfo(int itemId) {
        String sql = "SELECT info FROM items WHERE itemid = " + itemId;
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}

