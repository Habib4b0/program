package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;

public class GtnFrameworkColumnBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkColumnBean() {
		super();
	}

	private String columnNameWithAlies;
	private String tableAliesName;
	private String columnName;

	public String getColumnNameWithAlies() {
		return columnNameWithAlies;
	}

	public void setColumnNameWithAlies(String columnNameWithAlies) {
		this.columnNameWithAlies = columnNameWithAlies;
		initialiseTableAliesName();
	}

	public String getAliesName() {
		return tableAliesName;
	}

	public String getColumnName() {
		return columnName;
	}

	private void initialiseTableAliesName() {
		String[] arr = columnNameWithAlies.split("\\.");
		if (arr != null && arr.length > 1) {
			tableAliesName = arr[0];
			columnName = arr[1];
		}
	}
}
