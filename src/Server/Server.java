/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author wuser
 */
import java.io.*;
import java.util.*;
import java.net.*;

public class Server{

    // Vector to store active clients 
    static Vector<ClientHandler> ar = new Vector<>();

    // counter for clients 
    static int i = 0;

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234 
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting 
        // client request 
        while (true) {
            // Accept the incoming request 
            s = ss.accept();

            System.out.println("New client request received : " + s);

            // obtain input and output streams 
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request. 
            String name = dis.readUTF();
            ClientHandler mtch = new ClientHandler(s, name, dis, dos);

            // Create a new Thread with this object. 
            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            // add this client to active clients list 
            for (ClientHandler cl : ar) {
                cl.dos.writeUTF("ADD-CLT-" + name);
            }

            ar.add(mtch);

            // start the thread. 
            t.start();

            // increment i for new client. 
            // i is used for naming only, and can be replaced 
            // by any naming scheme 
            i++;

        }
    }
}
