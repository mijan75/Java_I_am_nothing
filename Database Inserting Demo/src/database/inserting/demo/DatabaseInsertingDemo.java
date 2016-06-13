/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.inserting.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class DatabaseInsertingDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String DB_URL = "jdbc:mysql://localhost/resturantdb";
        String DB_USER = "root";
        String DB_PASS = "ayesha75";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
//            String query1 = "insert into FoodItem values(66,'Chiken Sizling','Chaines','Hot',350);";
//            statement.executeUpdate(query1);
            String query = "select * from FoodItem";
            ResultSet resultSet = statement.executeQuery(query);
             while(resultSet.next()){
                int itemCode = resultSet.getInt("itemCode");
                String itemName = resultSet.getString("itemName");
                String category = resultSet.getString("categroy");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                System.out.printf("%d %s %s %s %.2f\n", itemCode,itemName,category,description,price);
             }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInsertingDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
