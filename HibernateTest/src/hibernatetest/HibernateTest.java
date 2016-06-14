/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class HibernateTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Student student = new Student (1,"Mijanur Rahaman",3.25);
        Address address1 = new Address("Home","Home","Home","Home");
        Address address2 = new Address("Office","Office","Office","Office");
        
        student.setHomeAddress(address1);
        student.setOfficeAddress(address2);
        
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
        
        
    }
    
}
