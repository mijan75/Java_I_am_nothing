/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ServerTheread extends Thread {
    private Socket socket;

    public ServerTheread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        InputStream in = null;
        try {
            in = socket.getInputStream();
            byte []message = new byte[1000];
            in.read(message);
            String name = new String(message).trim(); 
            while(true)
            {
                int length = in.read(message);
                if (length>=0)
                {
                    System.out.printf("%s %s\n",name,new String(message).substring(0, length));
                    String message2 ;
                    message2 = new String (message).substring(0,length);
                    OutputStream out = socket.getOutputStream();
                    out.write(message2.getBytes());
                    out.flush();
                }
                else {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerTheread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerTheread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
