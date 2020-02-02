package com.sbt.javaschool.rnd.lesson21.db.school.connection;

import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class SchoolConnection{
    private static final PGConnectionPoolDataSource dataSource;
    private static PooledConnection pooledConnection;

    private static final String DATABASE_NAME = "school";
    private static final String SCHEMA = "public";
    private static final String SERVER_NAME = "localhost";
    private static final int PORT = 5432;
    private static final String USER = "postgres";
    private static final String PASSWORD = "LhuoNV95";

    static {
        dataSource = new PGConnectionPoolDataSource();

        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setCurrentSchema(SCHEMA);
        dataSource.setServerNames(new String[] {SERVER_NAME});
        dataSource.setPortNumbers(new int[] {PORT});
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        try {
            pooledConnection = dataSource.getPooledConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = pooledConnection.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
