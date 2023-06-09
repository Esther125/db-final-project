package com.liyichen125.dbfinalproject.rowmapper;

import com.liyichen125.dbfinalproject.constant.RecordSituation;
import com.liyichen125.dbfinalproject.model.Message;
import com.liyichen125.dbfinalproject.model.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setMessage_id(resultSet.getInt("message_id"));
        message.setUser_id(resultSet.getInt("user_id"));
        message.setContent(resultSet.getString("content"));
        message.setM_time(resultSet.getDate("m_time"));
        return message;

    }
}
