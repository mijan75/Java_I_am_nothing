package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletongDesign {
    private static SingletongDesign instance = new SingletongDesign();
    private Connection connection = null;

    private SingletongDesign() {
        final String DB_URL = "jdbc:mysql://localhost/test";
        final String DB_USR = "root";
        final String DB_PASS = "ayesha75";

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SingletongDesign getInstance(){
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
