/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class Singleton {
    public static final Singleton instance = new Singleton();
    
    public static  SessionFactory factory;
    
    public  Singleton (){
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getFactory() {
        return factory;
    }
    
    
}
