package dev.cleysonph.gestaovagas.exceptions;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> new ErrorMessageDTO(error.getField(), error.getDefaultMessage()))
            .toList();
        return ResponseEntity.badRequest().body(errors);
    }
    
}
