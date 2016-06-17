/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.attribute;

import javax.persistence.Embeddable;

/**
 *
 * @author mijan
 * 
 */
@Embeddable
public class Address {
    String home;
    String city;
    String country;

    public Address() {
    }
    
    
    
    public Address(String home, String city, String country) {
        this.home = home;
        this.city = city;
        this.country = country;
    }

    public String getHome() {
        return home;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}