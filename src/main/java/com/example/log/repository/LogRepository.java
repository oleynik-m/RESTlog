package com.example.log.repository;

import com.example.log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogRepository extends JpaRepository<Log,Long> {
    Optional<Log> findByMessage (String message);
}
