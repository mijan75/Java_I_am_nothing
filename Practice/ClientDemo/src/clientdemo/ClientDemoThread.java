/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ClientDemoThread extends Thread{
    Socket socket;

    public ClientDemoThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run(){
        InputStream in = null;
        try {
            in = socket.getInputStream();
            byte[] message = new byte[1000];
            while(true){
                int length = in.read(message);
                if(length<0)
                    break;
                System.out.printf("%s\n",new String(message).trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientDemoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
