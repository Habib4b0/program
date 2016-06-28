/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.common.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shyam.d
 */
public class SessionDTO {
    
    private int systemId;
    private String companyId;
    private String companyNo;
    private String companyName;
    private String parentSysId;
    private String mode;
    private String parentLookUpId;
    private String uiSessionId;
    private String sessionDate;
    private String isSave;
    private String userId;
    private String additionalNotes= StringUtils.EMPTY;
    private String flagForDeduction;
    

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getUiSessionId() {
        return uiSessionId;
    }

    public void setUiSessionId(String uiSessionId) {
        this.uiSessionId = uiSessionId;
    }

    public String getParentLookUpId() {
        return parentLookUpId;
    }

    public void setParentLookUpId(String parentLookUpId) {
        this.parentLookUpId = parentLookUpId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    public String getParentSysId() {
        return parentSysId;
    }

    public void setParentSysId(String parentSysId) {
        this.parentSysId = parentSysId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getFlagForDeduction() {
        return flagForDeduction;
    }

    public void setFlagForDeduction(String flagForDeduction) {
        this.flagForDeduction = flagForDeduction;
    }

    }
