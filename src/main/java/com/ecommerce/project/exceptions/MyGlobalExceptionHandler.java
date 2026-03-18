package com.ecommerce.project.exceptions;

import com.ecommerce.project.payLoad.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e  ){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err)->{
           String fieldName=((FieldError)err).getField();
           String message=err.getDefaultMessage();
           response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> myHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Map<String, String> response = new HashMap<>();
        Throwable rootCause = e.getMostSpecificCause();
        String message = rootCause != null ? rootCause.getMessage() : e.getMessage();
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResorceNotFoundException.class)
    public ResponseEntity<ApiResponse> myResourceNotFoundException(ResorceNotFoundException e){
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> myApiException(ApiException e){
        String message=e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
