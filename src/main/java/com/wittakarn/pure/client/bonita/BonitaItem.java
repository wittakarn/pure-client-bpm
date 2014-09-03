/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wittakarn.pure.client.bonita;

import java.io.Serializable;

/**
 *
 * @author wittakarn
 */
public class BonitaItem implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String password;
    private String activityInstance;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the activityInstance
     */
    public String getActivityInstance() {
        return activityInstance;
    }

    /**
     * @param activityInstance the activityInstance to set
     */
    public void setActivityInstance(String activityInstance) {
        this.activityInstance = activityInstance;
    }
    
}
