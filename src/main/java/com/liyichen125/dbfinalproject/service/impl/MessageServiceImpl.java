package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.ItemDao;
import com.liyichen125.dbfinalproject.dao.MessageDao;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public Integer createMessage(MessageRequest messageRequest) {
        return messageDao.createMessage(messageRequest);
    }
}
