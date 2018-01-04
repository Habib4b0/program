/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class ContractHolderDTO {

    private String contractHolderId = StringUtils.EMPTY;
    private String contractHolderNo = StringUtils.EMPTY;
    private String contractHolderName = StringUtils.EMPTY;
    private String contractHolderStatus = StringUtils.EMPTY;
    private String contractHolderType = StringUtils.EMPTY;

    public String getContractHolderId() {
        return contractHolderId;
    }

    public void setContractHolderId(String contractHolderId) {
        this.contractHolderId = contractHolderId;
    }

    public String getContractHolderNo() {
        return contractHolderNo;
    }

    public void setContractHolderNo(String contractHolderNo) {
        this.contractHolderNo = contractHolderNo;
    }

    public String getContractHolderName() {
        return contractHolderName;
    }

    public void setContractHolderName(String contractHolderName) {
        this.contractHolderName = contractHolderName;
    }

    public String getContractHolderStatus() {
        return contractHolderStatus;
    }

    public void setContractHolderStatus(String contractHolderStatus) {
        this.contractHolderStatus = contractHolderStatus;
    }

    public String getContractHolderType() {
        return contractHolderType;
    }

    public void setContractHolderType(String contractHolderType) {
        this.contractHolderType = contractHolderType;
    }
}
