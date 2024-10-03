package com.phyex0.socketmanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/listen")
    @SendTo("/public/chat")
    public String listen(@Payload String message) {
        log.info("Listened message sent to public: " + message);
        return message;
    }

    public void testingAll(String message) {
        messagingTemplate.convertAndSend("/public/chat", message);
        log.info(message + " : sent to public");
    }

    public void testingUnique(String message, String userId) {
        messagingTemplate.convertAndSendToUser(userId, "/chat", message);
        log.info(message + " : sent to private: " + userId);
    }
}
