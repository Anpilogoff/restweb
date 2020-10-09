package com.anpilogoff.model.connection;

import com.anpilogoff.model.dao.Dao;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.function.DoubleToIntFunction;


/**
 The task of this class is to implement the logic of obtaining a connection from the connection pool and return it back
 when the interaction with the database was completed.
 */
public class DBconnector implements ConnectionBuilder {
    /** Just logger for logging. */
    private static final Logger log = Logger.getLogger(DBconnector.class);


    /**
     * Interface variable which Object after initializing will typically be
     *  registered with a naming service based on JNDI API and represents physically data source
     *  and have basically Connection Pooling implementation.
     */
    private DataSource dataSource;



    /**
     * Constructor implements InitialContext initialization. All JNDI operations executed relative to context, so
     * InitialContext is a start point for naming and catalogue operations. After InitContext receiving, it can be use
     * for objects and another context finding. Current method use it to find and init Data Source - server context part
     */
    public DBconnector() {
        try{
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/MyLocalDB");
        } catch (NamingException e) {
            log.warn("Data Source lookup exception:  " + e.getCause());

        }
    }

    /**
     * Realize connection establishing-method of
     * @see DataSource interface
     * @return object of
     * @see Connection class which represents connection to DataSource
     * @throws SQLException in a case of login-timeout exceeding.
     */
    public Connection getPoolConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.warn("SQL Exception during connection receiving:  "+e.getCause());
            e.printStackTrace();
        }
        System.out.println(connection);
        try {
            System.out.println(connection.getClientInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


