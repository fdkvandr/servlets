package com.http.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    public static final String URL_KEY = "db.url";
    public static final String USER_KEY = "db.user";
    public static final String PASSWORD_KEY = "db.password";
    public static final String DRIVER_KEY = "db.driver";

    static {
        loadDriver();
    }

    private ConnectionManager() {
    }

    public static Connection get() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(
                    PropertiesUtil.get(DRIVER_KEY));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
