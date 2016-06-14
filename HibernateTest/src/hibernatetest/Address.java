/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mijan
 */
@Embeddable
public class Address {
    @Column (name = "Home")
    private String home;
    @Column (name = "Street")
    private String street;
    @Column (name = "PostalCode")
    private String postalCode;
    @Column (name = "Pin_Code")
    private String pinCode;

    public Address() {
    }

    public Address(String home, String street, String postalCode, String pinCode) {
        this.home = home;
        this.street = street;
        this.postalCode = postalCode;
        this.pinCode = pinCode;
    }
    
    

    public String getHome() {
        return home;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
    
}
