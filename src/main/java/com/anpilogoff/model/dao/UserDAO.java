package com.anpilogoff.model.dao;

import com.anpilogoff.model.connection.ConnectionBuilder;
import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;
import com.mysql.cj.protocol.Resultset;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.json.JSONObject;

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

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder)
    { this.connectionBuilder = connectionBuilder; }

    private Connection getPoolConnection() throws SQLException
    { return connectionBuilder.getPoolConnection(); }

    @SneakyThrows
    public synchronized User registerNewUser(User user) {
        String GET_USER_FROM_DB = "SELECT * FROM users WHERE login = " + Character.toString('\'') +user.getLogin() + Character.toString('\'');
        String INSERT_USER_IN_TABLE = "INSERT INTO users(login,password,nickname,email,role) VALUES (?,?,?,?,?)";
        System.out.println(GET_USER_FROM_DB);
        Connection connection = null;
        boolean isEmpty = true;
        System.out.println(isEmpty);

        try {
            connection = getPoolConnection();
            log.info("Connection was  created succesfully:  " + connection);
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FROM_DB);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isEmpty = false;
                return null;
            }
            if (isEmpty) {
                System.out.println("resulset is null");
                preparedStatement = connection.prepareStatement(INSERT_USER_IN_TABLE);

                preparedStatement.setString(1,user.getLogin());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.setString(3,user.getNickname());
                preparedStatement.setString(4,user.getEmail());
                preparedStatement.setString(5,"user");

                int isInserted = preparedStatement.executeUpdate();

                connection.commit();

                if(isInserted == 1){
                    preparedStatement.close();
                    connection.close();

                    user.setLogin(user.getLogin());
                    user.setPassword(user.getPassword());
                    user.setNickname(user.getNickname());
                    user.setEmail(user.getEmail());
                    System.out.println(user+ " user");
                    System.out.println("return user: userdao");
                  //  return user;
                }
            }
        } catch (SQLException e) {
            log.error("Exception during connection receiving from ConnectionPool:  " + e.getCause());
            e.printStackTrace();
            return user; //nullable
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("in finally block :  "  + connection.isClosed() + " connection closed?");
            }
        }
        System.out.println(connection + "before class return");
        return user;
    }



    @SneakyThrows
    public Profile registerNewProfile(String nickname, String name, String surname, int age, String gender, String country) {
        String REGISTER_NEW_PROFILE = "INSERT INTO profiles(user_nickname,name,surname,age,gender,country) VALUES (?,?,?,?,?,?)";
        Connection connection = null;

        try{
            connection = getPoolConnection();
            PreparedStatement statement = connection.prepareStatement(REGISTER_NEW_PROFILE);
            statement.setString(1,nickname);
            statement.setString(2,name);
            statement.setString(3,surname);
            statement.setInt(4,age);
            statement.setString(5,gender);
            statement.setString(6,country);

            int isInserted= statement.executeUpdate();
            if(isInserted==1){
                statement.close();
                connection.commit();
                connection.close();
                System.out.println("secondIsinserted "+isInserted );
            }
        } catch (SQLException e) {
            log.warn("SQL exception during method \"registerprofile\": " + e.getCause());
            e.printStackTrace();
            if (connection!=null) {
                connection.close();
            }
            return  null;
        }
        return new Profile(nickname,name,surname,age,gender,country);
    }

    @Override
    public boolean loginUser() {
        return false;
    }

    public boolean deleteUser() { return false; }

    public boolean blockUser() { return false; }

}
