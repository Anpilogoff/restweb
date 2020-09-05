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
    private Character password;
    private String nickname;
    private String role;
    private String email;
    private int onlineStatus;


    public User(String login, Character password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, Character password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, Character password, String nickname, String email) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
