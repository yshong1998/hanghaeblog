package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.LoginRequestDto;
import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.entitiy.User;
import com.sparta.hanghaeblog.entitiy.UserRoleEnum;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "yshong1998Token";

    @Transactional
    public ResponseEntity<Message> signup(SignupRequestDto requestDto) {
        User user = new User(requestDto);
        if(requestDto.getAdminToken().equals(ADMIN_TOKEN)){
            user.setRole(UserRoleEnum.ADMIN);
        }
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "signup success"));
    }

    @Transactional
    public ResponseEntity<Message> login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 아닙니다.")
        );
        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "login success"));
    }
}
