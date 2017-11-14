package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;

public class GtnFrameworkSelectClauseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkSelectClauseBean() {
		super();
	}

	private GtnFrameworkColumnBean columnBean;

	public GtnFrameworkColumnBean getColumnBean() {
		return columnBean;
	}

	public void setColumnNameWithAlies(String columnNameWithAlies) {
		this.columnBean = new GtnFrameworkColumnBean();
		columnBean.setColumnNameWithAlies(columnNameWithAlies);

	}

}
