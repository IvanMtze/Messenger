/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author wuser
 */
public class ClientHandler implements Runnable {

    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // constructor 
    public ClientHandler(Socket s, String name,
            DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {
                // receive the string 
                received = dis.readUTF();
                    System.out.println(received);

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    for (ClientHandler mc : Server.ar) {
                        mc.dos.writeUTF(this.name + ":" + "logout");
                    }
                    this.s.close();
                    break;
                }

                // break the string into message and recipient part 
                StringTokenizer st = new StringTokenizer(received, ":");
                String recipient = st.nextToken();
                String MsgToSend = st.nextToken();

                // search for the recipient in the connected devices list. 
                // ar is the vector storing client of active users 
                for (ClientHandler mc : Server.ar) {
                    // if the recipient is found, write on its 
                    // output stream 
                    if (mc.name.equals(recipient) && mc.isloggedin == true) {
                        mc.dos.writeUTF(this.name + " [PRIVATE] : " + MsgToSend);
                        this.dos.writeUTF(this.name+ "[PRIVATE TO "+mc.getName()+"]"+MsgToSend);
                        break;
                    }
                }
                if (recipient.equals("GLOBAL")) {
                    for (ClientHandler mc : Server.ar) {
                        // if the recipient is found, write on its 
                        // output stream 
                        mc.dos.writeUTF(this.name + " [GLOBAL] : " + MsgToSend);
                    }
                }
                //dos.flush();

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try {
            // closing resources 
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
