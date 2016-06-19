/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.saving.pkgclass.pkg1.pkg5;

import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class HibernateSavingClass15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student student = new Student("1","Mijanur Rahaman",3.25,new Date(),"Mijan is a good boy");
        
        
        SessionFactory  factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
        
        student = null;
        session = factory.openSession();
        session.beginTransaction();
        student = (Student)session.get(Student.class, "1");
        System.out.println(student.getDescription());
    }
    
}
