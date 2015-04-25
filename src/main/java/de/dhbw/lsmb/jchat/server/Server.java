/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server;

import de.dhbw.lsmb.jchat.connection.ConnectionManager;
import de.dhbw.lsmb.jchat.db.EntityManagement;
import de.dhbw.lsmb.jchat.db.models.Message;
import de.dhbw.lsmb.jchat.json.models.Action;
import de.dhbw.lsmb.jchat.json.models.ChatProtocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public final class Server extends Thread
{
    private ServerSocket serverSocket;
    private boolean running;
    
    public Server(int port, String entity) throws IOException {
        System.out.println("Server start @ Port " + port + ".");
        serverSocket = new ServerSocket(port);
        EntityManagement.getEntityManagerFactoryInstance(entity);
    }
    
    public Server(int port, String entity, int parentPort, String parentHost) throws IOException {
        this(port, entity);
        ServerConnection con = ConnectionManager.getInstance().addConnection(new Socket(parentHost, parentPort));
        ChatProtocol prot = new ChatProtocol(Action.USER_SEND);
        prot.setVerification(ServerConnection.SERVER_VERIFIC);
        con.write(prot);
       
        prot = new ChatProtocol(Action.HISTORY_SEND);
        prot.setVerification(ServerConnection.SERVER_VERIFIC);
        
        EntityManager em = EntityManagement.createEntityManager();
        Query query = em.createQuery("FROM Message m ORDER BY m.date DESC");
        query.setMaxResults(1);
        List<Message> lastMessage = query.getResultList();
        
        if(! lastMessage.isEmpty()) {
            prot.setDate(lastMessage.get(0).getDate());
        }
        
        con.write(prot);
    }
    
    @Override
    public void start() {
        super.start();
        running = true;
    }
    
    public void close() {
        try
        {
            running = false;
            ConnectionManager.getInstance().closeAllConnections();
            serverSocket.close();
        }
        catch(IOException ex)
        {
            System.out.println("Serversocket not closed.");
        }
    }
    
    @Override
    public void run() {
        while(running && !serverSocket.isClosed()) {
            try
            {
                System.out.println("Waiting 4 connection.");
                Socket socket = serverSocket.accept();
                System.out.println("Connected " + socket.getInetAddress());
                ConnectionManager.getInstance().addConnection(socket);
            }
            catch(IOException ex)
            {
                System.out.println("Not connected.");
            }
        }
        close();
    }
}
