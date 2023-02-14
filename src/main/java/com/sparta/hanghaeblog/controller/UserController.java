package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.Dto.LoginRequestDto;
import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Message signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public Message login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        return userService.login(requestDto, response);
    }
}