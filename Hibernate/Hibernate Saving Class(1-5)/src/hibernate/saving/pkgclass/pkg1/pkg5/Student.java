/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.saving.pkgclass.pkg1.pkg5;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author mijan
 * 
 */
@Entity (name = "Student_Details")
//@Table (name = "Student_Details")
public class Student {
    @Id //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    String id;
    //@Basic
    @Column (name = "Name")
    String name;
    //@Transient
    @Column (name = "CGPA")
    double cgpa;
    @Temporal (TemporalType.DATE)
    Date date;
    @Lob
    String description;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Student() {
    }

    public Student(String id, String name, double cgpa, Date date, String description) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    //@Column ( name= "Name")
    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
    
    
}
