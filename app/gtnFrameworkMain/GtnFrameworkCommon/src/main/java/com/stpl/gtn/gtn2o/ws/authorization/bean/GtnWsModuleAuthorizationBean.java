package com.stpl.gtn.gtn2o.ws.authorization.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnWsModuleAuthorizationBean implements Serializable {
	public GtnWsModuleAuthorizationBean() {
		super();
	}

	private int gtnModuleComponentRoleDetailsSid;
	private int gtnRoleMasterSid;
	private int gtnModuleMasterSid;
	private int gtnComponentSid;
	private boolean isEditable;
	private boolean isVisible;
	private boolean isTableProperty;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private String moduleName;
	private String componentId;
	private String componentType;
	private String tablePropertyId;
	private int moduleSubModuleSid;
	private boolean updateModuleFlag;

	public int getGtnModuleComponentRoleDetailsSid() {
		return gtnModuleComponentRoleDetailsSid;
	}

	public void setGtnModuleComponentRoleDetailsSid(int gtnModuleComponentRoleDetailsSid) {
		this.gtnModuleComponentRoleDetailsSid = gtnModuleComponentRoleDetailsSid;
	}

	public int getGtnRoleMasterSid() {
		return gtnRoleMasterSid;
	}

	public void setGtnRoleMasterSid(int gtnRoleMasterSid) {
		this.gtnRoleMasterSid = gtnRoleMasterSid;
	}

	public int getGtnModuleMasterSid() {
		return gtnModuleMasterSid;
	}

	public void setGtnModuleMasterSid(int gtnModuleMasterSid) {
		this.gtnModuleMasterSid = gtnModuleMasterSid;
	}

	public int getGtnComponentSid() {
		return gtnComponentSid;
	}

	public void setGtnComponentSid(int gtnComponentSid) {
		this.gtnComponentSid = gtnComponentSid;
	}

	public boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean getIsTableProperty() {
		return isTableProperty;
	}

	public void setIsTableProperty(boolean isTableProperty) {
		this.isTableProperty = isTableProperty;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getTablePropertyId() {
		return tablePropertyId;
	}

	public void setTablePropertyId(String tablePropertyId) {
		this.tablePropertyId = tablePropertyId;
	}

	public int getModuleSubModuleSid() {
		return moduleSubModuleSid;
	}

	public void setModuleSubModuleSid(int moduleSubModuleSid) {
		this.moduleSubModuleSid = moduleSubModuleSid;
	}

	public boolean isUpdateModuleFlag() {
		return updateModuleFlag;
	}

	public void setUpdateModuleFlag(boolean updateModuleFlag) {
		this.updateModuleFlag = updateModuleFlag;
	}

	@Override
	public String toString() {
		return "GtnWsModuleAuthorizationBean [isEditable=" + isEditable + ", isVisible=" + isVisible
				+ ", isTableProperty=" + isTableProperty + "]";
	}

}
