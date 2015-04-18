/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.client;

import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonLogin;
import de.dhbw.lsmb.jchat.json.models.JsonRegister;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Client
{
    public static void main(String args[]) throws IOException, InterruptedException {
        final ClientConnection con = new ClientConnection(new Socket("localhost", 1234), new ClientConnection.LoginListener() {
            @Override
            public void logedin(ClientConnection con)
            {
                System.out.println("Verification: " + con.getVerification());
            }
        });
        
        ChatProtocol register = new ChatProtocol(Action.REGISTER);
        register.setRegister(new JsonRegister("user", "user.user@domain.de", "password", "password"));
        con.write(register);
        con.write(register);
        register.setRegister(new JsonRegister("user2", "user.user@domain.de", "password", "password"));
        con.write(register);
        register.setRegister(new JsonRegister("user2", "user.user@domain.com", "password", "password111"));
        con.write(register);
        
        ChatProtocol login = new ChatProtocol(Action.LOGIN);
        login.setLogin(new JsonLogin("user.user@domain.com", "password"));
        con.write(login);
        login.setLogin(new JsonLogin("user.user@domain.de", "password"));
        con.write(login);
        
        Thread.sleep(100000);
        
        con.close();
   }
}
