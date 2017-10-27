/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class ViewLookupDTO implements Serializable {

    private String viewType = StringUtils.EMPTY;
    private String viewName = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private String createdUser = StringUtils.EMPTY;
    private Date createdDate;
    private String viewSid = StringUtils.EMPTY;
    private String fieldName = StringUtils.EMPTY;
    private Boolean checkFlag;
    private boolean viewTypeFlag = false;
    private String detailsValue = StringUtils.EMPTY;
    private String viewCategory = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private Date modifiedDate;

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getViewSid() {
        return viewSid;
    }

    public void setViewSid(String viewSid) {
        this.viewSid = viewSid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public boolean getViewTypeFlag() {
        return viewTypeFlag;
    }

    public void setViewTypeFlag(boolean viewTypeFlag) {
        this.viewTypeFlag = viewTypeFlag;
    }

    public String getDetailsValue() {
        return detailsValue;
    }

    public void setDetailsValue(String detailsValue) {
        this.detailsValue = detailsValue;
    }

    public String getViewCategory() {
        return viewCategory;
    }

    public void setViewCategory(String viewCategory) {
        this.viewCategory = viewCategory;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
