package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.ItemDao;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Item getItemByStatus(Integer status) {
        String sql = "select * from item where status = :status";
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        List<Item> itemList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(itemList.size()>0){
            return itemList.get(0);
        }else{
            return null;
        }

    }

}
