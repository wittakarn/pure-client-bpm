/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wittakarn.client;

import com.wittakarn.bpm.domain.LeaveItem;
import com.wittakarn.pure.client.bonita.BonitaItem;
import com.wittakarn.pure.client.bonita.BonitaWrapper;
import com.wittakarn.pure.client.oracle.OracleItem;
import com.wittakarn.pure.client.oracle.OracleWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import org.bonitasoft.engine.exception.BonitaException;

/**
 *
 * @author Wittakarn
 */
public class App {
    public static void main(String args[]){
    }
    
    public List<LeaveItem> searchTask(String vendor){
        List<LeaveItem> leaveItems = new ArrayList<LeaveItem>();
        
        if(vendor.equals("Oracle")){
            try {
                OracleItem oracleItem = new OracleItem();
                oracleItem.setUserId("xxx");
                oracleItem.setPassword("yyy");
                searchOracleTask(oracleItem);
            } catch (WorkflowException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(vendor.equals("Bonita")){
            BonitaItem bonitaItem = new BonitaItem();
            bonitaItem.setUserId("xxx");
            bonitaItem.setPassword("yyy");
            try {
                searchBonitaTask(bonitaItem);
            } catch (BonitaException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //map list of HashMap to listof leaveItem.
        
        return leaveItems;
    }
    
    private List<LeaveItem> searchBonitaTask(BonitaItem bonitaItem) throws BonitaException{
        
        //getting all tasks from bonita. 
        List<HashMap<String, Object>> hashs = (List<HashMap<String, Object>>) BonitaWrapper.listPendingTasks(bonitaItem.getUserId(), bonitaItem.getPassword());
        
        //map list of hashmap to leave items.
        
        return null;
    }
    
    private List<LeaveItem> searchOracleTask(OracleItem oracleItem) throws WorkflowException{
        
        //getting all tasks from oracle.
        List<HashMap<String, Object>> hashs = (List<HashMap<String, Object>>) OracleWrapper.searchTask(oracleItem.getUserId(), oracleItem.getPassword());
        
        //map list of hashmap to leave items.
        
        return null;
    }
}
