/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public final class Connection extends Thread
{
    private final Socket socket;
    private boolean connected;
    
    private PrintWriter out;
    private BufferedReader in;
    
    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        open();
    }
    
    public void open() throws IOException
    {
        System.out.println("Open Connection");
        this.connected = true;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.start();
    }
    
    public void close()
    {
        System.out.println("Close connection");
        this.connected = false;
        if(out != null) {
            out.close();
        }
        if(in != null) {
            try
            {
                in.close();
            }
            catch(IOException ex)
            {}
        }
        if(socket != null) {
            try
            {
                socket.close();
            }
            catch(IOException ex)
            {}
        }
    }
    
    @Override
    public void run() {
        while(connected && socket.isConnected() && !socket.isClosed())
        {
            try
            {
                String read = in.readLine();
                if(read == null) {
                    break;
                }
                System.out.println("Read from Client " + read);
                //TODO desight what to do with String
            }
            catch(IOException ex)
            {
                break;
            }
        }
        
        close();
    }
    
    public void write(String write) {
        System.out.println("Write to Client " + write);
        out.write(write);
    }
}
