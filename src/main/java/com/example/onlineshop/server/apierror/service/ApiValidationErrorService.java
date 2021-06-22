package com.example.onlineshop.server.apierror.service;

import com.example.onlineshop.server.apierror.ApiSubError;
import com.example.onlineshop.server.apierror.ApiValidationError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<ApiSubError> addValidationErrors(Set<ConstraintViolation<?>> set){
        List<ApiSubError> apiValidationErrorList = new ArrayList<>();
        for (ConstraintViolation<?> error:set){
            apiValidationErrorList.add(addValidationError(error));
        }
        return apiValidationErrorList;
    }

    private ApiValidationError addValidationError(ConstraintViolation<?> cv) {
        return ApiValidationError.builder()
                .object(cv.getRootBeanClass().getSimpleName())
                .field(((PathImpl) cv.getPropertyPath()).getLeafNode().asString())
                .rejectedValue(cv.getInvalidValue())
                .message(cv.getMessage())
                .build();
    }
}
