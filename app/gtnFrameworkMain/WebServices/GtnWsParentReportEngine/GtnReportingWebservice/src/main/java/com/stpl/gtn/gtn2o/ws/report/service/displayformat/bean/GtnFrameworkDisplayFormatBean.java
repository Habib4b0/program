package com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean;

public class GtnFrameworkDisplayFormatBean {

	private String tableName;
	private String columnName;
	private String selectedColumnName;

	public GtnFrameworkDisplayFormatBean() {
		super();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSelectedColumnName() {
		return selectedColumnName;
	}

	public void setSelectedColumnName(String selectedColumnName) {
		this.selectedColumnName = selectedColumnName;
	}
}
