/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.Message;
import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonMessage;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class HisotrySendAction extends ServerAction
{
    private static final String SELECT = "FROM Message m WHERE m.date > :date";

    public HisotrySendAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        Date date = protocol.getDate();
        
        EntityManager em = EntityManagement.createEntityManager();
        
        ChatProtocol prot = new ChatProtocol(Action.HISTORY_SAVE);
        ArrayList<JsonMessage> messages = new ArrayList<>();
        
        Query query;
        if(date == null) {
            query = em.createQuery("FROM Message m");
        } else {
            query = em.createQuery(SELECT);
            query.setParameter("date", date);
        }
        List<Message> result = query.getResultList();
        
        for(Message msg : result)
        {
            JsonMessage jmsg = new JsonMessage(msg.getMessage());
            jmsg.setDate(msg.getDate());
            jmsg.setSenderid(msg.getId());
            messages.add(jmsg);
        }
        
        prot.setMessages(messages);
        prot.setVerification(ServerConnection.SERVER_VERIFIC);
        getConnection().write(prot);
        
        return getProtocolStatus(new JsonStatus(true, "send history correct"));
    }
    
}
