package Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    private String presentAddress;
    private String permanentAddress;
    @Enumerated(EnumType.STRING)
    private Quality quality;
    @ManyToMany
    @JoinTable(name = "Anything", joinColumns ={@JoinColumn(name = "addressId")},
            inverseJoinColumns = {@JoinColumn(name ="studentId")})
    private List<Student> studentList;

    public Address() {
        studentList = new ArrayList<>();
    }

    public Address(String presentAddress, String permanentAddress, Quality quality, List<Student> studentList) {
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.quality = quality;
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Address{" +
                "presentAddress='" + presentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", quality=" + quality +
                '}';
    }
}
