package com.stpl.gtn.gtn2o.ws.itemmaster.bean;

import java.util.Date;

public class GtnWsBrandMasterBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public GtnWsBrandMasterBean() {
		super();
	}

	private int brandMasterSid;
	private String brandId;
	private String brandName;
	private String displayBrand;
	private String brandDesc;
	private Date createdDate;
	private Date modifiedDate;
	private int createdBy;
	private int modifiedBy;
	private char inboundStatus;

	public int getBrandMasterSid() {
		return brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		this.brandMasterSid = brandMasterSid;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDisplayBrand() {
		return displayBrand;
	}

	public void setDisplayBrand(String displayBrand) {
		this.displayBrand = displayBrand;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public char getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}


}
