/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasesimpleapp;

/**
 *
 * @author mijan
 */
public class Food {
    
    private int id;
    private String name;
    private String category;
    private String description;
    private double price;

    public Food() {
    }

    public Food(int id, String name, String category, String description, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    
    
}
