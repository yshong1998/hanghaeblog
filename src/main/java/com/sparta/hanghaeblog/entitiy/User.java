package com.sparta.hanghaeblog.entitiy;


import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(SignupRequestDto signupRequestDto){
        username = signupRequestDto.getUsername();
        password = signupRequestDto.getPassword();
    }
}
