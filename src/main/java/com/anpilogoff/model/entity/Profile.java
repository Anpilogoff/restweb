package com.anpilogoff.model.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Profile {
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String country;

    public Profile(String name, String surname, int age, String gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    public Profile(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
