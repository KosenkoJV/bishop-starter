package com.bishop.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MonitoringController {

    private final CommandService commandService;

    public MonitoringController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        return Map.of(
                "queuedCommands", commandService.getQueueSize(),
                "completedByAuthor", commandService.getAuthorStats()
        );
    }
}
