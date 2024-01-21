package com.example.order.exception;

import com.example.order.exception.CustomException.CustomExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionAdvice {

    @ExceptionHandler({
        CustomException.class
    })

    public ResponseEntity<CustomException.CustomExceptionResponse> exceptionHandler(final CustomException c){
        log.warn("api Exception : {}", c.getErrorCode());
        return ResponseEntity
            .status(c.getStatus())
            .body(CustomExceptionResponse.builder()
                .message(c.getMessage())
                .code(c.getErrorCode().name())
                .status(c.getStatus()).build());
    }
}
