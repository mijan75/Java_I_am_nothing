/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ClientDemo {

    public ClientDemo() {
        try {
            // TODO code application logic here
            String hostName = "192.168.1.13";
            int portNumber = 9876;
          
            Socket socket = new Socket(hostName,portNumber);
            OutputStream out = socket.getOutputStream();
            out.write("Mijan".getBytes());
            out.flush();
            
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
            
            while(true)
            {
                String message = stdin.readLine();
                out.write(message.getBytes());
                out.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ClientDemo();
    }
    
}
