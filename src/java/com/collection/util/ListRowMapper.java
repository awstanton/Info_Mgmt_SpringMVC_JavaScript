
package com.collection.util;

import com.collection.model.SimpleList;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ListRowMapper implements RowMapper<SimpleList> {
    
    @Override
    public SimpleList mapRow(ResultSet rs, int rowCount ) throws SQLException {
        SimpleList l = new SimpleList(rs.getInt(1), rs.getString(2));
        
        String outline = rs.getString(3);
        
        if (outline.equals("")) {
            l.setOutline(null);
        }
        else {
            String[] fields = outline.split(":");

            for (String field : fields)
                l.addOutlineField(field);
        }
        
        return l;
    }
    //getInt(int colIndex);
    //getString(int colIndex);
    //first(); // moves cursor to the first row
    // column numbers are starting from 1
}
