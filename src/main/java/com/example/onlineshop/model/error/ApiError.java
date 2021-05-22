package com.example.onlineshop.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String debugMessage;
    private List<ApiSubError> apiSubErrors;

    private ApiError(){
        this.apiSubErrors = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
        this.debugMessage = "";
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.httpStatus = status;
        this.message = message;
    }

    public ApiError(HttpStatus status,String message,Throwable ex){
        this(status,message);
        this.debugMessage = ex.getLocalizedMessage();
    }

    public void addValidationError(InvalidFormatException exception){
        DateTimeParseException parseException = (DateTimeParseException) exception.getCause();
        ApiValidationError validationError = ApiValidationError.builder()
                .message(parseException.getLocalizedMessage())
                .rejectedValue(parseException.getParsedString())
                .field("")
                .object(LocalDateTime.class.toString())
                .build();
        this.apiSubErrors.add(validationError);
    }

    public void addValidationError(FieldError fieldError){
        ApiValidationError validationError = ApiValidationError.builder()
                .field(fieldError.getField())
                .object(fieldError.getObjectName())
                .rejectedValue(fieldError.getRejectedValue())
                .message(fieldError.getDefaultMessage())
                .build();
        this.apiSubErrors.add(validationError);
    }

    public void addValidationErrors(List<FieldError> list){
        list.forEach(this::addValidationError);
    }
}
