package com.example.log.controller;


import com.example.log.exceptions.LogNotFoundException;
import com.example.log.model.Log;
import com.example.log.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/log")
public class LogController {

    static final Logger logger =
            LoggerFactory.getLogger(LogController.class);

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Created")
    void create(@RequestBody Log newLog) throws JsonProcessingException {
        logger.info(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(newLog));
        this.logService.save(newLog);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Updated")
    void update(@RequestBody Log newLog,@PathVariable Long id) throws JsonProcessingException {
        Log log = this.logService.getById(id).orElseThrow(() -> new LogNotFoundException(String.format("Log id %d not found",id)));
        logger.info(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(newLog));
        newLog.setId(log.getId());
        this.logService.save(newLog);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Deleted")
    void delete (@PathVariable Long id) {
        Log log = this.logService.getById(id).orElseThrow(() -> new LogNotFoundException(String.format("Log id %d not found",id)));
        logger.info(log.toString());
        this.logService.delete(log);
    }

    @GetMapping("/id/{id}")
    Log getById(@PathVariable Long id) {
        return this.logService.getById(id).orElseThrow(() -> new LogNotFoundException(String.format("Log id %d not found",id)));
    }

    @GetMapping()
    List<Log> getAll(@ParameterObject Log log) {
        return this.logService.getAll(Example.of(log));
    }
}
