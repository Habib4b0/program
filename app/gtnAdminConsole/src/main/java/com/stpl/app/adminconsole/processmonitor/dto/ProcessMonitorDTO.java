/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class ProcessMonitorDTO {

    private String processName;
    private HelperDTO processType;
    private String hours1;
    private String hours2;
    private String hours3;
    private String minutes1;
    private String minutes2;
    private String minutes3;
    private String email;
    private String message;
    private String calender;
    private String modifiedDate;
    private String modifiedBy;
    private Date startDate;
    private Date endDate;
    private int sid;
    private String processDisplayName = StringUtils.EMPTY;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public HelperDTO getProcessType() {
        return processType;
    }

    public void setProcessType(HelperDTO processType) {
        this.processType = processType;
    }

    public String getHours1() {
        return hours1;
    }

    public void setHours1(String hours1) {
        this.hours1 = hours1;
    }

    public String getHours2() {
        return hours2;
    }

    public void setHours2(String hours2) {
        this.hours2 = hours2;
    }

    public String getHours3() {
        return hours3;
    }

    public void setHours3(String hours3) {
        this.hours3 = hours3;
    }

    public String getMinutes1() {
        return minutes1;
    }

    public void setMinutes1(String minutes1) {
        this.minutes1 = minutes1;
    }

    public String getMinutes2() {
        return minutes2;
    }

    public void setMinutes2(String minutes2) {
        this.minutes2 = minutes2;
    }

    public String getMinutes3() {
        return minutes3;
    }

    public void setMinutes3(String minutes3) {
        this.minutes3 = minutes3;
    }

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCalender() {
        return calender;
    }

    public void setCalender(String calender) {
        this.calender = calender;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getProcessDisplayName() {
        return processDisplayName;
    }

    public void setProcessDisplayName(String processDisplayName) {
        this.processDisplayName = processDisplayName;
    }

}
