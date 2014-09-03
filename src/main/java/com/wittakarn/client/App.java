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
import java.util.HashMap;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.bonitasoft.engine.exception.BonitaException;

/**
 *
 * @author Wittakarn
 */
public class App {
    public static void main(String args[]){
        
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
