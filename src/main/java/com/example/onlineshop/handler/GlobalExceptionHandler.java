package com.example.onlineshop.handler;

import com.example.onlineshop.model.error.ApiError;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,"Resource not found",ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT,"Entity already exist",ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"Validation Errors");
        apiError.addValidationErrors(ex.getFieldErrors());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = RollbackException.class)
    public ResponseEntity<Object> handleRollBackException(){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"Could not commit transaction");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Unhandled exception",ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError error){
        return new ResponseEntity<>(error,error.getHttpStatus());
    }
}
