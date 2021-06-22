package com.example.onlineshop.server.handler;

import com.example.onlineshop.server.apierror.ApiError;
import com.example.onlineshop.server.apierror.service.ApiErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ApiErrorService apiErrorService;

    @Autowired
    public GlobalExceptionHandler(ApiErrorService apiErrorService) {
        this.apiErrorService = apiErrorService;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = RollbackException.class)
    public ResponseEntity<Object> handleRollBackException(RollbackException ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex){
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        return buildResponseEntity(this.apiErrorService.getApiError(ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
