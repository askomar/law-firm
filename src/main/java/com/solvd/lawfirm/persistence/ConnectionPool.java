package com.solvd.lawfirm.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static volatile ConnectionPool connectionPool;
    private static volatile List<Connection> availableConnections;

    private ConnectionPool() {
        try {
            Class.forName(Config.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find Driver class");
        }
    }

    public static ConnectionPool getInstance() {
        int poolSize = Integer.parseInt(Config.POOL_SIZE.getValue());
        return getInstance(poolSize);
    }

    public static ConnectionPool getInstance(Integer initialPoolSize) {
        if (connectionPool == null && initialPoolSize != null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    availableConnections = new ArrayList<>(initialPoolSize);
                    IntStream.range(0, initialPoolSize)
                            .boxed()
                            .forEach(index -> availableConnections.add(createConnection()));
                }
            }
        }
        return connectionPool;
    }

    private static Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Config.URL.getValue(), Config.USERNAME.getValue(), Config.PASSWORD.getValue());
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find Driver class" + e);
        }
        return connection;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (availableConnections.size() <= 0) {
        }
        connection = availableConnections.remove(0);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (ConnectionPool.class) {
                if (availableConnections.contains(connection)) {
                    throw new RuntimeException("Try to release a non-existing connection");
                }
                availableConnections.add(connection);
            }
        } else {
            throw new RuntimeException("Connection param in #releaseConnection() is null");
        }
    }
}
