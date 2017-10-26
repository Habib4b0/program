/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class SaveViewLookUpDTO {
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String fieldName = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private Integer userID = 0;
    private boolean viewStatus = false;
    private String viewMasterSid = StringUtils.EMPTY;
    private List<String> companyIDList = new ArrayList();

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

    public List<String> getCompanyIDList() {
        return companyIDList;
    }

    public void setCompanyIDList(List<String> companyIDList) {
        this.companyIDList = companyIDList;
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


    
}
