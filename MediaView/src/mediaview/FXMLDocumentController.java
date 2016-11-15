/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaview;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private MediaView mediaView;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlePlayAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if(file != null){
        initPlayer(file.toURI().toString());
        }   
    }
    
    public void initPlayer(String uri){
        Media media = new Media(uri);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        if (uri == null)
            return;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
            }
        });
    }
    
}
