package com.example.onlineshop.service.apierror;

import com.example.onlineshop.service.apierror.error.ApiSubError;
import com.example.onlineshop.service.apierror.error.ApiValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
class ApiValidationErrorService {

    public List<ApiSubError> addValidationErrors(List<FieldError> list){
        List<ApiSubError> apiValidationErrorList = new ArrayList<>();
        for (FieldError error:list){
            apiValidationErrorList.add(addValidationError(error));
        }
        return apiValidationErrorList;
    }

    private ApiValidationError addValidationError(FieldError fieldError){
        return ApiValidationError.builder()
                .object(fieldError.getObjectName())
                .field(fieldError.getField())
                .rejectedValue(fieldError.getRejectedValue())
                .message(fieldError.getDefaultMessage())
                .build();
    }
}
