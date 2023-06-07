package com.liyichen125.dbfinalproject.rowmapper;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordRowMapper implements RowMapper<Record> {
    @Override
    public Record mapRow(ResultSet resultSet, int i) throws SQLException {
        Record record = new Record();

        record.setRecord_id(resultSet.getInt("record_id"));

        // 重要

        // String 和 ENUM之間的轉換


        String situationsStr = resultSet.getString("situation");
        RecordSituation situation =RecordSituation.valueOf(situationsStr);
        record.setSituation(situation);


        //簡潔寫法
        //item.setType(ItemType.valueOf(resultSet.getString("category")));

        record.setItem_id(resultSet.getInt("item_id"));
        record.setBorrow_date(resultSet.getDate("borrow_date"));
        record.setUser_id(resultSet.getInt("user_id"));
        record.setViolation_type(resultSet.getString("violation_type"));
        record.setReturn_date(resultSet.getDate("return_date"));
        return record;

    }
}
