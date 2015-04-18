/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ConnectionManager
{
    private final ArrayList<Connection> connections;
    private static ConnectionManager instance;
    
    private ConnectionManager() {
        this.connections = new ArrayList<>();
    }
    
    public static ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager();
        }
        
        return instance;
    }
    
    public void addConnection(Socket socket) {
        try
        {
            connections.add(new Connection(socket));
        }
        catch(IOException ex)
        {
            System.out.println("Connection not created");
        }
    }
    
    public void closeAllConnections() {
        for(Connection connection : connections)
        {
            connection.close();
        }
    }
    
    public void write(String string) {
        for(Connection connection : connections)
        {
            connection.write(string);
        }
    }
}
