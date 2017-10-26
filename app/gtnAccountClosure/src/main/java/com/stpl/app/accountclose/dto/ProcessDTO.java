/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author hazi.s
 */
 public class ProcessDTO {  
     
    private Integer processSid = 0;
    private String processName = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private Date startDate ;
    private Date endDate ;
    private String calendar = StringUtils.EMPTY;
    private String frequencyRadio = StringUtils.EMPTY;
    private Date lastRunDate;
    private Date modifiedDate ;
    private String modifiedBy = StringUtils.EMPTY;
    private String hoursOne = StringUtils.EMPTY;
    private String hoursTwo = StringUtils.EMPTY;
    private String hoursThree = StringUtils.EMPTY;
    private String minutesOne = StringUtils.EMPTY;
    private String minutesTwo = StringUtils.EMPTY;
    private String minutesThree = StringUtils.EMPTY;
    private String runHours = StringUtils.EMPTY;
    private String runMinutes = StringUtils.EMPTY;
    private String isSelected = StringUtils.EMPTY;
    
    
    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
 

    public String getRunHours() {
        return runHours;
    }

    public void setRunHours(String runHours) {
        this.runHours = runHours;
    }

    public String getRunMinutes() {
        return runMinutes;
    }

    public void setRunMinutes(String runMinutes) {
        this.runMinutes = runMinutes;
    }
    
    public String getHoursOne() {
        return hoursOne;
    }

    public void setHoursOne(String hoursOne) {
        this.hoursOne = hoursOne;
    }

    public String getHoursTwo() {
        return hoursTwo;
    }

    public void setHoursTwo(String hoursTwo) {
        this.hoursTwo = hoursTwo;
    }

    public String getHoursThree() {
        return hoursThree;
    }

    public void setHoursThree(String hoursThree) {
        this.hoursThree = hoursThree;
    }

    public String getMinutesOne() {
        return minutesOne;
    }

    public void setMinutesOne(String minutesOne) {
        this.minutesOne = minutesOne;
    }

    public String getMinutesTwo() {
        return minutesTwo;
    }

    public void setMinutesTwo(String minutesTwo) {
        this.minutesTwo = minutesTwo;
    }

    public String getMinutesThree() {
        return minutesThree;
    }

    public void setMinutesThree(String minutesThree) {
        this.minutesThree = minutesThree;
    }
    
    public Integer getProcessSid() {
        return processSid;
    }

    public void setProcessSid(Integer processSid) {
        this.processSid = processSid;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getFrequencyRadio() {
        return frequencyRadio;
    }

    public void setFrequencyRadio(String frequencyRadio) {
        this.frequencyRadio = frequencyRadio;
    }

    public Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

   
 
     
     
     
     
     
     
     
 
 }