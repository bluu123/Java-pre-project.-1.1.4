package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.TimeZone;


public class Util {
    // Connect to MySQL
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return DriverManager.getConnection(connectionURL, userName,
                password);

    }
}
