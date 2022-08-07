package com.example.log.service;

import com.example.log.model.Log;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface LogService {
    void save (Log log);
    void delete (Log log);
    List<Log> getAll (Example<Log> logExample);
    Optional<Log> getByMessage (String message);
    Optional<Log> getById (Long id);
}
