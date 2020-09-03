package com.anpilogoff.model.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserCredentials {
    private String login;
    private String password;
    private String nickname;
    private String role;

    public UserCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
