package com.anpilogoff.model.entity;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserCredentials {
    String login;
    String password;

    public UserCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
