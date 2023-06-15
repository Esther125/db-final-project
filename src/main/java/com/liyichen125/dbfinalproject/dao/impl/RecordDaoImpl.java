package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.rowmapper.ItemRowMapper;
import com.liyichen125.dbfinalproject.rowmapper.RecordRowMapper;
import com.liyichen125.dbfinalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RecordDaoImpl implements RecordDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private ItemService itemService;

    @Override
    public Integer createRecord(RecordRequest recordRequest) {
        String sql = "INSERT INTO dormy.record(user_id,item_id,contact_person_id,situation,borrow_date,return_date,violation_type)" +
                "VALUES (:user_id,:item_id,:contact_person_id,:situation,:borrow_date,:return_date,:violation_type)";

        Map<String,Object> map = new HashMap<>();
        map.put("user_id",recordRequest.getUser_id());
        map.put("item_id", recordRequest.getItem_id());
        map.put("situation", recordRequest.getSituation().toString());

        map.put("contact_person_id", recordRequest.getContact_person_id());
        map.put("violation_type", recordRequest.getViolation_type());
        Item item = itemService.getItemById(recordRequest.getItem_id());
        LocalDate now = LocalDate.now();
        map.put("borrow_date",now);
        LocalDate returnDate = now.plusDays(item.getBorrow_day());
        map.put("return_date",returnDate);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int record_id = keyHolder.getKey().intValue();

        return record_id;
    }
    @Override
    public  List<Record> getRecordsByUserId(Integer userId){
        String sql = "SELECT r.record_id,i.item_id,r.situation,r.contact_person_id,r.borrow_date,return_date,r.violation_type,u.user_id, i.item_name FROM dormy.record AS r LEFT JOIN dormy.item AS i ON r.item_id = i.item_id  LEFT JOIN dormy.user AS u ON r.user_id = u.user_id  WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        sql = sql + " AND r.user_id = :user_id";
        map.put("user_id",userId);
        List<Record> recordList = namedParameterJdbcTemplate.query(sql,map,new RecordRowMapper());
        return recordList;
    }
    @Override
    public List<Record> getRecords(RecordSituation situation, String search) {
        String sql = "SELECT r.record_id,i.item_id,r.situation,r.contact_person_id,r.borrow_date,return_date,r.violation_type,u.user_id, i.item_name FROM dormy.record AS r LEFT JOIN dormy.item AS i ON r.item_id = i.item_id  LEFT JOIN dormy.user AS u ON r.user_id = u.user_id  WHERE 1=1";
        //String sql = "SELECT * FROM dormy.record WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        if(situation != null){
            sql = sql + " AND situation = :situation";
            map.put("situation",situation.name());
        }

        //模糊查詢(關鍵字搜尋)
        if(search != null){
            //百分比(%)不可以直接寫在SQL裡面，要寫在map的值裡面(jdbc template規定)
            sql = sql + " AND u.user_id LIKE :search";
            map.put("search","%" + search + "%");
        }

        List<Record> recordList = namedParameterJdbcTemplate.query(sql,map,new RecordRowMapper());
        return recordList;
    }

    @Override
    public Record getRecordById(Integer recordId){
        String sql = "SELECT * FROM dormy.record AS r WHERE r.record_id = :record_id";
        Map<String, Object> map = new HashMap<>();
        if(recordId != null){
            map.put("record_id",recordId);
        }
        List<Record> recordList = namedParameterJdbcTemplate.query(sql, map, new RecordRowMapper());

        if(recordList.size()>0){
            //這邊要修改 目前只會返回第一個項目
            return recordList.get(0);
        }else{
            return null ;
        }
    }
    @Override
    public  void updateRecord(Integer recordId,RecordRequest recordRequest){
            if (recordRequest.getBorrow_date() == null){
                String sql = "UPDATE record SET situation = :situation," +
                        "item_id = :item_id," +
                        "user_id = :user_id, contact_person_id = :contact_person_id, violation_type = :violation_type , return_date = :return_date " +
                        " WHERE record_id = :record_id";
                Map<String,Object> map = new HashMap<>();
                map.put("record_id",recordId);
                map.put("situation",recordRequest.getSituation().toString());
                map.put("user_id",recordRequest.getUser_id());
                map.put("contact_person_id",recordRequest.getContact_person_id());
                map.put("violation_type",recordRequest.getViolation_type());
                map.put("item_id",recordRequest.getItem_id());
                map.put("return_date",recordRequest.getReturn_date());
                namedParameterJdbcTemplate.update(sql, map);

            }else{
                String sql = "UPDATE record SET situation = :situation," +
                        "item_id = :item_id," +
                        "user_id = :user_id, contact_person_id = :contact_person_id, violation_type = :violation_type , return_date = :return_date ,borrow_date = :borrow_date" +
                        " WHERE record_id = :record_id";

                Map<String,Object> map = new HashMap<>();
                map.put("record_id",recordId);
                map.put("situation",recordRequest.getSituation().toString());
                map.put("user_id",recordRequest.getUser_id());
                map.put("contact_person_id",recordRequest.getContact_person_id());
                map.put("violation_type",recordRequest.getViolation_type());
                map.put("item_id",recordRequest.getItem_id());
                map.put("return_date",recordRequest.getReturn_date());
                map.put("borrow_date",recordRequest.getBorrow_date());
                namedParameterJdbcTemplate.update(sql, map);

            }


            // 紀錄當下日期
//        Date now = new Date();
//        map.put("purchase_date",now);


        }
    @Override
    public void createBorrowRequest(Item item, User user){
        String sql = "INSERT INTO dormy.record(user_id,item_id,situation,borrow_date,return_date)" +
                "VALUES (:user_id,:item_id,:situation,:borrow_date,:return_date)";

        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user.getUser_id());
        map.put("item_id", item.getItem_id());
        map.put("situation","UN_BORROW");

        map.put("contact_person_id","");

        LocalDate now = LocalDate.now();

        map.put("borrow_date",now);
        LocalDate returnDate = now.plusDays(item.getBorrow_day());


        map.put("return_date",returnDate);
        namedParameterJdbcTemplate.update(sql, map);


    }
    @Override
    public void createReserveRequest(Item item, User user){
        String sql = "INSERT INTO dormy.record(user_id,item_id,situation,borrow_date,return_date)" +
                "VALUES (:user_id,:item_id,:situation,:borrow_date,:return_date)";

        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user.getUser_id());
        map.put("item_id", item.getItem_id());
        map.put("situation","UN_RESERVE");
        map.put("contact_person_id","");
        LocalDate now = LocalDate.now();

        map.put("borrow_date",now);
        LocalDate returnDate = now.plusDays(item.getBorrow_day());
        map.put("return_date",returnDate);
        namedParameterJdbcTemplate.update(sql, map);
    }

}
