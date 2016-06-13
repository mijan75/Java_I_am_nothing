/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mijan;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mijan
 */
@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private double cgpa;
    private String address;

    public Student() {
    }

    public Student(int id, String name, double cgpa, String address) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Studnet{" + "id=" + id + ", name=" + name + ", cgpa=" + cgpa + ", address=" + address + '}';
    }
    
    
}
