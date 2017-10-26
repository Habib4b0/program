/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;

/**
 *
 * @author santanukumar
 */
public class BalanceReportDTO extends ExtListDTO implements Cloneable{
    String frequency=StringUtils.EMPTY;
    String timePeriodFrom=StringUtils.EMPTY;
    String timePeriodTo=StringUtils.EMPTY;
    String viewType=StringUtils.EMPTY;
    String itemIdentifier=StringUtils.EMPTY;
    List<String> selectedHeaders=new ArrayList<String>();
    String acctCloseMasterId=StringUtils.EMPTY;
    String query=StringUtils.EMPTY;
    private Integer levelNo = new Integer("0");
    private String countFlag=StringUtils.EMPTY;
    private String comapnySid = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String therapeuticSid = StringUtils.EMPTY;
    private String brandSid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private Integer parent = new Integer("0");
    private String hisTimePeriod=StringUtils.EMPTY;
    private String asOfDate=StringUtils.EMPTY;
    private String historyView=StringUtils.EMPTY;
    private Boolean checkRecord;
    private String hypertLink=StringUtils.EMPTY;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimePeriodFrom() {
        return timePeriodFrom;
    }

    public void setTimePeriodFrom(String timePeriodFrom) {
        this.timePeriodFrom = timePeriodFrom;
    }

    public String getTimePeriodTo() {
        return timePeriodTo;
    }

    public void setTimePeriodTo(String timePeriodTo) {
        this.timePeriodTo = timePeriodTo;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public List<String> getSelectedHeaders() {
        return selectedHeaders;
    }

    public void setSelectedHeaders(List<String> selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public String getAcctCloseMasterId() {
        return acctCloseMasterId;
    }

    public void setAcctCloseMasterId(String acctCloseMasterId) {
        this.acctCloseMasterId = acctCloseMasterId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getCountFlag() {
        return countFlag;
    }

    public void setCountFlag(String countFlag) {
        this.countFlag = countFlag;
    }

    public String getComapnySid() {
        return comapnySid;
    }

    public void setComapnySid(String comapnySid) {
        this.comapnySid = comapnySid;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getTherapeuticSid() {
        return therapeuticSid;
    }

    public void setTherapeuticSid(String therapeuticSid) {
        this.therapeuticSid = therapeuticSid;
    }

    public String getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(String brandSid) {
        this.brandSid = brandSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getHisTimePeriod() {
        return hisTimePeriod;
    }

    public void setHisTimePeriod(String hisTimePeriod) {
        this.hisTimePeriod = hisTimePeriod;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getHistoryView() {
        return historyView;
    }

    public void setHistoryView(String historyView) {
        this.historyView = historyView;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getHypertLink() {
        return hypertLink;
    }

    public void setHypertLink(String hypertLink) {
        this.hypertLink = hypertLink;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
