/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public abstract class ServerAction
{
    private final ServerConnection connection;
    
    public ServerAction(ServerConnection connection) {
        this.connection = connection;
    }
    
    public abstract ChatProtocol doAction(ChatProtocol protocol);
    
    public ChatProtocol getProtocolStatus(JsonStatus status) {
        ChatProtocol protocol = new ChatProtocol(Action.STATUS);
        protocol.setStatus(status);
        return protocol;
    }
    
    public ChatProtocol getProtocolStatus(JsonStatus status, String verification) {
        ChatProtocol protocol = getProtocolStatus(status);
        protocol.setVerification(verification);
        return protocol;
    }
    
    public ServerConnection getConnection() {
        return connection;
    }
}
