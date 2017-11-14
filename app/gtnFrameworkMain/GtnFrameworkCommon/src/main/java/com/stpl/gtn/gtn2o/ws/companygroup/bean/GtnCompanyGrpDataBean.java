package com.stpl.gtn.gtn2o.ws.companygroup.bean;

import java.io.Serializable;

public class GtnCompanyGrpDataBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnCompanyGrpDataBean() {
		super();
	}

	private int companyGroupSid;
	private int companyMasterSid;
	private String companyTradeClassSid;
	private String companyParentDetailsSid;
	private int versionNo;

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public String getCompanyTradeClassSid() {
		return companyTradeClassSid;
	}

	public void setCompanyTradeClassSid(String companyTradeClassSid) {
		this.companyTradeClassSid = companyTradeClassSid;
	}

	public String getCompanyParentDetailsSid() {
		return companyParentDetailsSid;
	}

	public void setCompanyParentDetailsSid(String companyParentDetailsSid) {
		this.companyParentDetailsSid = companyParentDetailsSid;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getCompanyGroupSid() {
		return companyGroupSid;
	}

	public void setCompanyGroupSid(int companyGroupSid) {
		this.companyGroupSid = companyGroupSid;
	}

	@Override
	public String toString() {
		return "GtnCompanyGrpDataBean{" + "companyGroupSid=" + companyGroupSid + ", companyMasterSid="
				+ companyMasterSid + ", companyTradeClassSid=" + companyTradeClassSid + ", companyParentDetailsSid="
				+ companyParentDetailsSid + ", versionNo=" + versionNo + '}';
	}

}
