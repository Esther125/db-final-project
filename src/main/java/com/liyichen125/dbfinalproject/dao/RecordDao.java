package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.RecordRequest;

public interface RecordDao {
    Integer createRecord(Integer user_id, RecordRequest recordRequest);
}
