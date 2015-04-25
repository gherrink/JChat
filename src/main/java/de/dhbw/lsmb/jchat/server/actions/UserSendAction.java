/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import com.google.gson.stream.JsonReader;
import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.User;
import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonRegister;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class UserSendAction extends ServerAction
{

    public UserSendAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        EntityManager em = EntityManagement.createEntityManager();
        
        ChatProtocol prot = new ChatProtocol(Action.USER_SAVE);
        prot.setVerification(ServerConnection.SERVER_VERIFIC);
        
        List<User> users = em.createQuery("FROM User u").getResultList();
        
        prot.setUser(new ArrayList<>(users));
        
        getConnection().write(prot);
        
        
        return getProtocolStatus(new JsonStatus(true, "Send Actions"));
    }
    
}
