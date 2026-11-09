package com.qveto.exam.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DatabaseConfig {
    private static HikariDataSource dataSource;

    private DatabaseConfig(){}

    public static synchronized DataSource getDataSource() {
        if(dataSource == null) {
            Properties props = loadProperties();
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.maxSize", "10")));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static Properties loadProperties() {
        Properties props = new Properties();
        try(InputStream in = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if(in == null) throw new IllegalStateException("Missing db.properties in classpath. Copy db.properties.template to db.properties.");
            props.load(in);
            return props;
        } catch(IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }
}
