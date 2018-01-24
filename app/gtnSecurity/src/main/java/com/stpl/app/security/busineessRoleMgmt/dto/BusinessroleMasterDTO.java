package com.stpl.app.security.busineessRoleMgmt.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

public class BusinessroleMasterDTO implements Serializable {

	private static final long serialVersionUID = -3929126770642500541L;

	private String systemId=StringUtils.EMPTY;
	private int createdBy;
	private String createdDate=StringUtils.EMPTY;
	private String hierarchyLevel=StringUtils.EMPTY;
	private int userId;
	private String processed=StringUtils.EMPTY;
	private String businessroleName=StringUtils.EMPTY;

	public BusinessroleMasterDTO(){
		super();
	}
	
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getBusinessroleName() {
        return businessroleName;
    }

    public void setBusinessroleName(String businessroleName) {
        this.businessroleName = businessroleName;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getBusinessroleDesc() {
        return businessroleDesc;
    }

    public void setBusinessroleDesc(String businessroleDesc) {
        this.businessroleDesc = businessroleDesc;
    }
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
	private int modifiedBy;
	private String modifiedDate=StringUtils.EMPTY;
	private String businessroleDesc=StringUtils.EMPTY;
	private String isActive=StringUtils.EMPTY;

    public int getBusinessroleMasterSid() {
        return businessroleMasterSid;
    }

    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        this.businessroleMasterSid = businessroleMasterSid;
    }
        private int businessroleMasterSid;
}
