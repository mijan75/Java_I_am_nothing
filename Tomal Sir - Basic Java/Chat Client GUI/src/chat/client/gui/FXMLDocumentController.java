/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea chatLogArea;
    @FXML
    private ListView<?> userList;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;
    @FXML
    private Label statusLabel;
    private OutputStream out;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String hostname = "172.17.0.134";
        int portNumber = 9876;
        try {
            Socket socket = new Socket(hostname, portNumber);
            out = socket.getOutputStream();
            String message = "";
            
            String name = "Monirul Hasan";
            out.write(name.getBytes());
            out.flush();
            
            ThreadedClientReader reader = new ThreadedClientReader(socket, this);
            //reader.start();
        } catch (IOException ex) {
            Logger.getLogger(ChatClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void handleSendMessageAction(ActionEvent event) {
        String message = messageField.getText();
        try {
            out.write(message.getBytes());
            messageField.setText("");
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMessage(String message) {
        chatLogArea.appendText(message + "\n");
    }
}
