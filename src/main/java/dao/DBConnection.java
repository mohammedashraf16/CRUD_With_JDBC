package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3307;
    private static final String DB_NAME = "jdbc_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection(){
        // jdbc:mysql://localhost:3307/jdbc_db
        try{
            connection= DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DB_NAME),USER_NAME,PASSWORD);
        }
        catch (SQLException se){
            se.fillInStackTrace();
        }
        return connection;
    }
}
