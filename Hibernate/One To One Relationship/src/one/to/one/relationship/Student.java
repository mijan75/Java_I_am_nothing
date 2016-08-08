/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one.to.one.relationship;


import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    //@OneToOne
    //@OneToMany
    @ManyToMany (mappedBy="student")
    Collection <Address> address = new ArrayList<>();

    public Collection<Address> getAddress() {
        return address;
    }

    public void setAddress(Collection<Address> address) {
        this.address = address;
    }

    

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
    
    
}
