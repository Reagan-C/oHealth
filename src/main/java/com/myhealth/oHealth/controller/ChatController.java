package com.myhealth.oHealth.controller;

import com.myhealth.oHealth.model.domain.ChatMessage;
import com.myhealth.oHealth.model.domain.ChatRoom;
import com.myhealth.oHealth.repository.ChatMessageRepository;
import com.myhealth.oHealth.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    private final ChatMessageRepository chatMessageRepository;


    public ChatController(ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat/{roomId}/send")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage handleChatMessage(@DestinationVariable String roomId,
                                         ChatMessage message) {
        message.setChatRoom((ChatRoom) chatRoomRepository.findById(Long.parseLong(roomId)).orElseThrow(
                () -> new IllegalArgumentException("")
        ));
        message.setSentat(LocalDateTime.now());
        chatMessageRepository.save(message);
        return message;
    }

}

