package com.bishop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Общий обработчик исключений
@ControllerAdvice
public class GlobalExceptionHandler {

    // Если случается любая ошибка типа RuntimeException — перехватываем здесь
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Ошибка: " + ex.getMessage());
    }
}
