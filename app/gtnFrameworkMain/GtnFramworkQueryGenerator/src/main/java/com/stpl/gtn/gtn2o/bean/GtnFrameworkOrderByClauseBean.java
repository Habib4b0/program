package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;

public class GtnFrameworkOrderByClauseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkOrderByClauseBean() {
		super();
	}

	private GtnFrameworkColumnBean columnBean;
	private String orderType;

	public GtnFrameworkColumnBean getColumnBean() {
		return columnBean;
	}

	public void setColumnBean(GtnFrameworkColumnBean columnConfig) {
		this.columnBean = columnConfig;
	}

	public void setColumnNameWithAlies(String columnNameWithAlies, String orderType) {
		this.columnBean = new GtnFrameworkColumnBean();
		columnBean.setColumnNameWithAlies(columnNameWithAlies);
		this.orderType = orderType;

	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
