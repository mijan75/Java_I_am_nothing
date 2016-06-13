/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField lineDisplayField;
    @FXML
    private TextField wordDisplayField;
    @FXML
    private TextField lettersDisplayField;
    @FXML
    private TextField topFiveLetterField;
    @FXML
    private TextField topFivewordfield;
    @FXML
    private TextField withoutSpaceCharField;
    @FXML
    private TextField withSpaceCharField;
    
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleFileBrowseOption(ActionEvent event) {
        FileChooser selectFile = new FileChooser();
        File file = selectFile.showOpenDialog(null);
        try {
            RandomAccessFile input = new RandomAccessFile(file,"r");
            String line;
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int totalChar = 0;
            int charCount = 0;
            int lineCount = 0;
            int spaceCount = 0;
            int wordCount = 0;
            int totalCharr = 0;
            int totalCharWithOutSpace = 0;
            while (true){
                line = input.readLine();
                if (line == null || line.length() == 0){
                    break;
                }
                totalChar =totalChar + line.length();
                int mark1 = 0;
                int mark2 = 0;
                for (i=0;i<line.length();i++){
                    if (i==line.length()-1){
                        lineCount++;
                    }
                }
                for (j=0;j<line.length();j++){
                   if((line.charAt(j)>=65 && line.charAt(j)<=122)){
                       charCount++;
                   }
                } 
                for (k=0;k<line.length();k++){
                    if (line.charAt(k) == ' '){
                        spaceCount++;
                    }
                }
                line = line + ' ';
                for(l=0;l<line.length();l++){
                    if (line.charAt(l) == ' ' || line.charAt(l) == ',' || line.charAt(l) == '.' || line.charAt(l) == '?' || line.charAt(l) == ';'){
                       mark2 = l;
                       String word = line.substring(mark1,mark2);
                       mark1 = mark2 + 1;
                       if (word.length()>0){
                           wordCount++;
                       }
                    }
                }
            }
            totalCharWithOutSpace = totalChar -spaceCount;
            totalChar = totalChar + (lineCount-1);
            wordDisplayField.setText(wordCount + "");
            withSpaceCharField.setText(totalChar+"");
            withoutSpaceCharField.setText(totalCharWithOutSpace+"");
            lettersDisplayField.setText(charCount+"");
            lineDisplayField.setText(lineCount+"") ;
        }catch(FileNotFoundException fnfe){
            System.err.println("File Not Found\n");
        }catch(IOException ioe){
            System.err.println("Somethig Is Wrong\n");
        }

    }
    
}
