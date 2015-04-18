/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.json.models;

import java.util.ArrayList;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class ChatProtocol
{
    private String action;
    private Message message;
    private ArrayList<Message> messages;
    private Register register;
    
    public ChatProtocol(Action action) {
        this.action = action.toString();
    }

    /**
     * @return the action
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * @return the message
     */
    public Message getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message)
    {
        this.message = message;
    }

    /**
     * @return the register
     */
    public Register getRegister()
    {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(Register register)
    {
        this.register = register;
    }

    /**
     * @return the messages
     */
    public ArrayList<Message> getMessages()
    {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(ArrayList<Message> messages)
    {
        this.messages = messages;
    }
    
}
