package com.augustobellinaso.casadocodigo.compartilhado;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponseDto {

    private List<FieldErrorResponseDto> fieldErrors = new ArrayList<>();
    private List<String> globalErrors = new ArrayList<>();

    public void addError(String message) {
        globalErrors.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorResponseDto fieldError = new FieldErrorResponseDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldErrorResponseDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrors.size() + this.fieldErrors.size();
    }
}
