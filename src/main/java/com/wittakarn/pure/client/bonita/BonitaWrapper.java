/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wittakarn.pure.client.bonita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.PlatformAPI;
import org.bonitasoft.engine.api.PlatformAPIAccessor;
import org.bonitasoft.engine.api.PlatformLoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProcessRuntimeAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.data.DataNotFoundException;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceCriterion;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceNotFoundException;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ArchivedProcessInstance;
import org.bonitasoft.engine.bpm.process.ArchivedProcessInstancesSearchDescriptor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.PlatformSession;

/**
 *
 * @author Wittakarn
 */
public class BonitaWrapper {
    
    /**
     * Platform administrator user name
     */
    private static final String PLATFORM_PASSWORD = "platform";

    /**
     * Platform administrator password
     */
    private static final String PLATFORM_ADMIN = "platformAdmin";
    
    /**
     * The maximum number of elements retrieved by paged requests
     */
    private static int PAGE_SIZE = 100;
    
    static{
        System.setProperty("bonita.home", "D:\\bonita");
    }
    
    /**
     * List all pending tasks for the logged user
     * 
     * @throws BonitaException
     *             if an exception occurs when listing the pending tasks
     */
    public static List<HashMap<String, Object>> listPendingTasks(String user, String password) throws BonitaException {
        // login
        APISession session = doTenantLogin(user, password);
        try {
            ProcessAPI processAPI = getProcessAPI(session);
            // the result will be retrieved by pages of PAGE_SIZE size
            int startIndex = 0;
            int page = 1;
            List<HumanTaskInstance> pendingTasks = null;
            
            // get all tasks.
            pendingTasks = processAPI.getPendingHumanTaskInstances(session.getUserId(), startIndex, PAGE_SIZE, ActivityInstanceCriterion.LAST_UPDATE_ASC);
            // print all tasks.
            return generateResponseTask(page, pendingTasks, processAPI);
        } finally {
            // logout
            doTenantLogout(session);
        }
    }

    /**
     * Print a tasks page
     * 
     * @param page
     *            the page number
     * @param pendingTasks
     *            the page content
     */
    private static List<HashMap<String, Object>> generateResponseTask(int page, List<HumanTaskInstance> pendingTasks, ProcessAPI processAPI) throws DataNotFoundException {
        List<HashMap<String, Object>> hashMapTasks = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hashMapTask;
        if (pendingTasks.isEmpty()) {
            if (page == 1) {
                System.out.println("There are no pending tasks!");
            }
        } else {
            System.out.println("----- Page " + page + "-----");
        }
        for (HumanTaskInstance task : pendingTasks) {
            StringBuilder stb = new StringBuilder();
            stb.append("id: ");
            stb.append(task.getId());
            stb.append(", process instance id: ");
            stb.append(task.getRootContainerId());
            stb.append(", task name: ");
            stb.append(task.getName());
            
            stb.append(", state: ");
            stb.append(task.getState());
            stb.append(", task processDefinitionId: ");
            stb.append(task.getProcessDefinitionId());
            stb.append(", task ParentContainerId: ");
            stb.append(task.getParentContainerId());
            stb.append(", task ParentProcessInstanceId: ");
            stb.append(task.getParentProcessInstanceId());
            
            stb.append(", approve: ");
            stb.append(processAPI.getActivityDataInstance("approve", task.getId()).getValue());
            System.out.println(stb.toString());
            
            hashMapTask = new HashMap<String, Object>();
            hashMapTask.put("taskId", task.getId());
            hashMapTask.put("taskDefinitionName", task.getName());
            hashMapTask.put("processId", task.getProcessDefinitionId());
            hashMapTask.put("isApprove", processAPI.getActivityDataInstance("approve", task.getId()).getValue());
            hashMapTasks.add(hashMapTask);
        }
        
        return hashMapTasks;
    }
    
