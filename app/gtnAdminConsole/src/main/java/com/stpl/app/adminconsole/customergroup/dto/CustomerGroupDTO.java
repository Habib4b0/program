/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * The Class CustomerGroupDTO.
 *
 * @author vishalakshi
 */
public class CustomerGroupDTO implements Serializable {

    private static final long serialVersionUID = -6914336005062720300L;

    private String customerGroupNo = StringUtils.EMPTY;

    private String customerGroupName = StringUtils.EMPTY;

    private String customerGroupDesc = StringUtils.EMPTY;

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private int customerGroupSystemId;

    private int versionNo;

    private String status;

    private String searchCriteria = StringUtils.EMPTY;

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(final String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(final int versionNo) {
        this.versionNo = versionNo;
    }

    public String getCustomerGroupNo() {
        return customerGroupNo;
    }

    public void setCustomerGroupNo(final String customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(final String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupDesc() {
        return customerGroupDesc;
    }

    public void setCustomerGroupDesc(final String customerGroupDesc) {
        this.customerGroupDesc = customerGroupDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getCustomerGroupSystemId() {
        return customerGroupSystemId;
    }

    public void setCustomerGroupSystemId(final int customerGroupSystemId) {
        this.customerGroupSystemId = customerGroupSystemId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
