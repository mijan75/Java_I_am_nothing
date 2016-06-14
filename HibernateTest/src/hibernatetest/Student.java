/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mijan
 */
@Entity 
@Table (name = "Student_Details")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double cgpa;
    @Embedded
    @AttributeOverrides ({
        @AttributeOverride (name = "home" , column = @Column (name = "Home_Home_Address")),
        @AttributeOverride (name = "street" , column = @Column (name = "Home_Street_Address")),
        @AttributeOverride (name = "postalCode" , column = @Column (name = "Home_Postal_Code_Address")),
        @AttributeOverride (name = "pinCode" , column = @Column (name = "Home_Pin_Code_Address"))
    })
    private Address homeAddress;
    @Embedded
    private Address officeAddress;

    public Student() {
    }

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
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

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }
    
    
    
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", cgpa=" + cgpa + '}';
    }
    
    
}
