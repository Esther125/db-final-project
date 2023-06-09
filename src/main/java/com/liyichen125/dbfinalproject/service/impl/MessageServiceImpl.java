package com.liyichen125.dbfinalproject.service.impl;

import com.liyichen125.dbfinalproject.dao.MessageDao;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.model.Message;
import com.liyichen125.dbfinalproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public Integer createMessage(MessageRequest messageRequest) {
        return messageDao.createMessage(messageRequest);
    }
    public List<Message> getMessages(){
        return messageDao.getMessages();
    }

    @Override
    public void deleteMessage(Integer messageId) {
        messageDao.deleteMessageById(messageId);
    }
}
