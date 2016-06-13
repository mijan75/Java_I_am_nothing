/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.console.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class SessionFactorySingleton {
    private static final   SessionFactorySingleton instance = new SessionFactorySingleton();
    
    private static SessionFactory factory;
    public SessionFactorySingleton() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getFactory() {
        return factory;
    }
    
    
}
