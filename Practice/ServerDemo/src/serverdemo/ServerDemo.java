/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverdemo;

import java.io.IOException;
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
public class ServerDemo {
    private ArrayList<Socket> socketList;
    public ServerDemo() {
        socketList = new ArrayList<>();
        int portNubmer = 9876;
        
        try {
            ServerSocket serverSocket = new ServerSocket(portNubmer);
            while(true){
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                System.out.printf("Server is connected with : %s\n",socket.getInetAddress());
                ServerDemoThread serverDemoThread = new ServerDemoThread(socket, this);
                serverDemoThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ServerDemo();
    }
    
    public void broadcast(String message , Socket socket){
        System.out.printf("Broadcast %s\n",message);
        
        for(int i=0; i<socketList.size(); i++){
            Socket s = socketList.get(i);
            if(s.equals(socket))
                continue;
            try {
                OutputStream out = socket.getOutputStream();
                out.write(message.getBytes());
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerDemo.class.getName()).log(Level.SEVERE, null, ex);
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
