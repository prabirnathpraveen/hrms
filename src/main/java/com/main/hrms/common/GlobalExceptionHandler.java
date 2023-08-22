package com.main.hrms.common;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.google.gson.Gson;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("status", HttpStatus.NOT_FOUND.value());
        jsonResponse.put("message", "Resource Not Found: The requested resource does not exist.");
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonResponse);
        
        return new ResponseEntity<>(jsonString, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        jsonResponse.put("message", "An error occurred while processing the request.");
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonResponse);
        
        return new ResponseEntity<>(jsonString, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
