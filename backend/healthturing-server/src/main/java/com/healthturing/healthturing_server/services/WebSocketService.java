package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.ChatMessage;
import com.healthturing.healthturing_server.models.ChatMessageModel;
import com.healthturing.healthturing_server.repositories.IChatSocketRepository;

@Service
public class WebSocketService {

    @Autowired
    private IChatSocketRepository chatSocketRepository;

    public void saveMessage(ChatMessage message, String roomId) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setUserName(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        chatMessageModel.setRoomId(roomId);
        chatSocketRepository.save(chatMessageModel);
    }

    public List<ChatMessageModel> getAllChatMessages(String roomId) {
        return chatSocketRepository.findByRoomId(roomId);
    }


}
