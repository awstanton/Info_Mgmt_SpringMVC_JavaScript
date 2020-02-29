
package com.collection.util;

import org.springframework.jdbc.core.RowMapper;
import com.collection.model.SimpleItem;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<SimpleItem> {
    
    @Override
    public SimpleItem mapRow(ResultSet rs, int rowCount) throws SQLException {
        SimpleItem i = new SimpleItem(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        
        String info = rs.getString(5);
        
        if (info.equals("")) {
            i.setInfo(null);
        }
        else {
            String[] infoAttributes = info.split(":");

            for (String infoAttribute : infoAttributes)
                i.addInfoAttribute(infoAttribute);
        }
        
        return i;
    }
}
