/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.collection.congiguration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author mijan
 */
public class HibernateCollectionCongiguration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Student student = new Student ("1","Mijanur Rahaman",3.20);
        Address newaddress = new Address("Rangpur","Rangpur","Rangpur");
        Address newaddress1 = new Address("Dhaka","Dhaka","Dhaka");
        student.getList().add(newaddress);
        student.getList().add(newaddress1);
        
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }
    
}
