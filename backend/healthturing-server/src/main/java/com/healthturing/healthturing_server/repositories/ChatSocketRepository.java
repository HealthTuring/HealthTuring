package com.healthturing.healthturing_server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.ChatMessageModel;

@Repository
public class ChatSocketRepository implements IChatSocketRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(ChatMessageModel chatMessageModel) {
        String SQL = "INSERT INTO chats (user_name, message, room_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(SQL, new Object[] {
                chatMessageModel.getUserName(),
                chatMessageModel.getMessage(),
                chatMessageModel.getRoomId()
        });
    }

    @Override
    public List<ChatMessageModel> findByRoomId(String roomId) {
        String SQL = "SELECT * FROM chats WHERE room_id = ?";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(
                ChatMessageModel.class), roomId);
    }

}
