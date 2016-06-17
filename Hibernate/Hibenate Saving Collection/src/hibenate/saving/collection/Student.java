/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibenate.saving.collection;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

/**
 *
 * @author mijan
 */
@Entity
public class Student {
    
    String name;
    @Id
    String id;
    double cgpa;
    @ElementCollection
    @JoinTable (name = "Address" , joinColumns = @JoinColumn (name = "Id_Of_Student"))
    private Set<Address> list = new HashSet();

    public void setList(Set<Address> list) {
        this.list = list;
    }

    public Set<Address> getList() {
        return list;
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
