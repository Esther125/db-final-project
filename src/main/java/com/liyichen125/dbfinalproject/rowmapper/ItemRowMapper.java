package com.liyichen125.dbfinalproject.rowmapper;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();

        item.setItem_id(resultSet.getInt("item_id"));

        // 重要

        // String 和 ENUM之間的轉換
        String typeStr = resultSet.getString("type");
        ItemType type = ItemType.valueOf(typeStr);
        item.setType(type);

        String statusStr = resultSet.getString("status");
        ItemStatus status = ItemStatus.valueOf(statusStr);
        item.setStatus(status);


        //簡潔寫法
        //item.setType(ItemType.valueOf(resultSet.getString("category")));

        item.setBorrow_day(resultSet.getInt("borrow_day"));
        item.setPurchase_date(resultSet.getDate("purchase_date"));
        item.setTenure(resultSet.getInt("tenure"));
        item.setCompensation_price(resultSet.getInt("compensation_price"));
        item.setItem_name(resultSet.getString("item_name"));
        return item;

    }
}
