package com.phyex0.socketmanager.configuration;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    private final String PUBLIC = "/topic/ekmek";

    private final String PRIVATE = "/user/TestUser/chat";

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(message.getChannel(), StandardCharsets.UTF_8);
        String dest;
        if (topic.equals("messageQueue")) {
            dest = PUBLIC;
        } else {
            dest = PRIVATE;
        }

        messagingTemplate.convertAndSend(dest, new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
