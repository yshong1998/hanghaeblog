package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.Dto.LoginRequestDto;
import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "signup success"));
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        userService.login(requestDto, response);
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "login success"));
    }
}
