/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField course1Code;
    @FXML
    private TextField course1Grade;
    @FXML
    private TextField course1Credits;
    @FXML
    private TextField course2Code;
    @FXML
    private TextField course2Grade;
    @FXML
    private TextField course2Credits;
    @FXML
    private TextField gpaField;
    @FXML
    private TextField course3Code;
    @FXML
    private TextField course3Grade;
    @FXML
    private TextField course3Credits;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    double getGradePoint(String grade) {
        if (grade.equals("A+"))
            return 4.0;
        else if (grade.equals("A"))
            return 3.75;
        else if (grade.equals("A-"))
            return 3.5;
        else if (grade.equals("B+"))
            return 3.25;
        else return 0.0;
    }
    
    @FXML
    private void handleGPACalculateAction(ActionEvent event) {
        String grade;
        double gradePoint;
        double credits;
        double cumulativeGradePoint = 0.0;
        double totalCredits = 0.0;
        
        grade = course1Grade.getText();
        gradePoint = getGradePoint(grade);
        credits = Double.parseDouble(course1Credits.getText());
        
        cumulativeGradePoint = cumulativeGradePoint + gradePoint * credits;
        totalCredits = totalCredits + credits;

        grade = course2Grade.getText();
        gradePoint = getGradePoint(grade);
        credits = Double.parseDouble(course2Credits.getText());
        
        cumulativeGradePoint = cumulativeGradePoint + gradePoint * credits;
        totalCredits = totalCredits + credits;

        grade = course3Grade.getText();
        gradePoint = getGradePoint(grade);
        credits = Double.parseDouble(course3Credits.getText());
        
        cumulativeGradePoint = cumulativeGradePoint + gradePoint * credits;
        totalCredits = totalCredits + credits;
        
        double gpa = cumulativeGradePoint / totalCredits;
        gpaField.setText("" + gpa);
    }
    
}
