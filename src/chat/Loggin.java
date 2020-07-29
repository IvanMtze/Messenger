package chat;

import java.io.IOException;
import java.net.ConnectException;
import javax.swing.JOptionPane;

/**
 *
 * @author wuser
 */
public class Loggin extends javax.swing.JFrame {

    String clientName = "";

    public Loggin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnLoggin = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 140));

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(140, 100));

        btnLoggin.setText("Loggin");
        btnLoggin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogginActionPerformed(evt);
            }
        });
        jPanel4.add(btnLoggin);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel5.setPreferredSize(new java.awt.Dimension(140, 100));

        btnCancel.setText("Cancel");
        jPanel5.add(btnCancel);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Name:");
        jPanel3.add(jLabel1);

        txtName.setPreferredSize(new java.awt.Dimension(120, 28));
        jPanel3.add(txtName);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogginActionPerformed

        if (!txtName.getText().equals("")) {
            this.clientName = txtName.getText();
            ChatUI ui = new ChatUI();
            try {
                Client cl = new Client(clientName);
                cl.addObserver(ui);
                ui.setCl(cl);
                ui.setVisible(true);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "FATAL ERROR", "Error :( ", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "You must write a name", "Not name!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLogginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLoggin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
