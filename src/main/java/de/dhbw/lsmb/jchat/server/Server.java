/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server;

import de.dhbw.lsmb.jchat.connection.ConnectionManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public final class Server extends Thread
{
    public static void main(String args[]) throws IOException {
        (new Server(1234)).start();
    }
    
    private final ServerSocket serverSocket;
    private boolean running;
    
    
    public Server(int port) throws IOException {
        System.out.println("Server start @ Port " + port + ".");
        serverSocket = new ServerSocket(port);
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
