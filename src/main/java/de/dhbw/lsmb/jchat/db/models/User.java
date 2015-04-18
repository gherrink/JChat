/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.db.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
@Entity
public class User
{
    private static final String ADD_4_PASSWORD = "dkd938cmeu3mdDDE83";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(length = 50, name = "user_name", unique = true, nullable = false)
    private String user;
    
    @Column(length = 32, nullable = false)
    private String password;
    
    @Column(length = 80, unique = true, nullable = false)
    private String mail;
    
    public User()
    {
        this(null, null, null);
    }
    
    public User(String user, String mail, String password)
    {
        this.user = user;
        this.mail = mail;
        this.password = password;
    }
    
    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public static String hashPassword(String password, String mail)
    {
        String str = password + ADD_4_PASSWORD + mail;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger number = new BigInteger(1, md.digest(str.getBytes()));
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException ex)
        {}
        return password;
    }

    /**
     * @return the mail
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
}
