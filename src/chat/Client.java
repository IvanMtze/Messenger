package chat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wuser
 */
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Client extends Observable implements Observer {

    final static int ServerPort = 1234;
    String name = "";
    String conver = "";
    MessageOutput mo;
    
    final static int STATUS_OK = 200;
    final static int STATUS_ERR = 500;

    public Client(String name) {
        this.name = name;
        this.mo = new MessageOutput();
        try {
            ClientInit(this.name);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ClientInit(String name) throws UnknownHostException, IOException {
        // getting localhost ip 
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection 
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams 
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeUTF(name);

        // readMessage thread 
        MessageReceiver mr = new MessageReceiver();
        mr.setInput(dis);
        Thread readMessage = new Thread(mr);
        mr.addObserver(this);
        readMessage.start();
    }

    public void sendMessage(String message) {
        mo.sendMessage(message);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MessageOutput) {
            if (arg.equals("OK")) {
                notifyObservers(this.STATUS_OK);
            }
        } else if (o instanceof MessageReceiver) {
            System.out.println(arg);
            notifyObservers(arg);
        }
    }

}
