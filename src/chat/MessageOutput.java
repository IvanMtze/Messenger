/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author wuser
 */
public class MessageOutput extends Observable {

    private DataOutputStream output;

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream input) {
        this.output = input;
    }

    public void sendMessage(String msg) {
        try {
            output.writeUTF(msg);
            setChanged();
            notifyObservers("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
