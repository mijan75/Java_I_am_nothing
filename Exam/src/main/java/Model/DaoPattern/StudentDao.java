package Model.DaoPattern;

import Model.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> getAllStudent();
    public Student getStudent(String id);
    public double getCgpa(String id);
    public void setStudent(Student student);
    public void deleteStudent(String id);
    public void updateStudent(Student student);
}
