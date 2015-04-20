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
public class Client
{
    public Client(int port, String url, final String user, String password) throws IOException, InterruptedException {
        final ClientConnection con = new ClientConnection(new Socket(url, port), new ClientConnection.LoginListener() {
            @Override
            public void logedin(ClientConnection con)
            {
                String verific = con.getVerification();
                System.out.println(user + " Verification : " + verific);
                
                ChatProtocol message = new ChatProtocol(Action.MESSAGE);
                message.setVerification(verific);
                message.setMessage(new JsonMessage("Message from " + user));
                con.write(message);
            }
        }, new ClientConnection.MessageListener()
        {
            @Override
            public void message(JsonMessage message)
            {
            }
        });
        
        ChatProtocol register = new ChatProtocol(Action.REGISTER);
        register.setRegister(new JsonRegister(user, user + "@domain.de", password, password));
        con.write(register);
        
        ChatProtocol login = new ChatProtocol(Action.LOGIN);
        login.setLogin(new JsonLogin(user + "@domain.de", password));
        con.write(login);
        
        Thread.sleep(100000);
    }
}
