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
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Client2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        final ClientConnection con = new ClientConnection(new Socket("localhost", 1234), new ClientConnection.LoginListener() {
            @Override
            public void logedin(ClientConnection con)
            {
                String verific = con.getVerification();
                System.out.println("Verification: " + verific);
                
                ChatProtocol message = new ChatProtocol(Action.MESSAGE);
                message.setVerification(verific);
                message.setMessage(new JsonMessage("Message from client2"));
                con.write(message);
                
//                ChatProtocol logout = new ChatProtocol(Action.LOGOUT);
//                logout.setVerification(verific);
//                con.write(logout);
            }
        });
        
        ChatProtocol register = new ChatProtocol(Action.REGISTER);
        register.setRegister(new JsonRegister("client2", "client2@domain.de", "password2", "password2"));
        con.write(register);
        
        ChatProtocol login = new ChatProtocol(Action.LOGIN);
        login.setLogin(new JsonLogin("client2@domain.de", "password2"));
        con.write(login);
        
        Thread.sleep(100000);
        
        con.close();
    }
    
}
