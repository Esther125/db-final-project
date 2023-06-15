package com.liyichen125.dbfinalproject.service;

import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.model.Message;

import java.util.List;

public interface MessageService {
    Integer createMessage(MessageRequest messageRequest);
    List<Message> getMessages();

    void deleteMessage(Integer messageId);
}
