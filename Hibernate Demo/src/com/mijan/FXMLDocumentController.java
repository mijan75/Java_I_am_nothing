/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mijan;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField cgpaField;
    @FXML
    private TextArea addressField;
    private SessionFactory sessionFactory;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     sessionFactory =  new Configuration().configure().buildSessionFactory();
    }    

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        double cgpa = Double.parseDouble(cgpaField.getText());
        String address = addressField.getText();
        
        Student student = new Student(id ,name,cgpa,address);
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        
    }

    @FXML
    private void hadleGetAction(ActionEvent event) {
        Session session = sessionFactory.openSession();
        List <Student> list = session.createCriteria(Student.class).list();
        ArrayList <Student> aList = new ArrayList<>();
        for (Student student : list)
           aList.add(student);
        
        for (int i = 0; i< aList.size();i++)
          System.out.printf("%s\n",aList.get(i).getAddress());
    }
    
}
