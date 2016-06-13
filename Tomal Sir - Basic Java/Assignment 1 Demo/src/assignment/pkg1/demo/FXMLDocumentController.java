/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            RandomAccessFile input = new RandomAccessFile(file, "r");
            String line;

            /*
            Important/useful methods of String class for this assignment:
            length()
            charAt()
            toLowerCase()/toUpperCase()
            substring()
            */
            int aCount = 0;
            ArrayList<String> words = new ArrayList<>();
            while (true) {
                line = input.readLine();
                if (line == null || line.length() == 0)
                    break;
                line = line.toLowerCase();
                int marker1 = 0;
                int marker2 = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ' || line.charAt(i) == ',') {
                        marker2 = i;
                        String word = line.substring(marker1, marker2);
                        boolean found = false;
                        for (int j = 0; j < words.size(); j++)
                            if (words.get(j).equals(word))
                                found = true;
                        if (!found)
                            words.add(word);
                        //System.err.printf("Found word: [%s]\n", word);
                        marker1 = marker2 + 1;
                    }
                }
            }
            label.setText("# of as " + aCount);
            System.out.printf("# of as %d\n", aCount);
            for (int i = 0; i < words.size(); i++)
                System.out.printf("[%s]\n", words.get(i));
        } catch (FileNotFoundException fnfe) {
            System.err.printf("Could not locate the file\n");
        } catch (IOException ioe) {
            System.err.printf("Some error occured\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
