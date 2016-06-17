/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.collection.congiguration;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author mijan
 */
@Entity
public class Student {
    @Id
    String id;
    String name;
    double cgpa;
    @ElementCollection
    @GenericGenerator (name = "hilo-gen",strategy = "hilo")
    @CollectionId (columns ={@Column (name = "Mijan_Id")},generator = "hilo-gen",type = @Type (type = "long"))
    private Collection<Address> list = new ArrayList<Address>();

    public void setList(Collection<Address> list) {
        this.list = list;
    }

    public Collection<Address> getList() {
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
