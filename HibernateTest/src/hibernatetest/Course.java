/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mijan
 */
@Entity
public class Course {
    @Id
    String course;
    String faculty;

    public Course() {
    }

    public Course(String course, String faculty) {
        this.course = course;
        this.faculty = faculty;
    }

    public String getCourse() {
        return course;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Course{" + "course=" + course + ", faculty=" + faculty + '}';
    }
    
    
}