    /**
     * Execute the task chosen by the user
     * 
     * @param user
     * @param password
     * @param taskId
     * @throws BonitaException
     *             if an exception occurs when executing the task
     * @throws IOException
     *             if an exception occurs when reading the task id to be executed
     */
    public static void executeATask(String user, String password, Long taskId) throws BonitaException, IOException {

        ProcessRuntimeAPI processAPI;
        HumanTaskInstance taskToExecute;
        
        // login
        APISession session = doTenantLogin(user, password);
        try {
            processAPI = getProcessAPI(session);
            // retrieve the task to be executed in order to print information such as task name and process instance id
            // if you don't need this information you can assign and execute it directly without retrieving it
            taskToExecute = processAPI.getHumanTaskInstance(taskId);
            // assign the task
            processAPI.assignUserTask(taskToExecute.getId(), session.getUserId());
            System.out.println("Task '" + taskToExecute.getName() + "' of process instance '" + taskToExecute.getRootContainerId() + "' assigned to '"
                    + session.getUserName() + ".");

            // execute the task
            processAPI.executeFlowNode(taskToExecute.getId());
            System.out.println("Task '" + taskToExecute.getName() + "' of process instance '" + taskToExecute.getRootContainerId() + "' executed by '"
                    + session.getUserName() + ".");
        } catch (ActivityInstanceNotFoundException e) {
            // catch ActivityInstanceNotFoundException to cover the case where the user enter an invalid tasks id
            System.out.println("No task found with id " + taskId);
        } finally {
            // logout
            doTenantLogout(session);
        }
    }
    
    /**
     * Do all necessary actions to create the Bonita platform
     * 
     * @throws BonitaException
     *             if an exception occurs when creating the platform
     */
    public static void createPlatform() throws BonitaException {
        // login as platform administrator
        PlatformSession session = doPlatformLogin(PLATFORM_ADMIN, PLATFORM_PASSWORD);
        try {
            
            System.out.println("Creating and initializing the platform ...");
            // create and initialize the platform
            getPlatformAPI(session).createAndInitializePlatform();
            System.out.println("Platform created and initialized!");

            System.out.println("Starting node ...");
            // start the node (make scheduler service to start)
            getPlatformAPI(session).startNode();
            System.out.println("Node started!");
        } finally {
            // logout
            doPlatformLogout(session);
        }
    }
    
    /**
     * Get the page of archived process instances based on the start index
     * 
     * @param session
     *            the current session
     * @param startIndex
     *            the index of the first element of the page
     * @return the page of archived process instances based on the start index
     * @throws BonitaException
     */
    private static SearchResult<ArchivedProcessInstance> getArchivedProcessInstancePage(APISession session, int startIndex) throws BonitaException {
        // create a new SeachOptions with given start index and PAGE_SIZE as max number of elements
        SearchOptionsBuilder optionsBuilder = new SearchOptionsBuilder(startIndex, PAGE_SIZE);
        // when process instances are archived the original process instance id is supplied by SOURCE_OBJECT_ID,
        // so the result will be sort by the SOURCE_OBJECT_ID
        optionsBuilder.sort(ArchivedProcessInstancesSearchDescriptor.SOURCE_OBJECT_ID, Order.ASC);
        // perform the request and return the result;
        return getProcessAPI(session).searchArchivedProcessInstances(optionsBuilder.done());
    }
    
    public static void doTenantLogout(APISession session) throws BonitaException {
        getLoginAPI().logout(session);
        System.out.println("User '" + session.getUserName() + "' has logged out!");
    }
    
    private static LoginAPI getLoginAPI() throws BonitaException {
        return TenantAPIAccessor.getLoginAPI();
    }
    
    private static PlatformLoginAPI getPlaformLoginAPI() throws BonitaException {
        return PlatformAPIAccessor.getPlatformLoginAPI();
    }
    
    public static PlatformSession doPlatformLogin(String platformUsername, String password) throws BonitaException {
        return getPlaformLoginAPI().login(platformUsername, password);
    }
    
    public static void doPlatformLogout(PlatformSession session) throws BonitaException {
        getPlaformLoginAPI().logout(session);
    }
    
    private static ProcessAPI getProcessAPI(APISession session) throws BonitaException {
        return TenantAPIAccessor.getProcessAPI(session);
    }
    
    public static APISession doTenantLogin(String username, String password) throws BonitaException {
        APISession session = getLoginAPI().login(username, password);
        System.out.println("User '" + username + "' has logged in!");
        return session;
    }
    
    private static PlatformAPI getPlatformAPI(PlatformSession platformSession) throws BonitaException {
        return PlatformAPIAccessor.getPlatformAPI(platformSession);
    }
    
    private static HashMap<String, Object> getActivityData(ProcessRuntimeAPI processAPI, Long id) throws BonitaException{
        HashMap<String, Object> hash;
        try{
            hash = new HashMap<String, Object>();
            System.out.println("Get approve value");
            System.out.println("approve : " + processAPI.getActivityDataInstance("approve", id).getValue());
            hash.put("approve", processAPI.getActivityDataInstance("approve", id).getValue());
            
            return hash;
        }finally {
            hash = null;
        }
    }
    
}
