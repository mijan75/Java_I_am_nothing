/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class DatabaseConnectivity {

    public DatabaseConnectivity() {
        String DB_URL = "jdbc:mysql://localhost/Mijan";
        String DB_USER = "root";
        String DB_PASS = "ayesha75";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String Query = "select * from Table1";
            ResultSet resultSet = statement.executeQuery(Query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balence = resultSet.getDouble("balance");
                
                Student student = new Student(id, name , balence);
                System.out.println(student.getId());
                System.out.println(student.getName());
                System.out.println(student.getBalance());
            }                
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new DatabaseConnectivity();
    }
    
}
