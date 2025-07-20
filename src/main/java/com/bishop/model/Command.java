package com.bishop.model;

public class Command {
    private String description; // Описание задачи
    private String priority;    // Приоритет выполнения: "COMMON" или "CRITICAL"
    private String author;      // Кто отправил команду
    private String time;        // Время команды

    public Command() {}

    public Command(String description, String priority, String author, String time) {
        this.description = description;
        this.priority = priority;
        this.author = author;
        this.time = time;
    }

    // Геттеры и сеттеры

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
