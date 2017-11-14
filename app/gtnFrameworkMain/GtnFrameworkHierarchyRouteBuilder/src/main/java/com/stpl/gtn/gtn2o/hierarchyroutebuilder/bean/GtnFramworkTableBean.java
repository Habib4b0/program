package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFramworkTableBean {

	private int tableId;
	private String tablename;
	private String inboundStatusColumn;
	private String inboundStatusValue;

	public GtnFramworkTableBean(int tableId, String tablename, String inboundStatusColumn, String inboundStatusValue) {
		super();
		this.tableId = tableId;
		this.tablename = tablename;
		this.inboundStatusColumn = inboundStatusColumn;
		this.inboundStatusValue = inboundStatusValue;
	}

	public GtnFramworkTableBean() {

	}

	public String getInboundStatusColumn() {
		return inboundStatusColumn;
	}

	public void setInboundStatusColumn(String inboundStatusColumn) {
		this.inboundStatusColumn = inboundStatusColumn;
	}

	public String getInboundStatusValue() {
		return inboundStatusValue;
	}

	public void setInboundStatusValue(String inboundStatusValue) {
		this.inboundStatusValue = inboundStatusValue;
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

	public Object getTableNameAndInboudColumn() {
		return tablename + "." + inboundStatusColumn;
	}

}
