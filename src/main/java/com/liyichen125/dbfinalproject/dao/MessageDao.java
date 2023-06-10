package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.Message;

import java.util.List;

public interface MessageDao {
    Integer createMessage(MessageRequest messageRequest);
    List<Message> getMessages();

    void deleteMessageById(Integer messageId);
}
