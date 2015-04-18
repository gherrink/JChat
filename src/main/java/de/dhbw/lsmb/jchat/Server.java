/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Server
{
    public static void main(String args[]) {
        String data = "Test string";
        try {
            ServerSocket socket = new ServerSocket(1234);
            Socket skt = socket.accept();
            System.out.println("Server has connected!");
            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
            System.out.println("Sending string: '" + data + "'");
            out.print(data);
            out.close();
            skt.close();
            socket.close();
        } catch(Exception e) {
            System.out.println("Whoops! It didn't work!");
        }
   }
}
