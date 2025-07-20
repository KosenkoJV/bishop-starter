package com.bishop.service;

import com.bishop.model.Command;
import com.bishop.audit.WeylandWatchingYou;
import io.micrometer.core.instrument.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.*;

@Service
public class CommandService {

    private final Logger logger = LoggerFactory.getLogger(CommandService.class);

    private final ThreadPoolExecutor executor;
    private final Map<String, Integer> authorStats = new HashMap<>();

    public CommandService(MeterRegistry registry) {
        this.executor = new ThreadPoolExecutor(
                2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20)
        );

        // Публикуем метрику размера очереди
        Gauge.builder("bishop.queue.size", executor, e -> e.getQueue().size())
                .register(registry);
    }

    @WeylandWatchingYou
    public void submit(Command command) {
        //  Выводим всю команду
        logger.info("Получена команда: {}", command);

        // Выявление ошибок

        if (command.getDescription() == null || command.getDescription().trim().isEmpty()) {
            throw new RuntimeException("Описание не может быть пустым");
        }

        if (command.getDescription().length() > 1000) {
            throw new RuntimeException("Описание слишком длинное");
        }


        // Обновляем статистику по авторам
        if (command.getAuthor() != null) {
            authorStats.put(command.getAuthor(),
                    authorStats.getOrDefault(command.getAuthor(), 0) + 1);
        }

        Runnable task = () -> {
            logger.info("Выполняется команда от {}: {}", command.getAuthor(), command.getDescription());
        };

        //Проверка на CRITICAL
        String priority = command.getPriority();
        if (priority != null && priority.trim().equalsIgnoreCase("CRITICAL")) {
            logger.info("[CRITICAL] Команда выполнена немедленно: {}", command.getDescription());
            task.run();
        } else {
            logger.info("[COMMON] Команда добавлена в очередь: {}", command.getDescription());
            executor.execute(task);
        }
    }

    public int getQueueSize() {
        return executor.getQueue().size();
    }

    public Map<String, Integer> getAuthorStats() {
        return new HashMap<>(authorStats);
    }
}
