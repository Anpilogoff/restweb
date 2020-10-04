package com.anpilogoff.model.entity;

import com.anpilogoff.model.dao.Dao;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private String login;
    private String password;
    private String nickname;
    private String email;

    public User(String login, String password, String nickname, String role) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public User(String nickname, String email, String role) {
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    private String role;
    private int onlineStatus;

    public User(String login, String password, String nickname, String email, String role) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public User(String nickname, String role) {
        this.nickname = nickname;
        this.role = role;
    }
}
