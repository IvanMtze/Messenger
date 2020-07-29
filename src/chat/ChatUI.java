package chat;

import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author wuser
 */
public class ChatUI extends javax.swing.JFrame implements Observer{

    DefaultListModel<String> users;
    public ChatUI() {
        users = new DefaultListModel<>();
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
        jTextArea2 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
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

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jTextField2.setText("Message here...");
        jTextField2.setToolTipText("Write your message here...");
        jPanel6.add(jTextField2, java.awt.BorderLayout.CENTER);

        btnSend.setText("Enviar");
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
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof MessageOutput){
            if(arg.equals(Client.STATUS_OK)){
                
            }
            else if (arg.equals(Client.STATUS_ERR)){
                
            }
        }
        else if(o instanceof MessageReceiver){
            if(arg.toString().contains("ADD-CLT-")){
                String newClientName = arg.toString().subSequence(8, arg.toString().length()).toString();
                System.out.println("added client "+  newClientName);
                users.addElement(newClientName);
            }
            else
                System.out.println(arg);
        }
    }
}
