package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.MessageDao;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Message;
import com.liyichen125.dbfinalproject.rowmapper.ItemRowMapper;
import com.liyichen125.dbfinalproject.rowmapper.MessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageDaoImpl implements MessageDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createMessage(MessageRequest messageRequest) {
        String sql = "INSERT INTO message(user_id,content,m_time)" +
                " VALUES (:user_id,:content,:m_time)";

        Map<String, Object> map = new HashMap<>();
        map.put("user_id",messageRequest.getUser_id());
        map.put("content",messageRequest.getContent());

        Date now = new Date();
        map.put("m_time",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();


        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int message_id = keyHolder.getKey().intValue();

        return message_id;
    }

    @Override
    public List<Message> getMessages() {
        String sql = "SELECT * FROM message WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        List<Message> messageList = namedParameterJdbcTemplate.query(sql,map,new MessageRowMapper());
        return messageList;
    }

    @Override
    public void deleteMessageById(Integer messageId) {
        String sql = "DELETE FROM message WHERE message_id = :message_id";

        Map<String, Object> map = new HashMap<>();
        map.put("message_id",messageId);
        namedParameterJdbcTemplate.update(sql,map);
    }
}
