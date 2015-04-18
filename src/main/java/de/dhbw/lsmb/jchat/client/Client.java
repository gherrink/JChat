/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.client;

import com.google.gson.Gson;
import de.dhbw.lsmb.jchat.json.models.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class Client
{
    public static void main(String args[]) {
        try {
            Socket skt = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.println("Received strings:");
            while (!in.ready()) {}
            String line = in.readLine();
            System.out.println(line);
            
            Gson gson = new Gson();
            Message msg = gson.fromJson(line, Message.class);
            
            System.out.println(msg.toString());
            in.close();
      }
      catch(Exception e) {
         System.out.println("It didn't work!");
      }
   }
}
