package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface RecordDao {
    Integer createRecord(RecordRequest recordRequest);
    List<Record> getRecords(RecordSituation situation, String search);

    Record getRecordById(Integer recordId);
    void updateRecord(Integer recordId,RecordRequest recordRequest);

    void createBorrowRequest(Item item, User user);
    void createReserveRequest(Item item, User user);

}
