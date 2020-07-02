package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider instance = new DBConnectionProvider();

    private Connection connection;

    private String dbDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(dbDriver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadProperties() throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream("C:\\Users\\sky\\IdeaProjects\\Web\\TaskManagm\\src\\main\\resources\\DBConfig.properties"));
        dbUrl = properties.getProperty("db.url");
        dbDriver = properties.getProperty("db.driver");
        dbUsername = properties.getProperty("db.username");
        dbPassword = properties.getProperty("db.password");
    }

    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
//
//    private static DBConnectionProvider instance = new DBConnectionProvider();
//
//    private Connection connection;
//
//    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    private final String DB_URL = "jdbc:mysql://localhost:3306/tast_manager?useUnicode=true&characterEncoding=utf8" +
//            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private final String USER_NAME = "root";
//    private final String PASSWORD = "root";
//
//    private DBConnectionProvider() {
//        try {
//            Class.forName(DRIVER_NAME);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//    public static DBConnectionProvider getInstance() {
//        return instance;
//    }
//
//    public Connection getConnection() {
//        try {
//            if (connection == null || connection.isClosed()) {
//                connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        return connection;
//    }
}
