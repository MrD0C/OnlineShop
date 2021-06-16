package com.example.onlineshop.service.apierror;

import com.example.onlineshop.server.apierror.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import java.time.LocalDateTime;

@Service
public class ApiErrorService {

    private final ApiValidationErrorService validationErrorService;

    @Autowired
    public ApiErrorService(ApiValidationErrorService apiValidationErrorUtil) {
        this.validationErrorService = apiValidationErrorUtil;
    }

    public ApiError getApiError(HttpMessageNotReadableException exception){
        return ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("HTTP message not readable")
                .debugMessage(exception.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiError getApiError(ResourceNotFoundException exception){
         return ApiError.builder()
                 .httpStatus(HttpStatus.NOT_FOUND)
                 .message("Resource not found")
                 .debugMessage(exception.getLocalizedMessage())
                 .timestamp(LocalDateTime.now())
                 .build();
    }

    public ApiError getApiError(EntityExistsException exception){
        return ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("Entity already exist")
                .debugMessage(exception.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiError getApiError(MethodArgumentNotValidException exception){
        return ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("Entity already exist")
                .debugMessage(exception.getLocalizedMessage())
                .apiSubErrors(this.validationErrorService.addValidationErrors(exception.getFieldErrors()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiError getApiError(RollbackException exception){
        return ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Could not commit transaction")
                .debugMessage(exception.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ApiError getApiError(Exception exception){
        return ApiError.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Unhandled exception")
                .debugMessage(exception.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
