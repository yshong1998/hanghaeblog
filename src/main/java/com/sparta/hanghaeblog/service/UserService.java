package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.LoginRequestDto;
import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.entitiy.User;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public Message signup(SignupRequestDto requestDto){
        User user = new User(requestDto);
        //중복 검사
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        //중복 없으면 회원 가입
        userRepository.save(user);
        return new Message(200,"signup success");
    }

    @Transactional
    public Message login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 아닙니다.")
        );
        if(!password.equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return new Message(200,"login success");
    }
}
