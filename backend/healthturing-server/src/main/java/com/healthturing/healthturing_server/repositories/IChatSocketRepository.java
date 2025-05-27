package com.healthturing.healthturing_server.repositories;

import java.util.List;

import com.healthturing.healthturing_server.models.ChatMessageModel;

public interface IChatSocketRepository {
    public int save(ChatMessageModel chatMessageModel);
    public List<ChatMessageModel> findByRoomId(String roomId); 
}