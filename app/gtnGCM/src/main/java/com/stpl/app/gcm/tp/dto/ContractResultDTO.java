/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

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
    private HelperDTO cfpStatus = new HelperDTO(0, Constants.SELECT_ONE);
    private Date cfpStartDate;
    private Date cfpEndDate;
    private String ifpName = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpId = StringUtils.EMPTY;
    private HelperDTO ifpStatus = new HelperDTO(0, Constants.SELECT_ONE);
    private Date ifpStartDate;
    private Date ifpEndDate;

    private String pSName = StringUtils.EMPTY;
    private String pSNo = StringUtils.EMPTY;
    private String pSId = StringUtils.EMPTY;
    private HelperDTO pSStatus = new HelperDTO(0, Constants.SELECT_ONE);
    private Date pStartDate;
    private Date pSEndDate;
    private String rSName = StringUtils.EMPTY;
    private String rSNo = StringUtils.EMPTY;
    private String rSId = StringUtils.EMPTY;
    private String rARCategory = StringUtils.EMPTY;
    private HelperDTO rSStatus = new HelperDTO(0, Constants.SELECT_ONE);
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
    private String tpstatus=StringUtils.EMPTY;

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
        return compStartDate == null ? null : (Date) compStartDate.clone();
    }

    public void setCompStartDate(Date compStartDate) {
        this.compStartDate = compStartDate == null ? null : (Date) compStartDate.clone();
    }

    public Date getCompEndDate() {
        return compEndDate == null ? null : (Date) compEndDate.clone();
    }

    public void setCompEndDate(Date compEndDate) {
        this.compEndDate = compEndDate == null ? null : (Date) compEndDate.clone();
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
        return contStartDate == null ? null : (Date) contStartDate.clone();
    }

    public void setContStartDate(Date contStartDate) {
        this.contStartDate = contStartDate == null ? null : (Date) contStartDate.clone();
    }

    public Date getContEndDate() {
        return contEndDate == null ? null : (Date) contEndDate.clone();
    }

    public void setContEndDate(Date contEndDate) {
        this.contEndDate = contEndDate == null ? null : (Date) contEndDate.clone();
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

    public HelperDTO getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(HelperDTO cfpStatus) {
        this.cfpStatus = cfpStatus;
    }

    public Date getCfpStartDate() {
        return cfpStartDate == null ? null : (Date) cfpStartDate.clone();
    }

    public void setCfpStartDate(Date cfpStartDate) {
        this.cfpStartDate = cfpStartDate == null ? null : (Date) cfpStartDate.clone();
    }

    public Date getCfpEndDate() {
        return cfpEndDate == null ? null : (Date) cfpEndDate.clone();
    }

    public void setCfpEndDate(Date cfpEndDate) {
        this.cfpEndDate = cfpEndDate == null ? null : (Date) cfpEndDate.clone();
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public Date getIfpStartDate() {
        return ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public Date getIfpEndDate() {
        return ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public String getpSId() {
        return pSId;
    }

    public void setpSId(String pSId) {
        this.pSId = pSId;
    }
    public Date getpStartDate() {
        return pStartDate == null ? null : (Date) pStartDate.clone();
    }

    public void setpStartDate(Date pStartDate) {
        this.pStartDate = pStartDate == null ? null : (Date) pStartDate.clone();
    }

    public Date getpSEndDate() {
        return pSEndDate == null ? null : (Date) pSEndDate.clone();
    }

    public void setpSEndDate(Date pSEndDate) {
        this.pSEndDate = pSEndDate == null ? null : (Date) pSEndDate.clone();
    }

    public Date getrStartDate() {
        return rStartDate == null ? null : (Date) rStartDate.clone();
    }

    public void setrStartDate(Date rStartDate) {
        this.rStartDate = rStartDate == null ? null : (Date) rStartDate.clone();
    }

    public Date getrSEndDate() {
        return rSEndDate == null ? null : (Date) rSEndDate.clone();
    }

    public void setrSEndDate(Date rSEndDate) {
        this.rSEndDate = rSEndDate == null ? null : (Date) rSEndDate.clone();
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

    public String getTpstatus() {
        return tpstatus;
    }

    public void setTpstatus(String tpstatus) {
        this.tpstatus = tpstatus;
    }

    public HelperDTO getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(HelperDTO ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public HelperDTO getpSStatus() {
        return pSStatus;
    }

    public void setpSStatus(HelperDTO pSStatus) {
        this.pSStatus = pSStatus;
    }

    public HelperDTO getrSStatus() {
        return rSStatus;
    }

    public void setrSStatus(HelperDTO rSStatus) {
        this.rSStatus = rSStatus;
    }

}
