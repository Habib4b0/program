/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author karthikeyans
 */
public class ExclusionLookupDTO implements Serializable, Comparable<ExclusionLookupDTO> {

    private String values = StringUtils.EMPTY;
    private String excludedField = StringUtils.EMPTY;
    private String company_Master_Sid = StringUtils.EMPTY;
    private List<ExclusionLookupDTO> fieldList = new ArrayList<>();
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String fieldName = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private Integer userID = 0;
    private String createdUser = StringUtils.EMPTY;
    private boolean viewStatus = false;
    private String viewMasterSid = StringUtils.EMPTY;
    private List<String> companyIDList = new ArrayList();
    private List<CustomerGroupDTO> custGrpList = new ArrayList();
    private boolean screenFlag=false;
    private Integer sessionUserID = 0;

    /**
     * USed to Sort based on the field excludedField
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(ExclusionLookupDTO o) {
        return this.excludedField.compareTo(o.getExcludedField());
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getExcludedField() {
        return excludedField;
    }

    public void setExcludedField(String excludedField) {
        this.excludedField = excludedField;
    }

    public String getCompany_Master_Sid() {
        return company_Master_Sid;
    }

    public void setCompany_Master_Sid(String company_Master_Sid) {
        this.company_Master_Sid = company_Master_Sid;
    }

    public List<ExclusionLookupDTO> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<ExclusionLookupDTO> fieldList) {
        this.fieldList = fieldList;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public boolean isViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(boolean viewStatus) {
        this.viewStatus = viewStatus;
    }

    public String getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(String viewMasterSid) {
        this.viewMasterSid = viewMasterSid;
    }

    public List<String> getCompanyIDList() {
        return companyIDList;
    }

    public void setCompanyIDList(List<String> companyIDList) {
        this.companyIDList = companyIDList;
    }

    public List<CustomerGroupDTO> getCustGrpList() {
        return custGrpList;
    }

    public void setCustGrpList(List<CustomerGroupDTO> custGrpList) {
        this.custGrpList = custGrpList;
    }

    public boolean isScreenFlag() {
        return screenFlag;
    }

    public void setScreenFlag(boolean screenFlag) {
        this.screenFlag = screenFlag;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Integer getSessionUserID() {
        return sessionUserID;
    }

    public void setSessionUserID(Integer sessionUserID) {
        this.sessionUserID = sessionUserID;
    }

    
}
