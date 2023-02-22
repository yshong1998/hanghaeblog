package com.sparta.hanghaeblog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    TOKEN_UNAVAILABLE(HttpStatus.BAD_REQUEST, 400, "토큰이 유효하지 않습니다."),
    USER_UNMATCHED(HttpStatus.BAD_REQUEST, 400, "작성자만 삭제/수정할 수 있습니다."),
    USER_NAME_EXIST(HttpStatus.BAD_REQUEST, 400, "중복된 username 입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, 400, "회원을 찾을 수 없습니다."),
    PASSWORD_UNMATCHED(HttpStatus.BAD_REQUEST,400,"회원을 찾을 수 없습니다."),
    SIGNUP_FORM_UNMATCHED(HttpStatus.BAD_REQUEST,400,"회원 가입 양식에 맞지 않습니다."),
    ADMIN_PASSWORD_UNMATCHED(HttpStatus.BAD_REQUEST,400, "관리자 암호가 틀려 등록이 불가합니다."),
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST,400, "게시글이 존재하지 않습니다."),
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST,400, "댓글이 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, Integer errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}