/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.User;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class UserSaveAction extends ServerAction
{

    public UserSaveAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        ArrayList<User> users = protocol.getUser();
        
        EntityManager em = EntityManagement.createEntityManager();
        em.getTransaction().begin();
        
        for(User user : users)
        {
            Query query = em.createQuery("FROM User u WHERE id = :id");
            query.setParameter("id", user.getId());
            em.merge(user);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return getProtocolStatus(new JsonStatus(true, "Save all users"));
    }
    
}
