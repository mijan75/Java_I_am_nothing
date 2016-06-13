/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class Client {

    public Client() {
        try {
            String hostName = "192.168.1.13";
            int portNumber = 9876;
            Socket socket = new Socket(hostName,portNumber);
            OutputStream out = socket.getOutputStream();
            out.write("Mijanur Rahaman".getBytes());
            out.flush();
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ClientThread im  = new ClientThread(socket);
            im.start();
            String message ="";
            while (true){
                message = stdin.readLine();
                out.write(message.getBytes());
                out.flush();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Client();
    }
    
}
