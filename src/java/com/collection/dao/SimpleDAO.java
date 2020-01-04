package com.collection.dao;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.collection.model.*;
import java.util.List;

@Repository
public class SimpleDAO {
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void initService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<SimpleList> getAllLists() {
        String sql = "SELECT * FROM SimpleLists";
        List<SimpleList> simpleLists = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SimpleList.class));
        return simpleLists;
    }
    public List<SimpleItem> getAllItems() {
        String sql = "SELECT * FROM SimpleItems";
        List<SimpleItem> simpleItems = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SimpleItem.class));
        return simpleItems;
    }
}
