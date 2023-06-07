package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.model.Record;

import java.util.List;

public interface RecordService {
    Integer createRecord(RecordRequest recordRequest);
    List<Record> getRecords(RecordSituation situation , String search);
}
