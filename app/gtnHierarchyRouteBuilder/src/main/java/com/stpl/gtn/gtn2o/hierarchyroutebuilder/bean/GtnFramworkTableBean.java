package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFramworkTableBean {

	private int tableId;
	private String tablename;

	public GtnFramworkTableBean(int tableId, String tablename) {
		super();
		this.tableId = tableId;
		this.tablename = tablename;
	}

	public GtnFramworkTableBean() {
		
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}


}
