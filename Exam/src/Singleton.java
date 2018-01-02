import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Singleton {
    private static Singleton instance = new Singleton();
    Connection connection ;

    private Singleton(){
        connection = DriverManager.getConnection("","","");
    }

    public static Connection getConnection(){
        return coonection;
    }
}
