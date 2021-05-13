package com.example.onlineshop.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
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

    public ApiError(HttpStatus status,String message,Throwable ex){
        this();
        this.httpStatus = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }


}
