package com.stpl.gtn.gtn2o.ws.companymaster.bean;
// Generated Feb 18, 2017 12:08:27 PM by Hibernate Tools 3.6.0

/**
 * GtnWsCMasterQualifierBean generated by hbm2java
 */
public class GtnWsCMasterQualifierBean implements java.io.Serializable {

	public GtnWsCMasterQualifierBean() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private int companyQualifierSid;
	private int userId;
	private String companyQualifierValue;
	private String companyQualifierName;
	private String effectiveDates;
	private String notes;

	public int getCompanyQualifierSid() {
		return companyQualifierSid;
	}

	public void setCompanyQualifierSid(int companyQualifierSid) {
		this.companyQualifierSid = companyQualifierSid;
	}

	public String getCompanyQualifierValue() {
		return companyQualifierValue;
	}

	public void setCompanyQualifierValue(String companyQualifierValue) {
		this.companyQualifierValue = companyQualifierValue;
	}

	public String getCompanyQualifierName() {
		return companyQualifierName;
	}

	public void setCompanyQualifierName(String companyQualifierName) {
		this.companyQualifierName = companyQualifierName;
	}

	public String getEffectiveDates() {
		return effectiveDates;
	}

	public void setEffectiveDates(String effectiveDates) {
		this.effectiveDates = effectiveDates;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
