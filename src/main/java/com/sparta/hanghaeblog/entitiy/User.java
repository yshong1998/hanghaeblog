package com.sparta.hanghaeblog.entitiy;


import com.sparta.hanghaeblog.Dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany
    List<Post> userPosts = new ArrayList<>();

    public User(SignupRequestDto signupRequestDto){
        username = signupRequestDto.getUsername();
        password = signupRequestDto.getPassword();
        role = UserRoleEnum.USER;
    }

    public void setRole(UserRoleEnum role){
        this.role = role;
    }
}
