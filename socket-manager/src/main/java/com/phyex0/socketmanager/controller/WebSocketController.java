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
    @SendTo("/topic/ekmek")
    public String triggerServos(@Payload String s) {
        log.info("triggered: " + s);
        return s;
    }

    public void testingAll(String s) {
        messagingTemplate.convertAndSend("/topic/ekmek", s);
        log.info(s + " : sent to client");
    }

    public void testingUnique(String s, String id) {
        messagingTemplate.convertAndSendToUser(id, "/chat", s);
        log.info(s + " : sent to user: " + id);
    }
}
