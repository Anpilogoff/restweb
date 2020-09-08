package com.anpilogoff.model.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface gives opportunity implements by
 * @see DBconnector class to receive Connection from Connection Pool
 */
public interface ConnectionBuilder {
    Connection getPoolConnection() throws SQLException;
}
