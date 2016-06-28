/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;


/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingDTO {

    private HelperDTO businessUnit ;
    private HelperDTO programType;
    private HelperDTO programsubType ;
    private String createdBy = StringUtils.EMPTY;
    private Date createdDate;
    private String modifiedBy = StringUtils.EMPTY;
    private Date modifiedDate;
    private String source = StringUtils.EMPTY;
    private HelperDTO acctType;
    private String accountID = StringUtils.EMPTY;
    private String accountnumber = StringUtils.EMPTY;
    private String accountdesc = StringUtils.EMPTY;
    private String remark = StringUtils.EMPTY;
    private String subledgertype = StringUtils.EMPTY;
    private String indicator = StringUtils.EMPTY;
    private String idvalue = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String version = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String periodIndicator=StringUtils.EMPTY;
    private Integer glResMasterSid;
    private String acctTypeId=StringUtils.EMPTY;
    Boolean check = false;
    private String queryString=StringUtils.EMPTY;
    private String businessProcess=StringUtils.EMPTY;
    private Date activeFrom;
    private Date activeTo;
    private Set<Container.Filter> filters;
    private List<SortByColumn> sortedList = new ArrayList<>();
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    public String getAcctTypeId() {
        return acctTypeId;
    }

    public void setAcctTypeId(String acctTypeId) {
        this.acctTypeId = acctTypeId;
    }
    
    public Integer getGlResMasterSid() {
        return glResMasterSid;
    }

    public void setGlResMasterSid(Integer glResMasterSid) {
        this.glResMasterSid = glResMasterSid;
    }

    public String getPeriodIndicator() {
        return periodIndicator;
    }

    public void setPeriodIndicator(String periodIndicator) {
        this.periodIndicator = periodIndicator;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public HelperDTO getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(HelperDTO businessUnit) {
        this.businessUnit = businessUnit;
    }

    public HelperDTO getProgramType() {
        return programType;
    }

    public void setProgramType(HelperDTO programType) {
        this.programType = programType;
    }

    public HelperDTO getProgramsubType() {
        return programsubType;
    }

    public void setProgramsubType(HelperDTO programsubType) {
        this.programsubType = programsubType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public HelperDTO getAcctType() {
        return acctType;
    }

    public void setAcctType(HelperDTO acctType) {
        this.acctType = acctType;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccountdesc() {
        return accountdesc;
    }

    public void setAccountdesc(String accountdesc) {
        this.accountdesc = accountdesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubledgertype() {
        return subledgertype;
    }

    public void setSubledgertype(String subledgertype) {
        this.subledgertype = subledgertype;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdvalue() {
        return idvalue;
    }

    public void setIdvalue(String idvalue) {
        this.idvalue = idvalue;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public Date getActiveFrom() {
        return activeFrom;
    }
    
    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
}

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public String getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }

    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    public List<SortByColumn> getSortedList() {
        return sortedList;
    }

    public void setSortedList(List<SortByColumn> sortedList) {
        this.sortedList = sortedList;
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
    
}
