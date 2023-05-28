package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RecordDaoImpl implements RecordDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createRecord(Integer user_id, RecordRequest recordRequest) {
        String sql = "INSERT INTO record(user_id,item_id,situation,borrow_date,return_date)" +
                "VALUES (:user_id,:item_id,:situation,:borrow_date,:return_date)";

        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("item_id", recordRequest.getItem_id());
        map.put("situation", recordRequest.getSituation());

        Date now = new Date();
        map.put("borrow_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int record_id = keyHolder.getKey().intValue();

        return record_id;
    }
}
