package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;

import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

public class GtnFrameworkJoinConditionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkJoinConditionBean() {
		super();
	}

	private GtnFrameworkColumnBean joinLeftTableColumnBean;
	private String joinRightTableColumnBean;
	private GtnFrameworkOperatorType joinOperator;

	public GtnFrameworkColumnBean getJoinLeftTableColumnBean() {
		return joinLeftTableColumnBean;
	}

	public String getJoinRightTableColumnBean() {
		return joinRightTableColumnBean;
	}

	public GtnFrameworkOperatorType getJoinOperator() {
		return joinOperator;
	}

	public void setColumnNameWithAlies(String leftPartColumnNameWithAlies, String rightPartColumnNameWithAlies,
			GtnFrameworkOperatorType joinOperator) {
		this.joinLeftTableColumnBean = new GtnFrameworkColumnBean();
		this.joinRightTableColumnBean = null;
		this.joinOperator = joinOperator;
		joinLeftTableColumnBean.setColumnNameWithAlies(leftPartColumnNameWithAlies);
		if (rightPartColumnNameWithAlies != null) {
			this.joinRightTableColumnBean = rightPartColumnNameWithAlies;
		}

	}

	public void replaceColumnValue(String replaceString, String replacedString) {
		if (joinRightTableColumnBean == null)
			return;
		joinRightTableColumnBean = joinRightTableColumnBean.replace(replaceString, replacedString);

	}

}
