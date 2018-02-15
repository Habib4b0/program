package com.stpl.gtn.gtn2o.ws.udc.bean;

import java.io.Serializable;
import java.util.Date;


public class GtnWsUdcInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsUdcInfoBean() {
		super();
	}

	private String udcCategory;
	private String udcValue;
	private String aliasName;
	private Integer helperTableSid;
	private Integer refCount;
	private Integer createdBy;
	private Integer modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private boolean isBrand;
    
	public String getUdcCategory() {
		return this.udcCategory;
	}

	public void setUdcCategory(String udcCategory) {
		this.udcCategory = udcCategory;
	}

	public String getUdcValue() {
		return this.udcValue;
	}

	public void setUdcValue(String udcValue) {
		this.udcValue = udcValue;
	}

	public Integer getHelperTableSid() {
		return this.helperTableSid;
	}

	public void setHelperTableSid(Integer helperTableSid) {
		this.helperTableSid = helperTableSid;
	}

	public Integer getRefCount() {
		return this.refCount;
	}

	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public boolean isBrand() {
		return this.isBrand;
	}

	public void setBrand(boolean isBrand) {
		this.isBrand = isBrand;
	}
	
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
}
