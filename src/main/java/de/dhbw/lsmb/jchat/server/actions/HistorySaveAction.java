/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.Message;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonMessage;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class HistorySaveAction extends ServerAction
{

    public HistorySaveAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        ArrayList<JsonMessage> messages = protocol.getMessages();
        if(messages == null)
        {
            return getProtocolStatus(new JsonStatus(false, "Messages are missing"));
        }
        
        EntityManager em = EntityManagement.createEntityManager();
        em.getTransaction().begin();
        
        for( JsonMessage msg : messages)
        {
            Message message = new Message(msg.getDate(), msg.getMessage(), msg.getSenderid());
            em.persist(message);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return getProtocolStatus(new JsonStatus(true, "Messages got"));
    }
    
}
