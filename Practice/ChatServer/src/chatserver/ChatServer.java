/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ChatServer {
    private ArrayList <Socket>socketList;
    public ChatServer() {
        socketList = new ArrayList<>();
        int portNumber = 9876;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            while(true){
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                System.out.printf("Server is connected with %s\n", socket.getInetAddress());
                ChatServerThread chatServerThread = new ChatServerThread(socket,this);
                chatServerThread.start();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatServer();
    }
    
    public void broadcast(String message , Socket socket){
        System.out.println("Broadcast message " + message);
        
        for(int i=0; i<socketList.size(); i++){
            Socket s = socketList.get(i);
            if(s.equals(socket))
                continue;
            
            OutputStream out=null;
            try {
                out = s.getOutputStream();
                out.write(message.getBytes());
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void logOut(Socket socket){
        for(int i=0; i<socketList.size(); i++){
            Socket s = socketList.get(i);
            if(s.equals(socket))
                socketList.remove(s);
        }
    }
    
}
