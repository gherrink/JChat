/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server.actions;

import de.dhbw.lsmb.jchat.connection.ConnectionManager;
import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.User;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import de.dhbw.lsmb.jchat.json.models.JsonRegister;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class RegisterAction extends ServerAction
{
    private static final String SELECT_USER = "FROM User u WHERE u.user = :user";
    private static final String SELECT_MAIL = "FROM User u WHERE u.mail = :mail";

    public RegisterAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        JsonRegister register = protocol.getRegister();
        
        if(register == null)
        {
            return getProtocolStatus(new JsonStatus(false, "Register is missing"));
        }
        
        if(! register.getPassword().equals(register.getPasswordRepead())) {
            return getProtocolStatus(new JsonStatus(false, "Passworeds are not equal."));
        }
        
        EntityManager em = EntityManagement.createEntityManager();
        Query query = em.createQuery(SELECT_USER);
        query.setParameter("user", register.getUser());
        if(! query.getResultList().isEmpty()){
            em.close();
            return getProtocolStatus(new JsonStatus(false, "User already exists."));
        }
        
        query = em.createQuery(SELECT_MAIL);
        query.setParameter("mail", register.getMail());
        if(! query.getResultList().isEmpty()){
            em.close();
            return getProtocolStatus(new JsonStatus(false, "Mail already exists."));
        }
        
        String pw = User.hashPassword(register.getPassword(), register.getMail());
        User newUser = new User(register.getUser(), register.getMail(), pw);
        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
        em.close();
        
        ConnectionManager.getInstance().write(protocol, getConnection());
        
        return getProtocolStatus(new JsonStatus(true, "registered"));
    }
    
}
