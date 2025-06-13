package com.healthturing.healthturing_server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.ChatMessage;
import com.healthturing.healthturing_server.models.ChatMessageModel;
import com.healthturing.healthturing_server.repositories.IChatSocketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final IChatSocketRepository chatSocketRepository;

    /**
     * Guarda en la base de datos un mensaje, con la roomId y el usuario que lo envió.
     * @param message
     * @param roomId
     */
    public void saveMessage(ChatMessage message, String roomId) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setUserName(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        chatMessageModel.setRoomId(roomId);
        chatSocketRepository.save(chatMessageModel);
    }

    /**
     * Recupera todos los mensajes de una roomId.
     * @param roomId
     * @return List<ChatMessageModel>
     */
    public List<ChatMessageModel> getAllChatMessages(String roomId) {
        return chatSocketRepository.findByRoomId(roomId);
    }


}
