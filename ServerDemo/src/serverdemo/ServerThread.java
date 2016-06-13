/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ServerThread extends Thread{
    Socket socket;
    ServerDemo serverDemo;

    public ServerThread(Socket socket,ServerDemo serverDemo) {
        this.socket = socket;
        this.serverDemo = serverDemo;
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run (){
        InputStream in = null;
        
       try {
            in = socket.getInputStream();
            byte [] message = new byte [100];
            SimpleDateFormat formater = new SimpleDateFormat("h:mm a");
            in.read(message);
            
            String name = new String(message).trim();
            serverDemo.broadcast(name+ " loged in",socket);
            while(true)
            {
                
                
                //out = socket.getOutputStream();
                int length = in.read(message);
                
                if (length >=0)
                {
                    Date date = new Date();
                    System.out.printf("%s %s sent : %s\n",formater.format(date),name,new String (message).substring(0, length));
           
                    String outputMessage = String.format("%s %s sent : %s",formater.format(date),name,new String (message).substring(0, length));
                    serverDemo.broadcast(outputMessage,socket);
                }
                else
                {
                    //System.out.printf("%s", socket.getInetAddress().toString());
                    if (socket!=null )
                    {
                        serverDemo.logOut(socket);
                        serverDemo.broadcast(name + " logged out", socket);
                        break;    
                    }
                    
                }
            
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
