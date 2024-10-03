package com.phyex0.socketmanagerscalable.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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

    private RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/listen")
    @SendTo("/public/chat")
    public String triggerServos(@Payload String message) {
        log.info("Listened message sent to public: " + message);
        return "Succedd!";
    }

    public void testingAll(String message) {
        redisTemplate.convertAndSend("publicMessage", message);
        log.info(message + " : sent to public");
    }

    public void testingUnique(String message, String userId) {
        redisTemplate.convertAndSend("privateMessage", message);
        log.info(message + " : sent to private: " + userId);
    }
}
