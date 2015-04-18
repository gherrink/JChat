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
public enum Action
{
    REGISTER("register"), MESSAGE("message"), STATUS("status"), LOGIN("login"),
    LOGOUT("logout");
    
    private final String action;
    
    private Action(String action) {
        this.action = action;
    }
    
    public String toString() {
        return action;
    }
    
}
