import Model.Address;
//import Model.DaoPattern.StudentDaoIml;
import Model.Quality;
import Model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public Main() {
        //StudentDaoIml studentDaoIml = new StudentDaoIml();

        // Create a new student in the database
//        Student student = new Student("1","Mijan",3);
//        studentDaoIml.setStudent(student);
        // Delete a student from the database

//        String id = "1";
//        studentDaoIml.deleteStudent(id);

        // Update a student
//        Student student = new Student("2", "Reza", 3.75);
//        studentDaoIml.updateStudent(student);
        // Get cgpa for a particular student
//            String id = "2";
//        System.out.println(studentDaoIml.getCgpa(id));
        // Get a student from database;
//        String id = "3";
//        Student student = studentDaoIml.getStudent(id);
//        System.out.println(student.toString());
        // Get List of Student from the database
//        List<Student> students;
//        students = studentDaoIml.getAllStudent();
//        students.forEach(System.out::println);

        Student student = new Student ();
        Address address = new Address();

        address.setPresentAddress("Dh");
        address.setPermanentAddress("Ragpur");
        Quality quality = Quality.Good;
        address.setQuality(quality);

        student.setStudentId("10");
        student.setStudentName("Mijan");
        student.setCgpa(3.00);
        student.getAddress().add(address);
        address.getStudentList().add(student);


        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);

        List<Student> students = criteria.list();
        students.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args){
        new Main();
    }
}
