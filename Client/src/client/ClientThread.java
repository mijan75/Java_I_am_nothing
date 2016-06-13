/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ClientThread extends Thread{
    private Socket sokcet;

    public ClientThread(Socket sokcet) {
        this.sokcet = sokcet;
    }
    @Override
    public void run (){
        while(true)
        {
            InputStream in = null;
            try {
                in = sokcet.getInputStream();
                byte []message2=new byte[1000];
                
                while(true){
                int length = in.read(message2);
                if (length<0)
                   break;
                System.out.printf("%s\n",new String(message2).substring(0, length));
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
}
