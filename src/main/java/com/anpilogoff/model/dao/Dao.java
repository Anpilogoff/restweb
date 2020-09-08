package com.anpilogoff.model.dao;

import com.anpilogoff.model.entity.User;
import sun.java2d.cmm.Profile;

import java.sql.Connection;

public interface Dao{
    User registerNewUser(String login, String password);
    Profile registerNewProfile();
    boolean deleteUser();
    boolean blockUser();

}
