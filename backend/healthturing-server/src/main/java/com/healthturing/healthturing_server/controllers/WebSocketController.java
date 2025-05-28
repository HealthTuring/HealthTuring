package com.healthturing.healthturing_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthturing.healthturing_server.dto.ChatMessage;
import com.healthturing.healthturing_server.models.ChatMessageModel;
import com.healthturing.healthturing_server.services.WebSocketService;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println("Received message in room " + roomId + ": " + message.getMessage());
        this.webSocketService.saveMessage(message, roomId);
        return new ChatMessage(message.getMessage(), message.getUser());
    }

    @GetMapping("/api/chat/{roomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ChatMessageModel>> getAllChatMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(this.webSocketService.getAllChatMessages(roomId));
    }
    
}
