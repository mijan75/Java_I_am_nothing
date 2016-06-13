/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.io.demo;

/**
 *
 * @author kmhasan
 */
public class Product {
    private int productId;
    private String productName;
    private String productCategory;
    
    Product(int id, String name, String category) {
        productId = id;
        productName = name;
        productCategory = category;
    }
    
    int getProductId() {
        return productId;
    }
    
    String getProductName() {
        return productName;
    }
    
    void setProductName(String name) {
        productName = name;
    }
}
