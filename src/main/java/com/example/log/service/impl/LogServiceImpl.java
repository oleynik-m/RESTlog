package com.example.log.service.impl;

import com.example.log.model.Log;
import com.example.log.repository.LogRepository;
import com.example.log.service.LogService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    public LogServiceImpl (LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @Override
    public void save(Log log) {
        this.logRepository.save(log);
    }

    @Override
    public void delete(Log log) {
        this.logRepository.delete(log);
    }

    @Override
    public List<Log> getAll(Example<Log> logExample) {
        return this.logRepository.findAll(logExample);
    }

    @Override
    public Optional<Log> getByMessage(String message) {
        return this.logRepository.findByMessage(message);
    }

    @Override
    public Optional<Log> getById(Long id) {
        return this.logRepository.findById(id);
    }
}
