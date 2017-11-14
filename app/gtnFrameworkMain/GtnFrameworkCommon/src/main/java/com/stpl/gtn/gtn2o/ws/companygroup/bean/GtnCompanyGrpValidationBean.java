package com.stpl.gtn.gtn2o.ws.companygroup.bean;

import java.io.Serializable;

public class GtnCompanyGrpValidationBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnCompanyGrpValidationBean() {
		super();
	}

	private int imtdCount;
	private boolean companyGrpNameExist;
	private boolean companyGrpNoExist;
	private String companyGrpName;
	private String companyGrpNo;
	private int companyGrpMasterSid;

	public int getImtdCount() {
		return imtdCount;
	}

	public void setImtdCount(int imtdCount) {
		this.imtdCount = imtdCount;
	}

	public boolean isCompanyGrpNameExist() {
		return companyGrpNameExist;
	}

	public void setCompanyGrpNameExist(boolean companyGrpNameExist) {
		this.companyGrpNameExist = companyGrpNameExist;
	}

	public boolean isCompanyGrpNoExist() {
		return companyGrpNoExist;
	}

	public void setCompanyGrpNoExist(boolean companyGrpNoExist) {
		this.companyGrpNoExist = companyGrpNoExist;
	}

	public String getCompanyGrpName() {
		return companyGrpName;
	}

	public void setCompanyGrpName(String companyGrpName) {
		this.companyGrpName = companyGrpName;
	}

	public String getCompanyGrpNo() {
		return companyGrpNo;
	}

	public void setCompanyGrpNo(String companyGrpNo) {
		this.companyGrpNo = companyGrpNo;
	}

	public int getCompanyGrpMasterSid() {
		return companyGrpMasterSid;
	}

	public void setCompanyGrpMasterSid(int companyGrpMasterSid) {
		this.companyGrpMasterSid = companyGrpMasterSid;
	}

}
