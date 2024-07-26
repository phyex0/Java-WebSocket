package com.phyex0.socketmanager.controller;

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
    @SendTo("/topic/ekmek")
    public String triggerServos(@Payload String s) {
        log.info("Triggering all servos: " + s);
        return "Succedd!";
    }

    public void testingAll(String s) {
        redisTemplate.convertAndSend("messageQueue", s);
        log.info(s + " : sent to client");
    }

    public void testingUnique(String s, String id) {
        log.info(s + " sent to id: " + id);
        redisTemplate.convertAndSend("messageQueue2", s);

    }
}
