package com.brunoqueiroz.transferencias.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoApplicableTaxException.class)
    public ResponseEntity<String> handleNoApplicableTax(
            NoApplicableTaxException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

}