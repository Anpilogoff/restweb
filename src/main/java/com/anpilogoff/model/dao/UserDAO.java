package com.anpilogoff.model.dao;

import com.anpilogoff.model.connection.ConnectionBuilder;
import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;
import com.anpilogoff.model.entity.UserCredentials;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


/** The class implements logic getting from connection pool , as it contains a private variable of the ConnectionBuilder class and a setter for
 its further initialization in init() methods of corresponding servlets
 */
public class UserDAO implements Dao {
    private static final Logger log = Logger.getLogger(UserDAO.class);

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getPoolConnection() {
        return connectionBuilder.getPoolConnection();
    }

    private final Gson gson = new Gson();


    @SneakyThrows
    public synchronized User registerNewUser(User user) {
        String GET_USER_FROM_DB = "SELECT * FROM users WHERE login = " + Character.toString('\'') +user.getLogin() + Character.toString('\'');
        String INSERT_USER_IN_TABLE = "INSERT INTO users(login,password,nickname,email,role) VALUES (?,?,?,?,?)";
        Connection connection = null;

        boolean isEmpty = true;

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
                preparedStatement = connection.prepareStatement(INSERT_USER_IN_TABLE);

                preparedStatement.setString(1,user.getLogin());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.setString(3,user.getNickname());
                preparedStatement.setString(4,user.getEmail());
                preparedStatement.setString(5,user.getRole());

                int isInserted = preparedStatement.executeUpdate();

                connection.commit();

                if(isInserted == 1){
                    System.out.println(isInserted);
                    preparedStatement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            log.error("Exception during connection receiving from ConnectionPool:  " + e.getCause());
            e.printStackTrace();
            return null; //nullable
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
            }
        } catch (SQLException e) {
            log.warn("SQL exception during method \"registerprofile\": " + e.getCause());
            e.printStackTrace();
            if (connection!=null) {
                connection.close();
                return  null;
            }
        }
        Profile profile = new Profile(nickname,name,surname,age,gender,country);
        return profile;
    }

    @Override
    public JsonArray loginUser(String login, String password) throws SQLException {
        String IS_DB_CONTAIN_USER = "SELECT * FROM users where login = " + ('\'') + login + ('\'') +
                " and password = " + Character.toString('\'') + password + Character.toString('\'');
        String IF_USER_CONTAINS_SELECT_PROFILE = "SELECT * FROM profiles WHERE user_nickname = ?";

        User user = null;

        String nickname = null;
        String loginx = null;
        String passwordx = null;
        String email = null;

        String name = null;
        String surname = null;
        int age = 0;
        String gender = null;
        String country = null;
        String role = "user";

       Connection connection = getPoolConnection();

        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(IS_DB_CONTAIN_USER);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                loginx = resultset.getString("login");
                passwordx = resultset.getString("password");
                nickname = resultset.getString("nickname");
                email = resultset.getString("email");
                System.out.println(loginx + " "+ passwordx  + " " + nickname + email );

                user = new User(loginx, passwordx, nickname, email, role);
                user.sayHello();

                System.out.println("user from dao:  " + user);
            }
            if (nickname != null) {
                if (login.equals(loginx) && password.equals(passwordx)) {

                    statement = connection.prepareStatement(IF_USER_CONTAINS_SELECT_PROFILE);
                    statement.setString(1, user.getNickname());
                    ResultSet resultSet = statement.executeQuery();
                    //  if (resultSet != null) {
                    while (resultSet.next()) {
                        name = resultSet.getString("name");
                        surname = resultSet.getString("surname");
                        age = resultSet.getInt("age");
                        gender = resultSet.getString("gender");
                        country = resultSet.getString("country");
                    }
                    statement.close();
                    connection.close();
                    //     }
                } else {
                    return null;
                }
            } else {
                System.out.println(nickname + " " + loginx + " " + passwordx);
                return null;
            }
            }

        Profile profile = new Profile(nickname,name,surname,age,gender,country);
        UserCredentials credentials = new UserCredentials(login,password);


        JsonObject userJson = new JsonObject();

        userJson.addProperty("nickname",user.getNickname());
        userJson.addProperty("role",user.getRole());
        userJson.addProperty("email",user.getEmail());

        System.out.println(userJson.get("nickname") + "  nickname from userJson USERDAO login method");

        JsonObject profileJson = new JsonObject();

        profileJson.addProperty("name", profile.getName());
        profileJson.addProperty("surname",profile.getSurname());
        profileJson.addProperty("age", profile.getAge());
        profileJson.addProperty("gender",profile.getGender());
        profileJson.addProperty("country",profile.getCountry());

        JsonObject credentialsJson = new JsonObject();
        credentialsJson.addProperty("login",user.getLogin());
        //todo: encrypting.....
        credentialsJson.addProperty("password", user.getPassword());
        

        JsonArray array = new JsonArray(3);
        array.add(userJson);
        array.add(profileJson);
        array.add(credentialsJson);

        connection.close();
        return array;
    }


    public String uploadPhoto(String nickname, String file_name) {
        String insertPhoto = "INSERT INTO avatars(user_nickname, file_name) VALUES ('" + nickname + "', '" + file_name + "')";

        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restwebDB?serverTimezone=Europe/Moscow&useSSL=no","root","");
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(insertPhoto);
            int result = statement.executeUpdate();

            if (result == 1) {
                connection.commit();
                statement.close();
                connection.close();
            } else {
                log.warn("A problem occured during inserting avatar-file-name into table 'avatars'");
                return null;
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return file_name;
    }

    @Override
    public String getUserAvatar(String nickname) {
        String getQuery = "SELECT * FROM avatars WHERE user_nickname = '" + nickname +"';";
        Connection connection;
        String file_name = null;
        try{
            connection = getPoolConnection();
            PreparedStatement statement = connection.prepareStatement(getQuery);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet != null){
                while(resultSet.next()){
                    file_name = resultSet.getString("file_name");
                }
                statement.close();
                connection.close();
            }else{
                statement.close();
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return file_name;
    }

    @Override
    public boolean uploadSong(String nickname, String songName) {
        String INSERT_SONG = "INSERT INTO music(user_nickname,song_name) VALUES  (?,?)";

        Connection connection;
        try {
            connection = getPoolConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SONG);
            statement.setString(1,nickname);
            statement.setString(2,songName);
            int isInserted = statement.executeUpdate();

            if (isInserted > 0) {
                connection.commit();
                statement.close();
                connection.close();
                return true;
            } else {
                connection.commit();
                statement.close();
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            System.out.println(e.getCause().toString());
        }
        return false;
    }

    @Override
    public ArrayList<String> getUserSongs(String nickname) {
        String SELECT_SONGS = "SELECT * FROM music WHERE user_nickname = (?)";
        Connection connection;
        ArrayList<String>musicList = new ArrayList<>();
        try {
            connection = getPoolConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_SONGS);
            statement.setString(1,nickname);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                musicList.add(result.getString("song_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
        return null;
    }



    public boolean deleteUser() {
       return false;
    }

    public boolean blockUser() { return false; }

}
