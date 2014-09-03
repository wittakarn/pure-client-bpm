 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wittakarn.bpm.domain;

import java.util.HashMap;
import lombok.ToString;

/**
 *
 * @author wittakarn
 */
@ToString(callSuper = true)
public class LeaveItem{
    private static final long serialVersionUID = 1L;
    
    private String leaveTotDay;
    private String leaveIssue;
    private String approve;

    /**
     * @return the leaveTotDay
     */
    public String getLeaveTotDay() {
        return leaveTotDay;
    }

    /**
     * @param leaveTotDay the leaveTotDay to set
     */
    public void setLeaveTotDay(String leaveTotDay) {
        this.leaveTotDay = leaveTotDay;
    }

    /**
     * @return the leaveIssue
     */
    public String getLeaveIssue() {
        return leaveIssue;
    }

    /**
     * @param leaveIssue the leaveIssue to set
     */
    public void setLeaveIssue(String leaveIssue) {
        this.leaveIssue = leaveIssue;
    }
    
    /**
     * @return the approve
     */
    public String getApprove() {
        return approve;
    }

    /**
     * @param approve the approve to set
     */
    public void setApprove(String approve) {
        this.approve = approve;
    }
    
}
