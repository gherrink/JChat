/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat;

import de.dhbw.lsmb.jchat.server.Server;
import java.io.IOException;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Server3
{
    public static final int PORT = 4123;
    
    public static void main(String[] args) throws IOException
    {
        (new Server(PORT, "chat3", Server2.PORT, "localhost")).start();
    }
}
