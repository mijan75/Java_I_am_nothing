/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.demo;

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
 * @author kmhasan
 */
public class ChatServerDemo {

    ArrayList<Socket> sockets;

    public ChatServerDemo() {
        int portNumber = 9876;
        sockets = new ArrayList<>();

        try {
            // Socket - a medium for communication
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.printf("Server running on port %d\n", portNumber);
            while (true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);

                System.out.printf("Connected with %s\n", socket.getInetAddress());

                ThreadedServer threadedServer = new ThreadedServer(socket, this);
                threadedServer.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ADD A NEW METHOD TO PROCESS LOGOUT
    // WHENEVER A SOCKET LOGS OUT, WE NEED TO
    // REMOVE IT FROM THE sockets ARRAYLIST

    // MODIFY THIS METHOD TO INCLUDE socket
    public void broadcast(String message) {
        System.out.printf("BROADCASTING: %s\n", message);
        for (int i = 0; i < sockets.size(); i++) {
            Socket s = sockets.get(i);
            // ADD A LINE TO SKIP BROADCASTING
            // IF s == socket
            OutputStream out;
            try {
                out = s.getOutputStream();
                out.write(message.getBytes());
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatServerDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatServerDemo();
    }

}
