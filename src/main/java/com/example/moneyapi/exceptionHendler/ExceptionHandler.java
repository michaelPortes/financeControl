package com.example.moneyapi.exceptionHendler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String developmentMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> errors = List.of(new Error(userMessage, developmentMessage));
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Error> errors = ErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> ErrorList(BindingResult bindingResult){
        List<Error> errors = new ArrayList<>();


        for(FieldError fieldError : bindingResult.getFieldErrors()){
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developmentMessage = fieldError.toString();
            errors.add(new Error(userMessage, developmentMessage));
        }
        return errors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)

    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request ){
        String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
        String developmentMessage = ex.toString();
        List<Error> errors = List.of(new Error(userMessage, developmentMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @Getter
    public static class Error {
        private String userMessage;
        private String developmentMessage;

        public Error(String userMessage, String developmentMessage){
            this.userMessage = userMessage;
            this.developmentMessage = developmentMessage;
        }
    }
}
