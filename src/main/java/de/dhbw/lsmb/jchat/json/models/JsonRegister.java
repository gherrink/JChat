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
public class JsonRegister
{
    private String user;
    private String mail;
    private String password;
    private String passwordRepead;
    
    public JsonRegister(String user, String mail, String password, String passwordRepead) {
        this.user = user;
        this.mail = mail;
        this.password = password;
        this.passwordRepead = passwordRepead;
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

    /**
     * @return the passwordRepead
     */
    public String getPasswordRepead()
    {
        return passwordRepead;
    }

    /**
     * @param passwordRepead the passwordRepead to set
     */
    public void setPasswordRepead(String passwordRepead)
    {
        this.passwordRepead = passwordRepead;
    }
    
}
