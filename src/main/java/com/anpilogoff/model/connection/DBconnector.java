package com.anpilogoff.model.connection;

import com.anpilogoff.model.dao.Dao;
import org.apache.log4j.Logger;

import java.sql.*;


/** Task of that class is to...create new user with own principals having grants to insert/remove/update/select for
 * data-source connection establishing throug RDBMS "MySQL"
 * ...and to give to current user functionality such as method
 * @see DBconnector#connectDB(String, char) */
public class DBconnector implements Dao {

    private static final Logger log = Logger.getLogger(DBconnector.class);

    /** Variable describes URL to  project data source */
    private final static String DB_HOST = "jdbc:mysql://localhost:3306/restwebDB?serverTimezone=Europe/Moscow&useSSL=no";



    /**
     * Method implements data source connection logic(loading jdbc driver in memory + connection creating) but for it's
     * making it must will be obtain login/password as own arguments values from doPost method in
     * @see com.anpilogoff.controller.servlets.LoginServlet  called him
     * @param login will be insert as argument by servlet calling that method
     * @param password will be insert as argument by servlet calling that method
     * @return opened jdbc connection or null
     */
    public Connection connectDB(String login, char password)  {

        Connection connection = null;
        try {

            connection = DriverManager.getConnection(DB_HOST, login, String.valueOf(password));
        }  catch (SQLException e) {
            log.warn(e.getCause());
            e.printStackTrace();
            return null;
        }
        System.out.println(connection);
        return connection;
    }

    public boolean isExist(String login, char password) {
        String GET_RDBMS_USER = "SELECT*FROM rdbms_users(login,password) VALUES ('login','password')"
                + " WHERE login = " + login + " and password = " + password;
        String GET_USER_FROM_DB = "SELECT*FROM users(login,password) VALUES ('login','password')"
                + " WHERE login = " + login + " and password = " + password;
        String CREATE_RDBMS_USER = ""
        Connection connection = null;
        if (loadDriver()) {
            try {
                if((connection = connectDB(login, password))!=null){
                    return true;
                }else {
                    connection = DriverManager.getConnection(DB_HOST, "root", "root");
                    PreparedStatement isUserExistStatement = connection.prepareStatement(GET_USER_FROM_DB);
                    ResultSet isUserExistResultSet = isUserExistStatement.executeQuery();
                    String loginx = null;
                    String passwordx = null;
                    while (isUserExistResultSet.next()) {
                         loginx = isUserExistResultSet.getString("login");
                         passwordx = isUserExistResultSet.getString("password");
                        System.out.println(loginx + " " + passwordx);
                    }
                    if(loginx.equals(login) && passwordx.equals(password)){
                        //todo:
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } {

            }

        }
    }




    public boolean loadDriver(){
        Object driverClass = null;
        try{
             driverClass = Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }catch (ClassNotFoundException e ){
            log.warn(e.getCause());
            return false;
        }
        return true;
    }


    public void registerNewUser(){}
    public void registerNewProfile(){}
    public void deleteUser(){}
    public void blockUser(){}
}
