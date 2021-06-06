package com.example.onlineshop.handler;

import com.example.onlineshop.service.apierror.error.ApiError;
import com.example.onlineshop.service.apierror.ApiErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ApiErrorService apiErrorService;

    @Autowired
    public GlobalExceptionHandler(ApiErrorService apiErrorService) {
        this.apiErrorService = apiErrorService;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = RollbackException.class)
    public ResponseEntity<Object> handleRollBackException(RollbackException ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex){
        return buildResponseEntity(apiErrorService.getApiError(ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError error){
        return new ResponseEntity<>(error,error.getHttpStatus());
    }
}
