/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Client
{
    public static void main(String args[]) throws IOException, InterruptedException {
        ClientConnection con = new ClientConnection(new Socket("localhost", 1234));
        
        Thread.sleep(10000);
        
        con.close();
   }
}
