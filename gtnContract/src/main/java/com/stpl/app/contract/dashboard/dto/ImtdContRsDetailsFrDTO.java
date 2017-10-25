/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Shrihariharan
 */
public class ImtdContRsDetailsFrDTO {
    private int imtdContRsDetailsFrSid;
    private int imtdContRsDetailsSid;
    private int contRsDetailsFrSid;
    private int contRsDetailsSid;
    private int itemMasterSid;
    private int formulaId;
    private String formulaName=StringUtils.EMPTY;
    private String formulaMethodId=StringUtils.EMPTY;
    private String operation =StringUtils.EMPTY;
    private String userId=StringUtils.EMPTY;
    private String sessionId=StringUtils.EMPTY;

    public int getImtdContRsDetailsFrSid() {
        return imtdContRsDetailsFrSid;
    }

    public void setImtdContRsDetailsFrSid(int imtdContRsDetailsFrSid) {
        this.imtdContRsDetailsFrSid = imtdContRsDetailsFrSid;
    }

    public int getImtdContRsDetailsSid() {
        return imtdContRsDetailsSid;
    }

    public void setImtdContRsDetailsSid(int imtdContRsDetailsSid) {
        this.imtdContRsDetailsSid = imtdContRsDetailsSid;
    }

    public int getContRsDetailsFrSid() {
        return contRsDetailsFrSid;
    }

    public void setContRsDetailsFrSid(int contRsDetailsFrSid) {
        this.contRsDetailsFrSid = contRsDetailsFrSid;
    }

    public int getContRsDetailsSid() {
        return contRsDetailsSid;
    }

    public void setContRsDetailsSid(int contRsDetailsSid) {
        this.contRsDetailsSid = contRsDetailsSid;
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
