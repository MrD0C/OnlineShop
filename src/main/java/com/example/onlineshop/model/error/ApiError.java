package com.example.onlineshop.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String debugMessage;
    private List<ApiSubError> apiSubErrors;

    private ApiError(){
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

    private void addSubError(ApiSubError subError) {
        if (this.apiSubErrors == null) {
            this.apiSubErrors = new ArrayList<>();
        }
        this.apiSubErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    public void addValidationError(InvalidFormatException exception){
        DateTimeParseException parseException = (DateTimeParseException) exception.getCause();
        this.addValidationError(
                LocalDateTime.class.toString(),
                "",
                parseException.getParsedString(),
                parseException.getMessage()
        );
    }

    public void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    public void addValidationErrors(List<FieldError> list){
        list.forEach(this::addValidationError);
    }

    private void addValidationError(FieldError fieldError){
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }
}
