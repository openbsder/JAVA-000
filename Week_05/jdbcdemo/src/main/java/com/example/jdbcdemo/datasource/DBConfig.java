package com.example.jdbcdemo.datasource;

public class DBConfig {
    private static final String DB_NAME = "demo";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo?&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    public String getDbName() {
        return DB_NAME;
    }

    public String getDbUrl() {
        return DB_URL;
    }

    public String getDbUsername() {
        return DB_USERNAME;
    }

    public String getDbPassword() {
        return DB_PASSWORD;
    }
}
