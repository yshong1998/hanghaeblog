package com.sparta.hanghaeblog.exception;

import com.sparta.hanghaeblog.entitiy.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodUnValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        System.out.println("발생한 에러는 " + ex + " 이고 URI는 " + requestURI + " 입니다.");
        return new ResponseEntity<>(ex.getBody(),ex.getHeaders(),ex.getStatusCode());
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Message> handleRestApiException(RestApiException ex, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        System.out.println("발생한 에러는 " + ex + " 이고 URI는 " + requestURI + " 입니다.");
        return ResponseEntity.badRequest().body(new Message(ex.getErrorCode(),ex.getErrorMessage()));
    }
}
