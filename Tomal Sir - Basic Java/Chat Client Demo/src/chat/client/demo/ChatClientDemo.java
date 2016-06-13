/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class ChatClientDemo {

    public ChatClientDemo() {
        String hostname = "192.168.1.13";
        int portNumber = 9876;
        try {
            Socket socket = new Socket(hostname, portNumber);
            OutputStream out = socket.getOutputStream();
            String message = "";
            
            ThreadedClientReader reader = new ThreadedClientReader(socket);
            reader.start();
            
            String name = "Monirul Hasan";
            out.write(name.getBytes());
            out.flush();
            
            BufferedReader stdin = new BufferedReader
                (new InputStreamReader(System.in));
            while (true) {
                message = stdin.readLine();
                out.write(message.getBytes());
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatClientDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatClientDemo();
    }

}
