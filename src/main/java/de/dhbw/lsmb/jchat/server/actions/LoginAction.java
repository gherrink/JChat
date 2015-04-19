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
import de.dhbw.lsmb.jchat.json.models.JsonLogin;
import de.dhbw.lsmb.jchat.json.models.JsonStatus;
import de.dhbw.lsmb.jchat.server.ServerConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class LoginAction extends ServerAction
{
    private static final String SELECT = "FROM User u WHERE u.mail = :mail AND u.password = :password";

    public LoginAction(ServerConnection connection)
    {
        super(connection);
    }

    @Override
    public ChatProtocol doAction(ChatProtocol protocol)
    {
        JsonLogin login = protocol.getLogin();
        if(login == null)
        {
            return getProtocolStatus(new JsonStatus(false, "Login is missing"));
        }
        
        String pw = User.hashPassword(login.getPassword(), login.getMail());
        
        EntityManager em = EntityManagement.createEntityManager();
        Query query = em.createQuery(SELECT);
        query.setParameter("mail", login.getMail());
        query.setParameter("password", pw);
        
        if(query.getResultList().isEmpty()){
            em.close();
            return getProtocolStatus(new JsonStatus(false, "Mail or password is incorrect."));
        }
        
        return getProtocolStatus(new JsonStatus(true, "logged in"), pw);
    }
    
}
