/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.ui.Component;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.util.Constants;

/**
 *
 * @author maheshj
 */
public class ContractResultDTO {

    private String projectionId = StringUtils.EMPTY;
    private String workflowStatus = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private HelperDTO contractType = new HelperDTO(0, Constants.SELECT_ONE);
    private Date contStartDate;
    private Date contEndDate;
    private String cfpName = StringUtils.EMPTY;
    private String cfpNo = StringUtils.EMPTY;
    private String cfpId = StringUtils.EMPTY;
    private String cfpStatus = StringUtils.EMPTY;
    private Date cfpStartDate;
    private Date cfpEndDate;
    private String ifpName = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpId = StringUtils.EMPTY;
    private String ifpStatus = StringUtils.EMPTY;
    private Date ifpStartDate;
    private Date ifpEndDate;

    private String pSName = StringUtils.EMPTY;
    private String pSNo = StringUtils.EMPTY;
    private String pSId = StringUtils.EMPTY;
    private String pSStatus = StringUtils.EMPTY;
    private Date pStartDate;
    private Date pSEndDate;
    private String rSName = StringUtils.EMPTY;
    private String rSNo = StringUtils.EMPTY;
    private String rSId = StringUtils.EMPTY;
    private String rARCategory = StringUtils.EMPTY;
    private String rSStatus = StringUtils.EMPTY;
    private Date rStartDate;
    private Date rSEndDate;

    private HelperDTO status = new HelperDTO();
    private Date compStartDate;
    private Date compEndDate;
    private String cfpContSid = StringUtils.EMPTY;
    private String rsContSid = StringUtils.EMPTY;
    private String ifpContSid = StringUtils.EMPTY;
    private String psContSid = StringUtils.EMPTY;
    private Boolean checkRecord = false;
    private String contractMasterSid = StringUtils.EMPTY;
    private Integer userId = new Integer(1);
    private Integer sessionId = new Integer(1);
    private String projectionIdLink;
    private String tempVarOne = StringUtils.EMPTY;
    private String tempVarTwo = StringUtils.EMPTY;
    private String statusString=StringUtils.EMPTY;

    private HelperDTO statusDescription = new HelperDTO(0, Constants.SELECT_ONE);

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(String contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public Date getCompStartDate() {
        return compStartDate;
    }

    public void setCompStartDate(Date compStartDate) {
        this.compStartDate = compStartDate;
    }

    public Date getCompEndDate() {
        return compEndDate;
    }

    public void setCompEndDate(Date compEndDate) {
        this.compEndDate = compEndDate;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public Boolean isCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Date getContStartDate() {
        return contStartDate;
    }

    public void setContStartDate(Date contStartDate) {
        this.contStartDate = contStartDate;
    }

    public Date getContEndDate() {
        return contEndDate;
    }

    public void setContEndDate(Date contEndDate) {
        this.contEndDate = contEndDate;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getCfpNo() {
        return cfpNo;
    }

    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getpSName() {
        return pSName;
    }

    public void setpSName(String pSName) {
        this.pSName = pSName;
    }

    public String getpSNo() {
        return pSNo;
    }

    public void setpSNo(String pSNo) {
        this.pSNo = pSNo;
    }

    public String getrSName() {
        return rSName;
    }

    public void setrSName(String rSName) {
        this.rSName = rSName;
    }

    public String getrSNo() {
        return rSNo;
    }

    public void setrSNo(String rSNo) {
        this.rSNo = rSNo;
    }

    public String getrSId() {
        return rSId;
    }

    public void setrSId(String rSId) {
        this.rSId = rSId;
    }

    public String getrARCategory() {
        return rARCategory;
    }

    public void setrARCategory(String rARCategory) {
        this.rARCategory = rARCategory;
    }

    public HelperDTO getStatus() {
        return status;
    }

    public void setStatus(HelperDTO status) {
        this.status = status;
    }

    public String getCfpContSid() {
        return cfpContSid;
    }

    public void setCfpContSid(String cfpContSid) {
        this.cfpContSid = cfpContSid;
    }

    public String getRsContSid() {
        return rsContSid;
    }

    public void setRsContSid(String rsContSid) {
        this.rsContSid = rsContSid;
    }

    public String getIfpContSid() {
        return ifpContSid;
    }

    public void setIfpContSid(String ifpContSid) {
        this.ifpContSid = ifpContSid;
    }

    public String getPsContSid() {
        return psContSid;
    }

    public void setPsContSid(String psContSid) {
        this.psContSid = psContSid;
    }

    public String getCfpId() {
        return cfpId;
    }

    public void setCfpId(String cfpId) {
        this.cfpId = cfpId;
    }

    public String getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(String cfpStatus) {
        this.cfpStatus = cfpStatus;
    }

    public Date getCfpStartDate() {
        return cfpStartDate;
    }

    public void setCfpStartDate(Date cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }

    public Date getCfpEndDate() {
        return cfpEndDate;
    }

    public void setCfpEndDate(Date cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public Date getIfpStartDate() {
        return ifpStartDate;
    }

    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    public Date getIfpEndDate() {
        return ifpEndDate;
    }

    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public String getpSId() {
        return pSId;
    }

    public void setpSId(String pSId) {
        this.pSId = pSId;
    }

    public String getpSStatus() {
        return pSStatus;
    }

    public void setpSStatus(String pSStatus) {
        this.pSStatus = pSStatus;
    }

    public Date getpStartDate() {
        return pStartDate;
    }

    public void setpStartDate(Date pStartDate) {
        this.pStartDate = pStartDate;
    }

    public Date getpSEndDate() {
        return pSEndDate;
    }

    public void setpSEndDate(Date pSEndDate) {
        this.pSEndDate = pSEndDate;
    }

    public String getrSStatus() {
        return rSStatus;
    }

    public void setrSStatus(String rSStatus) {
        this.rSStatus = rSStatus;
    }

    public Date getrStartDate() {
        return rStartDate;
    }

    public void setrStartDate(Date rStartDate) {
        this.rStartDate = rStartDate;
    }

    public Date getrSEndDate() {
        return rSEndDate;
    }

    public void setrSEndDate(Date rSEndDate) {
        this.rSEndDate = rSEndDate;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getProjectionIdLink() {
        return projectionIdLink;
    }

    public void setProjectionIdLink(String projectionIdLink) {
        this.projectionIdLink = projectionIdLink;
    }

    public String getTempVarOne() {
        return tempVarOne;
    }

    public void setTempVarOne(String tempVarOne) {
        this.tempVarOne = tempVarOne;
    }

    public String getTempVarTwo() {
        return tempVarTwo;
    }

    public void setTempVarTwo(String tempVarTwo) {
        this.tempVarTwo = tempVarTwo;
    }

    public HelperDTO getContractType() {
        return contractType;
    }

    public void setContractType(HelperDTO contractType) {
        this.contractType = contractType;
    }

    public HelperDTO getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(HelperDTO statusDescription) {
        this.statusDescription = statusDescription;
}

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

}
