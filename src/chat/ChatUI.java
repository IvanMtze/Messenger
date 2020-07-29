package chat;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

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
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane3.setViewportView(txtPanel);

        jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        msgTxt.setText("Message here...");
        msgTxt.setToolTipText("Write your message here...");
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

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String msg = msgTxt.getText();
        if (!msg.equals("") || !msg.equals("Message here...")) {
            messageHistory.add(msg);
            messageIndex = messageHistory.size();
            cl.sendMessage(jList2.getSelectedValue() + ":" + msg);
            //txtPanel.append(msg);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void msgTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgTxtKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (messageIndex > 0 && messageIndex < messageHistory.size()) {
                msgTxt.setText(messageHistory.get(messageIndex--));
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (messageIndex > 0 && messageIndex < messageHistory.size()) {
                msgTxt.setText(messageHistory.get(messageIndex++));
            }
        }
    }//GEN-LAST:event_msgTxtKeyTyped

    private void msgTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_msgTxtFocusGained
        msgTxt.setText("");
    }//GEN-LAST:event_msgTxtFocusGained

    private void msgTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_msgTxtFocusLost
        msgTxt.setText("Message here...");
    }//GEN-LAST:event_msgTxtFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
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
        } else if (arg.equals(Client.STATUS_ERR)) {
            JOptionPane.showMessageDialog(this, "Unable to send message, try again", "Message Error", JOptionPane.ERROR_MESSAGE);
        } else if (arg.toString().contains("ADD-CLT-")) {
            String newClientName = arg.toString().subSequence(8, arg.toString().length()).toString();
            System.out.println("added client " + newClientName);
            users.add(users.size(), newClientName);
            txtPanel.append("has joined" + newClientName);
        } else {
            txtPanel.append(arg.toString());
        }
    }

}
