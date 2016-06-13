/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.demo;

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
 * @author kmhasan
 */
public class ThreadedServer extends Thread {

    private Socket socket;
    private ChatServerDemo mainServer;
    
    public ThreadedServer(Socket socket, ChatServerDemo mainServer) {
        this.socket = socket;
        this.mainServer = mainServer;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            byte[] message = new byte[1000];
            SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
            
            String name;
            in.read(message);
            name = new String(message).trim();
            mainServer.broadcast(name + " has logged in");
            
            while (true) {
                int length = in.read(message);
                if (length >= 0) {
                    Date date = new Date();
                    String inputMessage = new String(message).substring(0, length);
                    System.out.printf("[%s][%s]: %s\n", formatter.format(date), name, inputMessage);
                    String outputMessage = String.format("[%s][%s]: %s", formatter.format(date), name, inputMessage);
                    // MODIFY THE CALL TO INCLUDE socket
                    mainServer.broadcast(outputMessage);
                } else {
                    mainServer.broadcast(name + " has logged out");
                    // CALL APPROPRIATE METHOD IN mainServer TO
                    // REMOVE THIS SOCKET
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
