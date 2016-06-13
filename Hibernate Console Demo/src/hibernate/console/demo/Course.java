/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.console.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mijan
 */
@Entity
public class Course {
    @Id
    String courseId;
    String courseName;
    int credit;

    public Course() {
    }

    public Course(String courseId, String courseName, int credit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", courseName=" + courseName + ", credit=" + credit + '}';
    }
    
    
}
