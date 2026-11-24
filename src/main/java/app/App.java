package app;

import dao.DBConnection;

import java.sql.Connection;

public class App {
    static void main() {
        Connection con =DBConnection.getConnection();
        if (con ==null){
            System.out.println("Connection Failed!");
        }
        else {
            System.out.println("Connection Success");
        }
    }
}
