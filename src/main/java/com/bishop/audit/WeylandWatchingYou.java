package com.bishop.audit;

import java.lang.annotation.*;

// Аннотация
@Retention(RetentionPolicy.RUNTIME)        // Доступна во время выполнения
@Target(ElementType.METHOD)                // Применяется к методам
public @interface WeylandWatchingYou {
    // Пока без параметров, но можно добавить
}
