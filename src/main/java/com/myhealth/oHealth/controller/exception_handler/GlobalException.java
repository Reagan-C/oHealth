package com.myhealth.oHealth.controller.exception_handler;

import com.myhealth.oHealth.model.exceptions.ArticleIDExistsException;
import com.myhealth.oHealth.model.exceptions.ArticleIDNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> validation(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<String, Object>();
        Map<String, Object> errorMap = new HashMap<String, Object>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errorMap.put(fieldName, errorMessage);
            }else if(error != null){
                String objectName = ((ObjectError) error).getObjectName();
                String errorMessage = error.getDefaultMessage();
                errorMap.put(objectName, errorMessage);
            }
        });
        errors.put("message", errorMap);
        errors.put("error-type", "Data Validation");
        return errors;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ArticleIDNotFoundException.class)
    public Object notFound(ArticleIDNotFoundException ex) {
        final Map<String, Object> errors = new HashMap<String, Object>();
        errors.put("entityName", ArticleIDNotFoundException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ArticleIDExistsException.class)
    public Object exists(ArticleIDExistsException ex) {
        final Map<String, Object> errors = new HashMap<String, Object>();
        errors.put("entityName", ArticleIDExistsException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

}
