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
    ArrayList <Socket>sockets;
    
    public ServerDemo() {
        try {
            sockets = new ArrayList<>();
            int portNumber = 9876;
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true)
            {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                System.out.printf("%s\n", socket.getInetAddress().toString());
                ServerThread serverThread = new ServerThread(socket,this);
                serverThread.start();
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
    
    public void broadcast(String message,Socket socket){
        System.out.printf("BROADCAST :[%s] \n", message);
        for(int i = 0;i<sockets.size();i++)
        {
            Socket s = sockets.get(i);
            if (s.equals(socket))
            {
                continue;
            }
            try {
                OutputStream out =s.getOutputStream();
                out.write(message.getBytes());
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void logOut(Socket socket){
        sockets.remove(socket);
        
   }
    
}
