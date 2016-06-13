/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.console.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class HibernateConsoleDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Student student = new Student(2014,"Mijan",3.5,"Mirpur 14");
        
       
        Session session = null;
       try {
           session = SessionFactorySingleton.getFactory().openSession();
           
       }catch (Exception e){
          session.getTransaction().rollback();
       }finally {
           session.close();
       }
  
    }
    
}
