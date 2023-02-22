package com.sparta.hanghaeblog.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException extends RuntimeException{
    private Integer errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public RestApiException(ErrorCode errorCode){
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }
}