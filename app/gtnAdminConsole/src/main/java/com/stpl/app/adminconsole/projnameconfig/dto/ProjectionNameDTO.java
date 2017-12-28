/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.dto;

import java.io.Serializable;

/**
 * The Class ProjectionNameDTO.
 *
 * @author santanukumar
 */
public class ProjectionNameDTO implements Serializable {

    private String businessProcessType;

    private String selectedAttributes;

    private String versionNo;

    private String createdBy;

    private String modifiedBy;

    private String availableAttributes;

    private int projectionNameCongigSid;

    private String createdDate;

    private String modifiedDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getProjectionNameCongigSid() {
        return projectionNameCongigSid;
    }

    public void setProjectionNameCongigSid(final int projectionNameCongigSid) {
        this.projectionNameCongigSid = projectionNameCongigSid;
    }

    public String getAvailableAttributes() {
        return availableAttributes;
    }

    public void setAvailableAttributes(final String availableAttributes) {
        this.availableAttributes = availableAttributes;
    }

    public String getBusinessProcessType() {
        return businessProcessType;
    }

    public void setBusinessProcessType(final String businessProcessType) {
        this.businessProcessType = businessProcessType;
    }

    public String getSelectedAttributes() {
        return selectedAttributes;
    }

    public void setSelectedAttributes(final String selectedAttributes) {
        this.selectedAttributes = selectedAttributes;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(final String versionNo) {
        this.versionNo = versionNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
