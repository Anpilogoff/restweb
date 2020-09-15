package com.anpilogoff.model.entity;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserCredentials {
    String login;
    String password;
    String nickname;

    public UserCredentials(String login, String password, String nickname) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

    public UserCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
