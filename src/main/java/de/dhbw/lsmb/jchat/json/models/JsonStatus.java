/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.json.models;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class JsonStatus
{
    private boolean correct;
    private String message;
    
    public JsonStatus(boolean correct, String message) {
        this.correct = correct;
        this.message = message;
    }
    
    /**
     * @return the correct
     */
    public boolean isCorrect()
    {
        return correct;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(boolean correct)
    {
        this.correct = correct;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    @Override
    public String toString() {
        String str = "";
        if(correct) {
            str += "All right. ";
        } else {
            str += "Error. ";
        }
        
        return str + message;
    }
    
}
