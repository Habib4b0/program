package com.stpl.gtn.gtn2o.ws.itemgroup.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnItemGrpInformationBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnItemGrpInformationBean() {
		super();
	}

	private Integer itemGrpSid;
	private String itemGrpName = "";
	private String itemGrpNo = "";
	private String itemGrpDesc = "";
	private int versionNo;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private int companyMasterSid;

	public String getItemGrpNo() {
		return itemGrpNo;
	}

	public void setItemGrpNo(String itemGrpNo) {
		this.itemGrpNo = itemGrpNo;
	}

	public String getItemGrpDesc() {
		return itemGrpDesc;
	}

	public void setItemGrpDesc(String itemGrpDesc) {
		this.itemGrpDesc = itemGrpDesc;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getItemGrpName() {
		return itemGrpName;
	}

	public void setItemGrpName(String itemGrpName) {
		this.itemGrpName = itemGrpName;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getItemGrpSid() {
		return itemGrpSid;
	}

	public void setItemGrpSid(Integer itemGrpSid) {
		this.itemGrpSid = itemGrpSid;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

}
