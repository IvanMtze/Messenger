/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author wuser
 */
public class MessageReceiver extends Observable implements Runnable{

    private DataInputStream input;

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String msg = input.readUTF();
                setChanged();
                notifyObservers(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
