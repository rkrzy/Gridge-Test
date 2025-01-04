package com.example.demo.src.admin.model;


import com.example.demo.src.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRes {
    private String name;
    private String email;
    private String password;
    private Long id;
    private boolean isOauth;
    private LocalDateTime lastLogin;
    public UserDetailRes(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();
        this.isOauth = user.isOAuth();
        this.lastLogin = user.getLastLogin();
    }
}
