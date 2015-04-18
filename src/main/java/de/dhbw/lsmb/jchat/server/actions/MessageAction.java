/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.connection.ConnectionManager;
import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.Message;
import de.dhbw.lsmb.jchat.db.models.User;
import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonMessage;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class MessageAction extends ServerAction
{
    private static final String SELECT = "FROM User u WHERE u.password = :pw";

    public MessageAction(ServerConnection connection)
    {
        super(connection);
    }
    
    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        JsonMessage msg = protocol.getMessage();
        if(msg == null)
        {
            return getProtocolStatus(new JsonStatus(false, "Message is missing"));
        }
        
        EntityManager em = EntityManagement.createEntityManager();
        int sender;
        String sendername = "";
        if(msg.getSender() == null) {
            Query query = em.createQuery(SELECT);
            query.setParameter("pw", protocol.getVerification());
            List<User> result = query.getResultList();
            if(result.isEmpty()){
                em.close();
                return getProtocolStatus(new JsonStatus(false, "User not found."));
            }
            sendername = result.get(0).getUser();
            sender = result.get(0).getId();
        } else {
            sender = msg.getSenderid();
        }
        
        Message message = new Message(new Date(), msg.getMessage(), sender);
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
        em.close();
        
        ChatProtocol msgProt = new ChatProtocol(Action.MESSAGE);
        msgProt.setVerification(ServerConnection.SERVER_VERIFIC);
        msgProt.setMessage(new JsonMessage(sendername, message.getMessage(), message.getDate()));
        ConnectionManager.getInstance().write(msgProt, getConnection());
        
        return getProtocolStatus(new JsonStatus(true, "Message send"));
    }
    
}
