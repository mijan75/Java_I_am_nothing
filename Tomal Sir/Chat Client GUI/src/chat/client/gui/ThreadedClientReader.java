/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author kmhasan
 */
public class ThreadedClientReader {

    private Socket socket;
    private FXMLDocumentController controller;
    private InputStream in;
    private byte[] messageBytes;
    public ThreadedClientReader(Socket socket, FXMLDocumentController controller) {
        this.socket = socket;
        this.controller = controller;

        in = null;
        try {
            in = socket.getInputStream();
            messageBytes = new byte[1000];

            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(10),
                    event -> {
                        while (true) {
                            try {
                                int length = in.read(messageBytes);
                                if (length < 0) {
                                    break;
                                }
                                String message = new String(messageBytes).substring(0, length);
                                //System.out.printf("Server sent [%s]\n", message);
                                controller.addMessage(message);
                            } catch (IOException ex) {
                                Logger.getLogger(ThreadedClientReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.playFromStart();
        } catch (IOException ex) {
            Logger.getLogger(ThreadedClientReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
