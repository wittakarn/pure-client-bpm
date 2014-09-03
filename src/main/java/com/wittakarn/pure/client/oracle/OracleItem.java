package com.wittakarn.pure.client.oracle;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import lombok.ToString;

/**
 *
 * @author wittakarn
 */
@ToString
public class OracleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String password;
    private String taskId;
    private String processId;
    private String processName;
    private String taskDefinitionName;
    private String compositeInstanceId;
    private String compositeName;
    private String compositeVersion;
    private String state;			//	ready : claim : lock 
    private String assigneesId;
    private String assigneesType;
    private String owner;
    private String outcome;
    private String stepArrDtm;		//	create date
    private String stepClmDtm;		//	claim date
    private boolean admin;
    private HashMap<String, Object> updateField;
    private List<String> filterField;
    private List<String> returnField;
    private List<String> orderClause;
    private List<String> taskDefinitionNames;
    
    /**
     * @return the taskDefinitionName
     */
    public String getTaskDefinitionName() {
        return taskDefinitionName;
    }

    /**
     * @param taskDefinitionName the taskDefinitionName to set
     */
    public void setTaskDefinitionName(String taskDefinitionName) {
        this.taskDefinitionName = taskDefinitionName;
    }

    /**
     * @return the compositeInstanceId
     */
    public String getCompositeInstanceId() {
        return compositeInstanceId;
    }

    /**
     * @param compositeInstanceId the compositeInstanceId to set
     */
    public void setCompositeInstanceId(String compositeInstanceId) {
        this.compositeInstanceId = compositeInstanceId;
    }

    /**
     * @return the compositeName
     */
    public String getCompositeName() {
        return compositeName;
    }

    /**
     * @param compositeName the compositeName to set
     */
    public void setCompositeName(String compositeName) {
        this.compositeName = compositeName;
    }

    /**
     * @return the compositeVersion
     */
    public String getCompositeVersion() {
        return compositeVersion;
    }

    /**
     * @param compositeVersion the compositeVersion to set
     */
    public void setCompositeVersion(String compositeVersion) {
        this.compositeVersion = compositeVersion;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the assigneesId
     */
    public String getAssigneesId() {
        return assigneesId;
    }

    /**
     * @param assigneesId the assigneesId to set
     */
    public void setAssigneesId(String assigneesId) {
        this.assigneesId = assigneesId;
    }

    /**
     * @return the assigneesType
     */
    public String getAssigneesType() {
        return assigneesType;
    }

    /**
     * @param assigneesType the assigneesType to set
     */
    public void setAssigneesType(String assigneesType) {
        this.assigneesType = assigneesType;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the outcome
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    /**
     * @return the stepArrDtm
     */
    public String getStepArrDtm() {
        return stepArrDtm;
    }

    /**
     * @param stepArrDtm the stepArrDtm to set
     */
    public void setStepArrDtm(String stepArrDtm) {
        this.stepArrDtm = stepArrDtm;
    }

    /**
     * @return the stepClmDtm
     */
    public String getStepClmDtm() {
        return stepClmDtm;
    }

    /**
     * @param stepClmDtm the stepClmDtm to set
     */
    public void setStepClmDtm(String stepClmDtm) {
        this.stepClmDtm = stepClmDtm;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

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
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the processId
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * @param processId the processId to set
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * @return the processName
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * @param processName the processName to set
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * @return the updateField
     */
    public HashMap<String, Object> getUpdateField() {
        return updateField;
    }

    /**
     * @param updateField the updateField to set
     */
    public void setUpdateField(HashMap<String, Object> updateField) {
        this.updateField = updateField;
    }

    /**
     * @return the filterField
     */
    public List<String> getFilterField() {
        return filterField;
    }

    /**
     * @param filterField the filterField to set
     */
    public void setFilterField(List<String> filterField) {
        this.filterField = filterField;
    }

    /**
     * @return the returnField
     */
    public List<String> getReturnField() {
        return returnField;
    }

    /**
     * @param returnField the returnField to set
     */
    public void setReturnField(List<String> returnField) {
        this.returnField = returnField;
    }

    /**
     * @return the orderClause
     */
    public List<String> getOrderClause() {
        return orderClause;
    }

    /**
     * @param orderClause the orderClause to set
     */
    public void setOrderClause(List<String> orderClause) {
        this.orderClause = orderClause;
    }

    /**
     * @return the taskDefinitionNames
     */
    public List<String> getTaskDefinitionNames() {
        return taskDefinitionNames;
    }

    /**
     * @param taskDefinitionNames the taskDefinitionNames to set
     */
    public void setTaskDefinitionNames(List<String> taskDefinitionNames) {
        this.taskDefinitionNames = taskDefinitionNames;
    }

}
