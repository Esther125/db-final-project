package com.liyichen125.dbfinalproject.dao.impl;

import com.liyichen125.dbfinalproject.dao.MessageDao;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
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
}
