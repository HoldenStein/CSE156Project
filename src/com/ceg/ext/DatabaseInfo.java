package com.ceg.ext;

import java.sql.*;
public class DatabaseInfo
{
    public static final String url = "jbdc:mysql//cse.unl.edu/hsteinhauser";
    public static final String username = "hsteinhauser";
    public static final String password = "EX4:bs";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jbdc.Driver").newInstance();

        } catch(InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch(IllegalAccessException e) {
            System.out.println("IllegalAccessException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
        } catch(SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return conn;
    }
}
