package com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GtnIFamilyPlanCommonUpdateBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public GtnIFamilyPlanCommonUpdateBean() {
		super();
	}

	private String columnName;
	
	@JsonDeserialize(as = Object.class)
	private Serializable value;
	
	private String classType;
	private String imtdIfpDetailsSid;
	private int itemMasterSid;
	private String populateType;
	private boolean checkAll;

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
		this.value = (Serializable) value;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getImtdIfpDetailsSid() {
		return imtdIfpDetailsSid;
	}

	public void setImtdIfpDetailsSid(String imtdIfpDetailsSid) {
		this.imtdIfpDetailsSid = imtdIfpDetailsSid;
	}

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public String getPopulateType() {
		return populateType;
	}

	public void setPopulateType(String populateType) {
		this.populateType = populateType;
	}

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

}
