package com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean;

import java.io.Serializable;

public class GtnCFamilyPlanCommonUpdateBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnCFamilyPlanCommonUpdateBean() {
		super();
	}

	private String columnName;
	private Object value;
	private String classType;
	private String imtdCfpDetailsSid;

	private int companyMasterSid;
	private String populateType;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getImtdCfpDetailsSid() {
		return imtdCfpDetailsSid;
	}

	public void setImtdCfpDetailsSid(String imtdCfpDetailsSid) {
		this.imtdCfpDetailsSid = imtdCfpDetailsSid;
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public String getPopulateType() {
		return populateType;
	}

	public void setPopulateType(String populateType) {
		this.populateType = populateType;
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
		s.writeObject(value);
	}

	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();
		value = s.readObject();
	}
}
