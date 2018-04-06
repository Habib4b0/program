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
	private String selectClauseAliesName;
	private Boolean isValueFromColumnBean;
	private String selectClauseValue;

	public GtnFrameworkColumnBean getColumnBean() {
		return columnBean;
	}

	public String getSelectClauseAliesName() {
		return selectClauseAliesName;
	}

	public Boolean getIsValueFromColumnBean() {
		return isValueFromColumnBean;
	}

	public String getSelectClauseValue() {
		return selectClauseValue;
	}

	public void setColumnNameWithAlies(String columnNameWithAlies, String selectClauseAliesName,
			Boolean isValueFromColumnBean, String selectClauseValue) {
		this.selectClauseAliesName=selectClauseAliesName;
		this.isValueFromColumnBean = isValueFromColumnBean;
		this.selectClauseValue = selectClauseValue;
		if (isValueFromColumnBean) {
			this.columnBean = new GtnFrameworkColumnBean();
			columnBean.setColumnNameWithAlies(columnNameWithAlies);
		}
	}

	public void replaceSelectClause(String replaceString, String replacedString) {
		if (selectClauseValue == null)
			return;
		selectClauseValue = selectClauseValue.replace(replaceString, replacedString);
	}

}
