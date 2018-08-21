package com.stpl.gtn.gtn2o.ws.processscheduler.bean;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GtnCffOutBoundBean {

	public GtnCffOutBoundBean() {
		super();
	}

	private String columnName;

	@JsonDeserialize(as = Object.class)
	private Serializable value;
	private int rsModelSid;
	private int periodSid;
	private int cffDetailsSid;
	private boolean checkedRecord;
	private boolean checkAll;

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public int getCffDetailsSid() {
		return cffDetailsSid;
	}

	public void setCffDetailsSid(int cffDetailsSid) {
		this.cffDetailsSid = cffDetailsSid;
	}

	public boolean isCheckedRecord() {
		return checkedRecord;
	}

	public void setCheckedRecord(boolean checkRecord) {
		this.checkedRecord = checkRecord;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Serializable getValue() {
		return value;
	}

	public void setValue(Serializable value) {
		this.value = value;
	}

}
