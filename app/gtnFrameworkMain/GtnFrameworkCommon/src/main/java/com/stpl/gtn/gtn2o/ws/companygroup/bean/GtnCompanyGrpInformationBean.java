package com.stpl.gtn.gtn2o.ws.companygroup.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCompanyGrpInformationBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnCompanyGrpInformationBean() {
		super();
	}

	private Integer companyGrpSid;
	private String companyGrpName = "";
	private String companyGrpNo = "";
	private String companyGrpDesc = "";
	private int versionNo;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;

	public String getCompanyGrpName() {
		return companyGrpName;
	}

	public void setCompanyGrpName(String companyGrpName) {
		this.companyGrpName = companyGrpName;
	}

	public String getCompanyGrpDesc() {
		return companyGrpDesc;
	}

	public void setCompanyGrpDesc(String companyGrpDesc) {
		this.companyGrpDesc = companyGrpDesc;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public Integer getCompanyGrpSid() {
		return companyGrpSid;
	}

	public void setCompanyGrpSid(Integer companyGrpSid) {
		this.companyGrpSid = companyGrpSid;
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

	public String getCompanyGrpNo() {
		return companyGrpNo;
	}

	public void setCompanyGrpNo(String companyGrpNo) {
		this.companyGrpNo = companyGrpNo;
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

}
