/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat;

import de.dhbw.lsmb.jchat.client.Client;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ClientsTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        newClient(Server1.PORT, "localhost", "user1", "password1");
        newClient(Server2.PORT, "localhost", "user2", "password2");
    }
    
    public static void newClient(final int port, final String url, final String user, final String password) {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new Client(port, url, user, password);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }, "Client_"+user).start();
    }
    
}
