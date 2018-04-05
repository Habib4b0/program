package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkHelperSelectBean {

	private int masterTableSid;
	private String actualColumnName;

	public GtnFrameworkHelperSelectBean() {
		super();
	}

	public int getMasterTableSid() {
		return masterTableSid;
	}

	public void setMasterTableSid(int masterTableSid) {
		this.masterTableSid = masterTableSid;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

}
