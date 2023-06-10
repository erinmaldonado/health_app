package com.myapp;

import databases.DBConnection;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("hello world");
        System.out.println("HIIIII");

        DBConnection connection = new DBConnection();
        connection.getConnection();
    }
}
