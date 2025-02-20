package com.augustobellinaso.casadocodigo.compartilhado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException m) {
        List<ObjectError> globalErrors = m.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = m.getBindingResult().getFieldErrors();

        return buildValidationErrorsResponse(globalErrors, fieldErrors);
    }

    private ValidationErrorResponseDto buildValidationErrorsResponse(List<ObjectError> globalErrors,
                                                            List<FieldError> fieldErrors) {
        ValidationErrorResponseDto responseDto = new ValidationErrorResponseDto();

        globalErrors.forEach(error -> responseDto.addError(getErrorMessage(error)));

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            responseDto.addFieldError(error.getField(), errorMessage);
        });
        return responseDto;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
