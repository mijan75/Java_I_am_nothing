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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class ClientDemo {

    public ClientDemo() {
        String hostName = "192.168.1.5";
        int portNumber = 9876;
        
        try {
            Socket socket = new Socket(hostName, portNumber);
            OutputStream out = socket.getOutputStream();
            String name ;
            System.out.println("Enter you name please");
            Scanner sc = new Scanner(System.in);
            name = sc.nextLine();
            out.write(name.getBytes());
            out.flush();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            while(true){
                String message = reader.readLine();
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
