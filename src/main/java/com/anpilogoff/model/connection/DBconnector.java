package com.anpilogoff.model.connection;

import com.anpilogoff.model.dao.Dao;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


/** Task of that class is to...create new user with own principals having grants to insert/remove/update/select for
 * data-source connection establishing throug RDBMS "MySQL"
 * ...and to give to current user functionality such as method
 * @see DBconnector#connectDB(String, char) */
public class DBconnector implements Dao {


    private static final Logger log = Logger.getLogger(DBconnector.class);

    /**
     * Variable describes URL to  project data source
     */
    private final static String DB_HOST = "jdbc:mysql://localhost:3306/restwebDB?serverTimezone=Europe/Moscow&useSSL=no";


    /**
     * Method implements data source connection logic(loading jdbc driver in memory + connection creating) but for it's
     * making it must will be obtain login/password as own arguments values from doPost method in
     *
     * @param login    will be insert as argument by servlet calling that method
     * @param password will be insert as argument by servlet calling that method
     * @return opened jdbc connection or null
     * @see com.anpilogoff.controller.servlets.LoginServlet  called him
     */
    public Connection connectDB(String login, char password) {
        Connection connection = null;
        synchronized (this) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_HOST, login, String.valueOf(password));
            } catch (SQLException e) {
                log.warn(e.getCause());
                e.printStackTrace();
                return connection;
            } catch (ClassNotFoundException e) {
                log.warn(e.getCause());
                e.printStackTrace();
            }
            System.out.println(connection);
            return connection;
        }
    }

    public boolean isExist(String login, String password) {
        String GET_RDBMS_USER = "SELECT*FROM rdbms_users WHERE login = " + login + " and password = " + password;
        String GET_USER_FROM_DB = "SELECT*FROM users WHERE login = " + login + " and password = " + password;
        String CREATE_RDBMS_USER = "INSERT INTO rdbms_users(login,password) ;";

        Connection connection = null;

return false;

    }
    public void registerNewUser() { }
    public void registerNewProfile() { }
    public void deleteUser() { }
    public void blockUser() { }
}

