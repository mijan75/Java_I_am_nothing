/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salespoint;

/**
 *
 * @author mijan
 */
public class POS {
    private String productName;
    private int productId;
    private int productQuantity;
    
    public POS(String name,int id,int quantity){
        productName=name;
        productId=id;
        productQuantity=quantity;
    }
    
    public String getproductName(){
        return productName;
    }
    
    public int getproductId(){
        return productId;
    }
    
    public int getproductQuantity(){
        return productQuantity;
    }
    public void print(){
        System.out.printf("%s %d %d\n",productName,productId,productQuantity);
    }
}

