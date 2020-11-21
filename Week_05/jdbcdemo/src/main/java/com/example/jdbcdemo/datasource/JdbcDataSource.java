package com.example.jdbcdemo.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JdbcDataSource extends MysqlDataSource {

    public JdbcDataSource() {
        super();
        DBConfig dbConfig = new DBConfig();
        this.databaseName = dbConfig.getDbName();
        this.url = dbConfig.getDbUrl();
        this.user = dbConfig.getDbUsername();
        this.password = dbConfig.getDbPassword();
    }
}
