/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.client;

import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonLogin;
import de.dhbw.lsmb.jchat.json.models.JsonMessage;
import de.dhbw.lsmb.jchat.json.models.JsonRegister;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ChatFrame extends javax.swing.JFrame implements ClientConnection.LoginListener, ClientConnection.MessageListener
{
    private ClientConnection connection;
    private String verification;
    
    /**
     * Creates new form ChatFrame
     */
    public ChatFrame()
    {
        initComponents();
        
        try
        {
            String host = JOptionPane.showInputDialog("Please enter host", "localhost");
            int port = Integer.parseInt(JOptionPane.showInputDialog("Please enter port", "1234"));

            String user = JOptionPane.showInputDialog("Please enter a username", "user");
            String mail = JOptionPane.showInputDialog("Please enter a mail", "user@domain.de");
            String password = JOptionPane.showInputDialog("Please enter a password", "password");
            
            this.setTitle("Chat - " + user);
            
            connection = new ClientConnection(new Socket(host, port), this, this);
            
            ChatProtocol register = new ChatProtocol(Action.REGISTER);
            register.setRegister(new JsonRegister(user, mail, password, password));
            connection.write(register);

            ChatProtocol login = new ChatProtocol(Action.LOGIN);
            login.setLogin(new JsonLogin(mail, password));
            connection.write(login);
        }
        catch(Exception ex)
        {
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSend = new javax.swing.JTextPane();
        btnSend = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaChat = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSend.setEditable(false);
        txtSend.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtSendKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtSend);

        btnSend.setText("send");
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSendActionPerformed(evt);
            }
        });

        txtAreaChat.setEditable(false);
        txtAreaChat.setColumns(20);
        txtAreaChat.setRows(5);
        jScrollPane4.setViewportView(txtAreaChat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSend)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSendActionPerformed
    {//GEN-HEADEREND:event_btnSendActionPerformed
        sendMessage();
    }//GEN-LAST:event_btnSendActionPerformed

    private void txtSendKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtSendKeyReleased
    {//GEN-HEADEREND:event_txtSendKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            sendMessage();
            txtSend.setText("");
        }
    }//GEN-LAST:event_txtSendKeyReleased
    
    private void sendMessage() {
        System.out.println("hallo");
        String msg = txtSend.getText();
        txtSend.setText("");
        ChatProtocol message = new ChatProtocol(Action.MESSAGE);
        message.setVerification(verification);
        message.setMessage(new JsonMessage(msg));
        connection.write(message);
        showMessage("Me", msg, new Date());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ChatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtAreaChat;
    private javax.swing.JTextPane txtSend;
    // End of variables declaration//GEN-END:variables

    @Override
    public void logedin(ClientConnection con)
    {
        verification = con.getVerification();
        if(verification == null) {
            System.exit(0);
        }
        txtSend.setEnabled(true);
        txtSend.setEditable(true);
        btnSend.setEnabled(true);
    }

    @Override
    public void message(JsonMessage message)
    {
        showMessage(message.getSender(), message.getMessage(), message.getDate());
    }
    
    private void showMessage(String user, String message, Date date)
    {
        String msg = date.toString() + " " + user + ": " + message;
        txtAreaChat.setText(txtAreaChat.getText() + msg);
    }
}
