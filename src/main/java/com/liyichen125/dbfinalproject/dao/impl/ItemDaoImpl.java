package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.ItemDao;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.rowmapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private List<Integer> itemIds = new ArrayList<>();
    private List<String> itemTypes = new ArrayList<>();
    private List<Integer> itemStatus = new ArrayList<>();
    private List<Integer> itemBorrowDays = new ArrayList<>();

    @Override
    public Item getItemByStatus(Integer status) {
        String sql = "select * from item where status = :status";
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        List<Item> itemList = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());

        if(itemList.size()>0){
            //這邊要修改 目前只會返回第一個項目
//          return itemList.get(0);

//            for (int i=0; i<itemList.size(); i++) {
//                Item item = itemList.get(i);
//                itemIds.add(item.getItem_id());
//                itemTypes.add(item.getType());
//                itemStatus.add(item.getStatus());
//                itemBorrowDays.add(item.getBorrow_day());
//            }
            return itemList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Item getItemById(Integer item_id) {
        String sql = "select * from item where item_id =:item_id";
        Map<String, Object> map = new HashMap<>();
        map.put("item_id",item_id);

        List<Item> itemList = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());

        if(itemList.size()>0){
            //這邊要修改 目前只會返回第一個項目
            return itemList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createItem(ItemRequest itemRequest){
        String sql = "INSERT INTO item(type,status,borrow_day,purchase_date," +
                "tenure,compensation_price)" +
                "VALUES(:type,:status,:borrow_day,:purchase_date," +
                ":tenure,:compensation_price)";

        Map<String,Object> map = new HashMap<>();

        map.put("type",itemRequest.getType().toString());
        map.put("status",itemRequest.getStatus());
        map.put("borrow_day",itemRequest.getBorrow_day());

        // 紀錄當下日期
        Date now = new Date();
        map.put("purchase_date",now);

        map.put("tenure",itemRequest.getTenure());
        map.put("compensation_price",itemRequest.getCompensation_price());



        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int item_id = keyHolder.getKey().intValue();

        return item_id;
    }

    @Override
    public void updateItem(Integer item_id, ItemRequest itemRequest) {
        String sql = "UPDATE item SET type = :type, status = :status," +
                "borrow_day = :borrow_day," +
                "tenure = :tenure, compensation_price = :compensation_price" +
                " WHERE item_id = :item_id";

        Map<String,Object> map = new HashMap<>();
        map.put("item_id",item_id);
        map.put("type",itemRequest.getType().toString());
        map.put("status",itemRequest.getStatus());
        map.put("borrow_day",itemRequest.getBorrow_day());

        // 紀錄當下日期
//        Date now = new Date();
//        map.put("purchase_date",now);

        map.put("tenure",itemRequest.getTenure());
        map.put("compensation_price",itemRequest.getCompensation_price());

        namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public void deleteItemById(Integer item_id) {
        String sql = "DELETE FROM item WHERE item_id = :item_id";

        Map<String, Object> map = new HashMap<>();
        map.put("item_id",item_id);
        namedParameterJdbcTemplate.update(sql,map);
    }
}
