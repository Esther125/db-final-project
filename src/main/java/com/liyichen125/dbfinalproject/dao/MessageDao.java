package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;

public interface MessageDao {
    Integer createMessage(MessageRequest messageRequest);
}
