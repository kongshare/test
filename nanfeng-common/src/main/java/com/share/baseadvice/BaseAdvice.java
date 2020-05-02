package com.share.baseadvice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class BaseAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity exceptionHandle(RuntimeException e) {
        return ResponseEntity.ok("404");
    }



}
