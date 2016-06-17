/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mijan
 */
@Entity
public class Student {
    @Id
    String name;
    String id;
    double cgpa;
    @Embedded
    @AttributeOverrides ({
    @AttributeOverride (name = "home", column = @Column (name = "Homee")),
    @AttributeOverride (name = "city", column = @Column (name = "Citye")),
    @AttributeOverride (name = "country", column = @Column (name = "Countrye")),})
    Address homeaddress;
    @Embedded
    Address officeaddress;
    

    public Student() {
    }

    public Student(String name, String id, double cgpa) {
        this.name = name;
        this.id = id;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
    
    public Address getHomeaddress() {
        return homeaddress;
    }

    public Address getOfficeaddress() {
        return officeaddress;
    }

    public void setHomeaddress(Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    public void setOfficeaddress(Address officeaddress) {
        this.officeaddress = officeaddress;
    }
    
    
}