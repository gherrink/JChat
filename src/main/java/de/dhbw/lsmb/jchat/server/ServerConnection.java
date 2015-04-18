/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server;

import de.dhbw.lsmb.jchat.connection.Connection;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ServerConnection extends Connection
{

    public ServerConnection(Socket socket) throws IOException
    {
        super(socket);
    }

    @Override
    protected void doAction(ChatProtocol protocol)
    {
        //@TODO implement
    }
    
}
