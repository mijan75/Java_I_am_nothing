/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ChatClient {

    public ChatClient() {
        String hostName = "192.168.1.5";
        int portNumber = 9876;
        
        try {
            Socket socket = new Socket(hostName, portNumber);
            OutputStream out = socket.getOutputStream();
            out.write("Mijan".getBytes());
            out.flush();
            
            ChatClientThread chatClientThread = new ChatClientThread(socket);
            chatClientThread.start();
            while(true){
                String message = "";
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                message = read.readLine();
                out.write(message.getBytes());
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatClient();
    }
    
}
