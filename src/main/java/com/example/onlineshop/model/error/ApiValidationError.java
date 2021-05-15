package com.example.onlineshop.model.error;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiSubError {

    private final String object;
    private final String field;
    private final Object rejectedValue;
    private final String message;
}
