package com.anpilogoff.model.dao;

import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;

import java.sql.Connection;

public interface Dao{
    User registerNewUser(User user);
    Profile registerNewProfile(String nickname,String name, String surname, int age, String gender, String country);
    boolean deleteUser();
    boolean blockUser();

}
