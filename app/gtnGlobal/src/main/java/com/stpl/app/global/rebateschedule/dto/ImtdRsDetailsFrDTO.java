/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Shrihariharan
 */
public class ImtdRsDetailsFrDTO {
    private int imtdRsDetailsFrSid;
    private int imtdRsDetailsSid;
    private int rsDetailsFrSid;
    private int rsDetailsSid;
    private int itemMasterSid;
    private int formulaId;
    private String formulaName=StringUtils.EMPTY;
    private String formulaMethodId=StringUtils.EMPTY;
    private String operation =StringUtils.EMPTY;
    private String userId=StringUtils.EMPTY;
    private String sessionId=StringUtils.EMPTY;

    public int getImtdRsDetailsFrSid() {
        return imtdRsDetailsFrSid;
    }

    public void setImtdRsDetailsFrSid(int imtdRsDetailsFrSid) {
        this.imtdRsDetailsFrSid = imtdRsDetailsFrSid;
    }

    public int getImtdRsDetailsSid() {
        return imtdRsDetailsSid;
    }

    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        this.imtdRsDetailsSid = imtdRsDetailsSid;
    }

    public int getRsDetailsFrSid() {
        return rsDetailsFrSid;
    }

    public void setRsDetailsFrSid(int rsDetailsFrSid) {
        this.rsDetailsFrSid = rsDetailsFrSid;
    }

    public int getRsDetailsSid() {
        return rsDetailsSid;
    }

    public void setRsDetailsSid(int rsDetailsSid) {
        this.rsDetailsSid = rsDetailsSid;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public int getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaMethodId() {
        return formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        this.formulaMethodId = formulaMethodId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }
    
    
}
