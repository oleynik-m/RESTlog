package com.example.log.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Log {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime time ;

    public Log () {

    }

    public Log(String message, Type type, Level level) {
        this.message = message;
        this.type = type;
        this.level = level;
    }
    public Log(String message, Type type, Level level, LocalDateTime time) {
        this.message = message;
        this.type = type;
        this.level = level;
        this.time = time;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "message='" + message + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", time=" + time +
                '}';
    }
}
