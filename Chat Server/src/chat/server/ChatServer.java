/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ChatServer {
    private ChatServer(){
        int portNumber = 9876;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.printf("Server is running %d port\n",portNumber);
            while (true)
            {
                Socket socket = serverSocket.accept();
                System.out.printf("Connected with %s\n",socket.getInetAddress());
                InputStream in = socket.getInputStream();
                byte []message = new byte[1000];
             
                int length = in.read(message);
                System.out.printf("Client sent : %s\n", new String(message).substring(0, length));
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ChatServer();
    }
    
}
