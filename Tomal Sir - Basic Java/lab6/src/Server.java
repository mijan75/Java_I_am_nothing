import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class Server {

    public Server() {
        int portNumber = 9876;
        byte[] messageBytes = new byte[1000];
        int sum = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            System.out.printf("Waiting on clients on port %d\n", portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("Connected with %s\n", clientSocket.getInetAddress());

                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                in.read(messageBytes); // reading into byte array
                String message = new String(messageBytes).trim(); // converting the byte array to String
                int numberSent = Integer.parseInt(message); // converting string to integer

                System.out.printf("Client %s sent %d\n",
                        clientSocket.getInetAddress(), numberSent);
                sum = sum + numberSent;

                String outMessage = "" + sum;
                System.out.printf("Sending %d to %s\n", sum, clientSocket.getInetAddress());
                out.write(outMessage.getBytes());
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Server();
    }

}
