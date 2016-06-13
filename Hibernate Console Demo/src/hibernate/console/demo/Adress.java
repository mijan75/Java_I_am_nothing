/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.console.demo;

import javax.persistence.Embeddable;

/**
 *
 * @author mijan
 */
@Embeddable
public class Adress {
    String house;
    String street;
    String city;

    public Adress() {
    }

    public Adress(String house, String street, String city) {
        this.house = house;
        this.street = street;
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Adress{" + "house=" + house + ", street=" + street + ", city=" + city + '}';
    }
    
    
}
