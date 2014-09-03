/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wittakarn.domain;

import lombok.ToString;

/**
 *
 * @author wittakarn
 */
@ToString
public class SchedulerItem{
    private static final long serialVersionUID = 1L;
    
    private String testTotDay;
    private String testStartDate;
    
    /**
     * @return the testTotDay
     */
    public String getTestTotDay() {
        return testTotDay;
    }

    /**
     * @param testTotDay the testTotDay to set
     */
    public void setTestTotDay(String testTotDay) {
        this.testTotDay = testTotDay;
    }

    /**
     * @return the testStartDate
     */
    public String getTestStartDate() {
        return testStartDate;
    }

    /**
     * @param testStartDate the testStartDate to set
     */
    public void setTestStartDate(String testStartDate) {
        this.testStartDate = testStartDate;
    }

}
