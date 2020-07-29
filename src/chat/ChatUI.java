package chat;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author wuser
 */
public class ChatUI extends javax.swing.JFrame implements Observer {

    DefaultListModel<String> users;
    Vector<String> messageHistory;
    int messageIndex = 0;
    Client cl;

    public Client getCl() {
        return cl;
    }

    public void setCl(Client cl) {
        this.cl = cl;
        this.setTitle("Chat " + cl.name);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    cl.sendMessage("logout");
                    try {
                        cl.mo.getOutput().close();
                    } catch (IOException ex) {
                        Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);

                }
            }
        });
    }

    public ChatUI() {
        users = new DefaultListModel<>();
        users.addElement("GLOBAL");
        messageHistory = new Vector<>();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPanel = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        msgTxt = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(150, 100));
        setSize(new java.awt.Dimension(150, 100));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jList2.setModel(users);
        jList2.setPreferredSize(new java.awt.Dimension(140, 140));
        jScrollPane4.setViewportView(jList2);

        jPanel2.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel4.setLayout(new java.awt.BorderLayout());

        txtPanel.setEditable(false);
        txtPanel.setColumns(20);
        txtPanel.setRows(5);
        txtPanel.setFocusable(false);
        jScrollPane3.setViewportView(txtPanel);

        jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        msgTxt.setText("Message here...");
        msgTxt.setToolTipText("Write your message here...");
        msgTxt.setPreferredSize(new java.awt.Dimension(111, 50));
        msgTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                msgTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                msgTxtFocusLost(evt);
            }
        });
        msgTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                msgTxtKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                msgTxtKeyPressed(evt);
            }
        });
        jPanel6.add(msgTxt, java.awt.BorderLayout.CENTER);

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        jPanel6.add(btnSend, java.awt.BorderLayout.LINE_END);

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Menu");

        jMenuItem1.setText("logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Actions");

        jMenuItem2.setText("Send nudge");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String msg = msgTxt.getText();
        if (jList2.getSelectedValue() != null) {
            if (!msg.equals("") && !msg.equals("Message here...")) {
                messageHistory.add(msg);
                messageIndex = messageHistory.size() - 1;
                cl.sendMessage(jList2.getSelectedValue() + ":" + msg);
            }
        } else if (!msg.equals("") && !msg.equals("Message here...")) {
            messageHistory.add(msg);
            messageIndex = messageHistory.size() - 1;
            cl.sendMessage(jList2.getModel().getElementAt(0) + ":" + msg);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void msgTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgTxtKeyTyped

    }//GEN-LAST:event_msgTxtKeyTyped

    private void msgTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_msgTxtFocusGained
        if (msgTxt.getText().equals("Message here...")) {
            msgTxt.setText("");
        }
    }//GEN-LAST:event_msgTxtFocusGained

    private void msgTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_msgTxtFocusLost
        if (msgTxt.getText().equals("")) {
            msgTxt.setText("Message here...");
        }
    }//GEN-LAST:event_msgTxtFocusLost

    private void msgTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (messageIndex >= 0 && messageIndex <= messageHistory.size()) {
                msgTxt.setText(messageHistory.get(messageIndex--));
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

            if (messageIndex >= 0 && messageIndex <= messageHistory.size()) {
                msgTxt.setText(messageHistory.get(messageIndex++));
            }
        }
     }//GEN-LAST:event_msgTxtKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        logout();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        cl.sendMessage(jList2.getModel().getElementAt(0) + ":" + "nudge-nudge");
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField msgTxt;
    private javax.swing.JTextArea txtPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        if (arg.equals(Client.STATUS_OK)) {
            msgTxt.setText("");
            System.out.println("mensage enviado");
        } else if (arg.equals(Client.STATUS_ERR)) {
            JOptionPane.showMessageDialog(this, "Unable to send message, try again", "Message Error", JOptionPane.ERROR_MESSAGE);
        } else if (arg.toString().contains("ADD-CLT-")) {
            String newClientName = arg.toString().subSequence(8, arg.toString().length()).toString();
            System.out.println("added client " + newClientName);
            if (!cl.name.equals(newClientName) && !users.contains(newClientName)) {
                users.add(users.size(), newClientName);
                txtPanel.append("\n\t" + newClientName + " HAS JOINED THE CHAT \n");
            }
        } else if (arg.toString().contains("logout")) {
            StringTokenizer tok = new StringTokenizer(arg.toString(),":");
            String remove = tok.nextToken();
            if (!cl.name.equals(remove) && users.contains(remove)) {
                users.removeElement(remove);
                txtPanel.append("\n\t" + remove + " HAS LEFT THE CHAT \n");
            }
        } else if (arg.toString().contains("nudge-nudge")) {
            txtPanel.append("\n\tSOMEONE SENT A NUDGE NUDGE\n");
            playSound("../sounds/nudge-nudge-msn.wav");
            int x = (int) getLocation().getX();
            int y = (int) getLocation().getY();
            for (int i = 0; i < 10; i++) {
                setLocation(x + (ThreadLocalRandom.current().nextInt(-30, 30)), y + (ThreadLocalRandom.current().nextInt(-30, 30)));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            setLocation(x, y);
        } else {
            txtPanel.append(arg.toString() + "\n");
            playSound("../sounds/messenger-tono-mensaje-.wav");
        }
    }

    private void playSound(String file) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(file);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void logout() {
        int confirm = JOptionPane.showOptionDialog(
                this, "Are You Sure to Close Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            cl.sendMessage("logout");
            System.exit(0);
            try {
                cl.mo.getOutput().close();
            } catch (IOException ex) {
                Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
