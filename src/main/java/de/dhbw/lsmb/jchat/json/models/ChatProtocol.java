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
    private String verification;
    private JsonMessage message;
    private ArrayList<JsonMessage> messages;
    private JsonRegister register;
    private JsonStatus status;
    private JsonLogin login;
    
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
    public JsonMessage getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(JsonMessage message)
    {
        this.message = message;
    }

    /**
     * @return the register
     */
    public JsonRegister getRegister()
    {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(JsonRegister register)
    {
        this.register = register;
    }

    /**
     * @return the messages
     */
    public ArrayList<JsonMessage> getMessages()
    {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(ArrayList<JsonMessage> messages)
    {
        this.messages = messages;
    }

    /**
     * @return the status
     */
    public JsonStatus getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(JsonStatus status)
    {
        this.status = status;
    }

    /**
     * @return the verification
     */
    public String getVerification()
    {
        return verification;
    }

    /**
     * @param verification the verification to set
     */
    public void setVerification(String verification)
    {
        this.verification = verification;
    }

    /**
     * @return the login
     */
    public JsonLogin getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(JsonLogin login)
    {
        this.login = login;
    }
    
}
