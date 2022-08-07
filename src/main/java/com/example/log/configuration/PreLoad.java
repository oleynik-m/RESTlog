package com.example.log.configuration;

import com.example.log.controller.LogController;
import com.example.log.model.Level;
import com.example.log.model.Log;
import com.example.log.model.Type;
import com.example.log.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class PreLoad {
    static final Logger logger =
            LoggerFactory.getLogger(PreLoad.class);

    @Bean
    CommandLineRunner initDatabase(LogRepository logRepository) {
        return args -> {
            try {
                List<Log> data = Arrays.asList(
                        new Log("Error message", Type.JSON, Level.SEVERE, LocalDateTime.now()),
                        new Log("Warning message", Type.XML, Level.WARNING, LocalDateTime.now()),
                        new Log("Text message", Type.TEXT,Level.INFO, LocalDateTime.now())
                );
                logRepository.saveAll(data);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        };
    }
}
