package com.sparta.hanghaeblog.Dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "4자리 이상 10자리 이하의 소문자와 숫자로 구성되어야 합니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "8자리 이상 15자리 이하의 소문자와 대문자와 숫자로 구성되어야 합니다.")
    private String password;
}
