package com.tutorials.msexceladapter.handler;

import com.tutorials.msexceladapter.exception.ExcelAdapterException;
import com.tutorials.msexceladapter.model.ErrorResponseModel;
import io.jsonwebtoken.JwtException;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handle(Exception exception) {
        log.error("Exception thrown:", exception);
        if (exception instanceof MethodArgumentNotValidException ex) {
            var fieldError = Objects.requireNonNull(ex.getBindingResult().getFieldError());
            var message = String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
            return generateErrorResponse(message, 9001);
        } else if (exception instanceof ExcelAdapterException ex) {
            var message = Objects.requireNonNull(ex.getMessage());
            return generateErrorResponse(message, 7001);
        } else if (exception instanceof JwtException ex) {
            var message = Objects.requireNonNull(ex.getMessage());
            return generateErrorResponse(message, 8001);
        }
        return generateErrorResponse(exception.getMessage(), 9999);
    }

    private ResponseEntity<ErrorResponseModel> generateErrorResponse(String message, int code) {
        return new ResponseEntity<>(
                ErrorResponseModel.builder().message(message).code(code).build(),
                HttpStatus.BAD_REQUEST);
    }
}
