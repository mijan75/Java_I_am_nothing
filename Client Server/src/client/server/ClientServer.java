/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ClientServer {
    public ClientServer(){
        String hostName = "192.168.1.5";
        int portName = 9876;
        try {
            while(true){
            Socket socket = new Socket(hostName,portName);
            OutputStream out = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            out.write(message.getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ClientServer();
    }
    
}
