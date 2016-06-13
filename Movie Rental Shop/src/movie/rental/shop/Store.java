/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.rental.shop;

/**
 *
 * @author mijan
 */
public class Store {
    int id;
    String name;
    String phone;
    String Address;

    public Store(int id, String name, String phone, String Address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.Address = Address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return Address;
    }
    
    
}
