/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import javax.persistence.Embeddable;

/**
 *
 * @author mijan
 */
@Embeddable
public class Address {
    private String street;
    private String house ;

    public Address() {
    }

    public Address(String street, String house) {
        this.street = street;
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", house=" + house + '}';
    }
    
    
}
