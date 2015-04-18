/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.db.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
@Entity
public class Message
{
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;
    
    @Column(nullable = false)
    private String message;
    
    @Column(nullable = false)
    private int sender;
    
    public Message(String message, int sender)
    {
        this.message = message;
        this.sender = sender;
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

    /**
     * @return the sender
     */
    public int getSender()
    {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(int sender)
    {
        this.sender = sender;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }
    
}
