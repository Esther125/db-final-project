package com.liyichen125.dbfinalproject.rowmapper;

import com.liyichen125.dbfinalproject.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();

        item.setItem_id(resultSet.getInt("item_id"));
        item.setType(resultSet.getString("type"));
        item.setStatus(resultSet.getInt("status"));
        item.setBorrow_day(resultSet.getInt("borrow_day"));

        return item;

    }
}
