package com.phyex0.socketmanager.service;

import com.phyex0.socketmanager.controller.WebSocketController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class Test {
    private final WebSocketController webSocketController;

    private final String HOSTNAME = "HOSTNAME";

    private String getContainer() {
        String containerName = System.getenv(HOSTNAME);
        return Objects.nonNull(containerName) ? containerName : HOSTNAME;
    }

    @Scheduled(fixedRate = 10000L)
    public void testAll() {
        webSocketController.testingAll("This is a public message! ".concat(getContainer()));
    }

    @Scheduled(fixedRate = 10000L)
    public void testSingle() {
        webSocketController.testingUnique("This is a private message! ".concat(getContainer()), "TestUser");
    }
}
