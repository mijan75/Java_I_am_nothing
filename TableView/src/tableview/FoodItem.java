/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

/**
 *
 * @author mijan
 */
public class FoodItem {
    int itemCode;
    String itemName;
    String category;
    String description;
    double price;

    public int getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
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

    public FoodItem(int itemCode, String itemName, String category, String description, double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "FoodItem{" + "itemCode=" + itemCode + ", itemName=" + itemName + ", category=" + category + ", description=" + description + ", price=" + price + '}';
    }
    
    
}
