package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Record;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface RecordDao {
    Integer createRecord(RecordRequest recordRequest);
    List<Record> getRecords(RecordSituation situation, String search);

    Record getRecordById(Integer recordId);
    void updateRecord(Integer recordId,RecordRequest recordRequest);
}
