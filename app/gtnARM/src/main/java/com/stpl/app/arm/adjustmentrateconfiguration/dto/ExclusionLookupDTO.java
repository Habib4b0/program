/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author karthikeyans
 */
public class ExclusionLookupDTO implements Serializable, Comparable<ExclusionLookupDTO> {

    private String values = StringUtils.EMPTY;
    private String excludedField = StringUtils.EMPTY;
    private String companyMasterSid = StringUtils.EMPTY;
    private List<ExclusionLookupDTO> fieldList = new ArrayList<>();
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String fieldName = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private Integer userID = 0;
    private String createdUser = StringUtils.EMPTY;
    private boolean viewStatus = false;
    private String viewMasterSid = StringUtils.EMPTY;
    private transient List<CustomerGroupDTO> custGrpList = new ArrayList();
    private boolean screenFlag = false;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.values);
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.excludedField);
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.companyMasterSid);
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.fieldList);
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.viewName);
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.viewType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExclusionLookupDTO other = (ExclusionLookupDTO) obj;
        if (!Objects.equals(this.values, other.values)) {
            return false;
        }
        if (!Objects.equals(this.excludedField, other.excludedField)) {
            return false;
        }
        if (!Objects.equals(this.companyMasterSid, other.companyMasterSid)) {
            return false;
        }
        if (!Objects.equals(this.viewName, other.viewName)) {
            return false;
        }
        if (!Objects.equals(this.viewType, other.viewType)) {
            return false;
        }
        if (!Objects.equals(this.fieldName, other.fieldName)) {
            return false;
        }
        return Objects.equals(this.fieldList, other.fieldList);
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

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public List<ExclusionLookupDTO> getFieldList() {
        return CommonLogic.getInstance().getArrayListCloned(fieldList);
    }

    public void setFieldList(List<ExclusionLookupDTO> fieldList) {
        this.fieldList = CommonLogic.getInstance().getArrayListCloned(fieldList);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String exclviewName) {
        this.viewName = exclviewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String exclviewType) {
        this.viewType = exclviewType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String exclfieldName) {
        this.fieldName = exclfieldName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String exclcreatedBy) {
        this.createdBy = exclcreatedBy;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer excluserID) {
        this.userID = excluserID;
    }

    public boolean isViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(boolean exclviewStatus) {
        this.viewStatus = exclviewStatus;
    }

    public String getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(String exclviewMasterSid) {
        this.viewMasterSid = exclviewMasterSid;
    }

    public List<CustomerGroupDTO> getCustGrpList() {
        return CommonLogic.getInstance().getArrayListCloned(custGrpList);
    }

    public void setCustGrpList(List<CustomerGroupDTO> custGrpList) {
        this.custGrpList = CommonLogic.getInstance().getArrayListCloned(custGrpList);
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
