/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.console.demo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author mijan
 */
@Entity
public class Student {
    @Id
    int id;
    String name;
    double cgpa;
    String address;
    @Embedded
    Adress addres;
    @OneToOne
    Course course;

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
        return "Student{" + "id=" + id + ", name=" + name + ", cgpa=" + cgpa + ", address=" + address + '}';
    }
    
    
}
