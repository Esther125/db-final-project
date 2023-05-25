package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
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
//    private List<Integer> itemStatus = new ArrayList<>();
    private List<Integer> itemBorrowDays = new ArrayList<>();

    @Override
    public List<Item> getItems(ItemType type, ItemStatus status) {
        String sql = "SELECT * FROM item WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if(type != null ){
            sql = sql + " AND type = :type";//AND 前面一定要加空白鍵
            map.put("type",type.name());//因為type的類型本來是ENUM，要把它轉成字串所以要.name()

        }

        if(status != null){
            sql = sql + " AND status = :status";
            map.put("status",status.name());
        }

        List<Item> itemList = namedParameterJdbcTemplate.query(sql,map,new ItemRowMapper());
        return itemList;
    }

//    @Override
//    public Item getItemByStatus(Integer status) {
//        String sql = "select * from item where status = :status";
//        Map<String, Object> map = new HashMap<>();
//        map.put("status",status);
//
//        List<Item> itemList = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
//
//        if(itemList.size()>0){
//
//            return itemList.get(0);
//        }else{
//            return null;
//        }
//
//    }

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
                "tenure,compensation_price,item_name)" +
                "VALUES(:type,:status,:borrow_day,:purchase_date," +
                ":tenure,:compensation_price,:item_name)";

        Map<String,Object> map = new HashMap<>();

        map.put("type",itemRequest.getType().toString());
        map.put("status",itemRequest.getStatus().toString());
        map.put("borrow_day",itemRequest.getBorrow_day());

        // 紀錄當下日期
        Date now = new Date();
        map.put("purchase_date",now);

        map.put("tenure",itemRequest.getTenure());
        map.put("compensation_price",itemRequest.getCompensation_price());
        map.put("item_name",itemRequest.getItem_name());



        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int item_id = keyHolder.getKey().intValue();

        return item_id;
    }

    @Override
    public void updateItem(Integer item_id, ItemRequest itemRequest) {
        String sql = "UPDATE item SET type = :type, status = :status," +
                "borrow_day = :borrow_day," +
                "tenure = :tenure, compensation_price = :compensation_price, item_name = :item_name" +
                " WHERE item_id = :item_id";

        Map<String,Object> map = new HashMap<>();
        map.put("item_id",item_id);
        map.put("type",itemRequest.getType().toString());
        map.put("status",itemRequest.getStatus().toString());
        map.put("borrow_day",itemRequest.getBorrow_day());

        // 紀錄當下日期
//        Date now = new Date();
//        map.put("purchase_date",now);

        map.put("tenure",itemRequest.getTenure());
        map.put("compensation_price",itemRequest.getCompensation_price());
        map.put("item_name",itemRequest.getItem_name());

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
