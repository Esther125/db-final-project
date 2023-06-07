package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.rowmapper.ItemRowMapper;
import com.liyichen125.dbfinalproject.rowmapper.RecordRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RecordDaoImpl implements RecordDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

        Date now = new Date();
        map.put("borrow_date",now);
        map.put("return_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int record_id = keyHolder.getKey().intValue();

        return record_id;
    }
    @Override
    public List<Record> getRecords(RecordSituation situation, String search) {
        String sql = "SELECT r.record_id,i.item_id,r.situation,r.contact_person_id,r.borrow_date,return_date,r.violation_type,u.user_id, i.item_name FROM dormy.record AS r LEFT JOIN dormy.item AS i ON r.item_id = i.item_id  LEFT JOIN dormy.user AS u ON r.user_id = u.user_id  WHERE 1=1";

        Map<String, Object> map = new HashMap<>();


        if(situation != null){
            sql = sql + " AND situation = :situation";
            map.put("situation",situation.name());
        }

        //模糊查詢(關鍵字搜尋)
        if(search != null){
            //百分比(%)不可以直接寫在SQL裡面，要寫在map的值裡面(jdbc template規定)
            sql = sql + " AND i.item_name LIKE :search";
            map.put("search","%" + search + "%");
        }

        List<Record> recordList = namedParameterJdbcTemplate.query(sql,map,new RecordRowMapper());
        return recordList;
    }
}
