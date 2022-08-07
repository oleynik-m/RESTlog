package com.example.log.exceptions;

import com.example.log.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LogControllerAdvice {
    @ResponseBody
    @ExceptionHandler(LogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<Response> logNotFoundHandler(LogNotFoundException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
