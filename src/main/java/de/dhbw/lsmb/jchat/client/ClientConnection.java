/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.client;

import de.dhbw.lsmb.jchat.connection.Connection;
import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonMessage;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ClientConnection extends Connection
{
    private String verification = null;
    private LoginListener loginListener;
    
    public ClientConnection(Socket socket, LoginListener loginListener) throws IOException
    {
        super(socket);
        this.loginListener = loginListener;
    }
    
    public String getVerification() {
        return verification;
    }

    @Override
    protected void doAction(ChatProtocol protocol)
    {
        if(protocol.getAction().equals(Action.STATUS.toString())) {
            showStatus(protocol.getStatus());
        } else if(protocol.getAction().equals(Action.MESSAGE.toString())) {
            showMessage(protocol.getMessage());
        } 
        if(verification == null && protocol.getVerification() != null) {
            verification = protocol.getVerification();
            loginListener.logedin(this);
        }
    }
    
    private void showStatus(JsonStatus status)
    {
        if(status != null)
        {
            System.out.println(status.toString());
        }
    }
    
    private void showMessage(JsonMessage message) {
        if(message != null) {
            System.out.println(message.toString());
        }
    }
    
    public interface LoginListener {
        public void logedin(ClientConnection con);
    }
    
}
