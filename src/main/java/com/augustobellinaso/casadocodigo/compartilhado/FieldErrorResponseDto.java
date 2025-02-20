package com.augustobellinaso.casadocodigo.compartilhado;

public class FieldErrorResponseDto {

    private String field;
    private String message;

    FieldErrorResponseDto() {}

    public FieldErrorResponseDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
