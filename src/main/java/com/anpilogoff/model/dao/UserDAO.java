package com.anpilogoff.model.dao;

import com.anpilogoff.model.connection.ConnectionBuilder;
import com.anpilogoff.model.entity.User;
import org.apache.log4j.Logger;
import sun.java2d.cmm.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** The class implements logic getting from connection pool , as it contains a private variable of the ConnectionBuilder class and a setter for
 its further initialization in init() methods of corresponding servlets
 */
public class UserDAO implements Dao {
    private static final Logger log = Logger.getLogger(UserDAO.class);



    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) { this.connectionBuilder = connectionBuilder; }

    private Connection getPoolConnection() throws SQLException { return connectionBuilder.getPoolConnection(); }

    public synchronized User registerNewUser(String login, String password) {
        String GET_USER_FROM_DB = "SELECT*FROM users WHERE login = " + login + " and password = " + password;

        Connection connection = null;
        User user = null;
            try {
                connection = getPoolConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FROM_DB);
                ResultSet resultSet = preparedStatement.executeQuery();
                //todo:

            } catch (SQLException e) {
                log.error("Exception during connection receiving from ConnectionPool:  "+ e.getCause());
                e.printStackTrace();
            }
            System.out.println(connection);

        return user;
    }




    public Profile registerNewProfile() { return null; }

    public boolean deleteUser() { return false; }

    public boolean blockUser() { return false; }
}
