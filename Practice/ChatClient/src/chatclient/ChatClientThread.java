/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ChatClientThread extends Thread{
    Socket socket ;

    public ChatClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            InputStream in = socket.getInputStream();
            byte[] message = new byte[1000];
            while(true){
                int length = in.read(message);
                if(length<0)
                    break;
                System.out.printf("%s",new String(message).substring(0, length));
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
