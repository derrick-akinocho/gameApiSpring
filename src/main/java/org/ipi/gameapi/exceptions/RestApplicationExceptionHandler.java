package org.ipi.gameapi.exceptions;

import org.ipi.gameapi.models.ErrorValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ResponseBody
@ControllerAdvice
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders httpHeaders, HttpStatus status, WebRequest request){

        List<ErrorValidation> errorValidations = new ArrayList<>();
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for(FieldError error : fieldErrors){
            errorValidations.add(new ErrorValidation(error.getField(),error.getDefaultMessage()));
        }
        return this.handleExceptionInternal(e, errorValidations, httpHeaders, status, request);
    }
}
