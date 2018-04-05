/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author porchelvi.g
 */
public class SummaryDTO {

    private boolean checkRecord = false;
    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String cfp = StringUtils.EMPTY;
    private String ifp = StringUtils.EMPTY;
    private String ps = StringUtils.EMPTY;
    private String rs = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String cfpSid = StringUtils.EMPTY;
    private String ifpSid = StringUtils.EMPTY;
    private String psSid = StringUtils.EMPTY;
    private String rsSid = StringUtils.EMPTY;
    private String masterSid = StringUtils.EMPTY;
    private boolean isCount = false;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private String rarCategory = StringUtils.EMPTY;
    private String itemstartdate = StringUtils.EMPTY;

    public SummaryDTO() {
        super();
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getItemstartdate() {
        return itemstartdate;
    }

    public void setItemstartdate(String itemstartdate) {
        this.itemstartdate = itemstartdate;
    }

    public String getItemenddate() {
        return itemenddate;
    }

    public void setItemenddate(String itemenddate) {
        this.itemenddate = itemenddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String itemenddate = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

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

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
    }

    public String getCfp() {
        return cfp;
    }

    public void setCfp(String cfp) {
        this.cfp = cfp;
    }

    public String getIfp() {
        return ifp;
    }

    public void setIfp(String ifp) {
        this.ifp = ifp;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getCfpSid() {
        return cfpSid;
    }

    public void setCfpSid(String cfpSid) {
        this.cfpSid = cfpSid;
    }

    public String getIfpSid() {
        return ifpSid;
    }

    public void setIfpSid(String ifpSid) {
        this.ifpSid = ifpSid;
    }

    public String getPsSid() {
        return psSid;
    }

    public void setPsSid(String psSid) {
        this.psSid = psSid;
    }

    public String getRsSid() {
        return rsSid;
    }

    public void setRsSid(String rsSid) {
        this.rsSid = rsSid;
    }

    public String getMasterSid() {
        return masterSid;
    }

    public void setMasterSid(String masterSid) {
        this.masterSid = masterSid;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }
}
