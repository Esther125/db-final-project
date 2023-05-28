package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.RecordDao;
import com.liyichen125.dbfinalproject.dto.RecordRequest;
import com.liyichen125.dbfinalproject.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Transactional //修改多個table時確保數據一致
    @Override
    public Integer createRecord(Integer user_id, RecordRequest recordRequest) {
        return user_id;
    }
}
