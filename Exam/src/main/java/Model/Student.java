package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @Column(name = "id")
    private String studentId;
    @Column(name = "name")
    private String studentName;
    @Column(name = "cgpa")
    private double cgpa;
    @ManyToMany(mappedBy = "studentList")
    private List<Address> address;

    public Student() {
        address = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", cgpa=" + cgpa +
                ", address=" + address +
                '}';
    }
}
