/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ServerDemoThread extends Thread{
    Socket socket ;
    ServerDemo serverDemo;

    public ServerDemoThread(Socket socket, ServerDemo serverDemo) {
        this.socket = socket;
        this.serverDemo = serverDemo;
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
            SimpleDateFormat  formatter = new SimpleDateFormat("h:mm a");
            in.read(message);
            String name = new String(message).trim();
            serverDemo.broadcast(name + " is online",socket);
            while(true){
               int length = in.read(message);
               if(length>0){
                    Date data = new Date();
                    System.out.printf("%s sent : %s %s\n",name,new String(message).substring(0, length),formatter.format(data));
                    String outputmessage = String.format("%s sent : %s %s",name,new String(message).trim(),formatter.format(data));
                    serverDemo.broadcast(outputmessage,socket);
               }
               else{
                   if(socket != null){
                       serverDemo.logOut(socket);
                       serverDemo.broadcast(name + " is offline",socket);
                       break;
                   }
               }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerDemoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
