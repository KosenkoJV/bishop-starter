package com.bishop.controller;

import com.bishop.model.Command;
import com.bishop.service.CommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST-контроллер
@RestController
@RequestMapping("/commands") //localhost:8080/commands
public class CommandController {

    private final CommandService service;

    public CommandController(CommandService service) {
        this.service = service;
    }

    // Обработка POST-запроса
    @PostMapping
    public ResponseEntity<String> submit(@RequestBody Command command) {
        try {
            service.submit(command); // Передаём команду в сервис
            return ResponseEntity.accepted().body("Принято");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
