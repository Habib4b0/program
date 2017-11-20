package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCMasterInformationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnCMasterInformationBean() {
		super();
	}

	private String companyId;

	private String companyNo;

	private String companyName;

	private Integer companyType;

	private Integer companyStatus;

	private Integer companyCategory;

	private Integer companyGroup;

	private Date companyStartDate;

	private Date companyEndDate;

	private Integer organizationKey;

	private Integer lives;

	private String financialSystem;

	private String address1;

	private String address2;

	private String city;

	private Integer state;

	private String zipCode;

	private Integer country;

	private String regionCode;

	private Date lastUpdatedDate;

	private String internalNotes;

	private String inboundStatus;

	private Integer recordLockStatus;

	private String batchId;

	private String source;

	private String createdByName;

	private Integer createdBy;

	private Date createdDate;

	private Integer modifiedBy;

	private String modifiedByName;

	private Date modifiedDate;

	private int companyMasterSystemId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(Integer companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Integer getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(Integer companyCategory) {
		this.companyCategory = companyCategory;
	}

	public Integer getCompanyGroup() {
		return companyGroup;
	}

	public void setCompanyGroup(Integer companyGroup) {
		this.companyGroup = companyGroup;
	}

	public Date getCompanyStartDate() {
		return companyStartDate == null ? null : (Date) companyStartDate.clone();
	}

	public void setCompanyStartDate(Date companyStartDate) {
		this.companyStartDate = companyStartDate == null ? null : (Date) companyStartDate.clone();
	}

	public Date getCompanyEndDate() {
		return companyEndDate == null ? null : (Date) companyEndDate.clone();
	}

	public void setCompanyEndDate(Date companyEndDate) {
		this.companyEndDate = companyEndDate == null ? null : (Date) companyEndDate.clone();
	}

	public Integer getOrganizationKey() {
		return organizationKey;
	}

	public void setOrganizationKey(Integer organizationKey) {
		this.organizationKey = organizationKey;
	}

	public Integer getLives() {
		return lives;
	}

	public void setLives(Integer lives) {
		this.lives = lives;
	}

	public String getFinancialSystem() {
		return financialSystem;
	}

	public void setFinancialSystem(String financialSystem) {
		this.financialSystem = financialSystem;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate == null ? null : (Date) lastUpdatedDate.clone();
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate == null ? null : (Date) lastUpdatedDate.clone();
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public String getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public Integer getRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(Integer recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public int getCompanyMasterSystemId() {
		return companyMasterSystemId;
	}

	public void setCompanyMasterSystemId(int companyMasterSystemId) {
		this.companyMasterSystemId = companyMasterSystemId;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}
}
