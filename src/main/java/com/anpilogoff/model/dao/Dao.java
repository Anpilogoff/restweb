package com.anpilogoff.model.dao;

import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;
import com.google.gson.JsonArray;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public interface Dao{
    User registerNewUser(User user);
    Profile registerNewProfile(String nickname,String name, String surname, int age, String gender, String country);
    boolean deleteUser();
    boolean blockUser();
    JsonArray loginUser(String login, String password) throws SQLException;
    String uploadPhoto(String nickname, String file_name);
    String getUserAvatar(String nickname);


}
