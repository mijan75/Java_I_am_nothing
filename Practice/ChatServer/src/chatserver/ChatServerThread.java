/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

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
public class ChatServerThread extends Thread{
    Socket socket;
    ChatServer chatServer;

    public ChatServerThread(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run(){
        InputStream in = null;
        try {
            in = socket.getInputStream();
            byte []message = new byte[1000];
            int l = in.read(message);
            
            String name = new String(message).substring(0, l);
            chatServer.broadcast(name + " logged in ", socket);
            SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
            while(true){
                int length = in.read(message);
                if(length >=0){
                    Date date = new Date() ;
                    System.out.printf("%s sent : %s %s\n",name,new String(message).substring(0, length),formatter.format(date));
                    String outputmessage = String.format("%s sent : %s %s\n",name,new String(message).substring(0, length),formatter.format(date));
                    chatServer.broadcast(outputmessage, socket);
                }
                else{
                    if(socket!=null){
                        chatServer.logOut(socket);
                        chatServer.broadcast(name + " logged out", socket);
                        break;   
                    }
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ChatServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
