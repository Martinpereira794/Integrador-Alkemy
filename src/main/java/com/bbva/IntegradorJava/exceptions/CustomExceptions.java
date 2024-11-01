package com.bbva.IntegradorJava.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class CustomExceptions {

	

	    @ExceptionHandler(ExceptionsTP.class)
	    public ResponseEntity<Map<String, Object>> handleExceptionsTP(ExceptionsTP ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now().toString());
	        response.put("error", "Not Found");
	        response.put("status", ex.getStatus().value());
	        response.put("message", ex.getMessage());
	        return ResponseEntity.status(ex.getStatus()).body(response);
	    }
	}




