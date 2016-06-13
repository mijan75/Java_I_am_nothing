/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.demo.gui;

/**
 *
 * @author kmhasan
 */
public class Distributor {
    private int id;
    private String name;
    private String address;
    private String phone;

    public Distributor(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    
    @Override
    public String toString() {
        return id + " - " +  name;
    }
}
