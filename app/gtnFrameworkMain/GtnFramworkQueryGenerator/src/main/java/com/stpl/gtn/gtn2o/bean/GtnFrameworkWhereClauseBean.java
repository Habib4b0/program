package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;
import java.util.Date;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

public class GtnFrameworkWhereClauseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkWhereClauseBean() {
		super();
	}

	private GtnFrameworkColumnBean whereClauseLeftbean;
	private GtnFrameworkOperatorType operatorType;
	private GtnFrameworkColumnBean whereClauseRightbean;
	private String stringConditionValue;
	private int intConditionValue;
	private Date dateConditionValue;
	private GtnFrameworkDataType conditionValueType;
	private String finalValue;

	public String getStringConditionValue() {
		return stringConditionValue;
	}

	public int getIntConditionValue() {
		return intConditionValue;
	}

	public Date getDateConditionValue() {
		return dateConditionValue;
	}

	public GtnFrameworkOperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(GtnFrameworkOperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public GtnFrameworkColumnBean getWhereClauseLeftbean() {
		return whereClauseLeftbean;
	}

	public void setWhereClauseLeftbean(GtnFrameworkColumnBean whereClauseLeftbean) {
		this.whereClauseLeftbean = whereClauseLeftbean;
	}

	public GtnFrameworkColumnBean getWhereClauseRightbean() {
		return whereClauseRightbean;
	}

	public void setWhereClauseRightbean(GtnFrameworkColumnBean whereClauseRightbean) {
		this.whereClauseRightbean = whereClauseRightbean;
	}

	public String getConditionalValue() {
		String finalReturnValue;
		switch (conditionValueType) {
		case INTEGER:
			finalReturnValue = String.valueOf(intConditionValue);
			break;
		case STRING:
			finalReturnValue = "'" + stringConditionValue + "'";
			break;
		case DATE:
			finalReturnValue = "CAST( " + dateConditionValue + "as DATETIME)";
			break;
		default:
			finalReturnValue = finalValue;
		}
		return finalReturnValue;
	}

	public void initializeVariables(String leftPartColumnNameWithAlies, String rightPartColumnNameWithAlies,
			GtnFrameworkOperatorType operatorType, GtnFrameworkDataType conditionValueType, Object value) {
		this.whereClauseLeftbean = new GtnFrameworkColumnBean();
		this.operatorType = operatorType;
		this.conditionValueType = conditionValueType;
		setConditionalValue(conditionValueType, value);
		whereClauseLeftbean.setColumnNameWithAlies(leftPartColumnNameWithAlies);
		if (rightPartColumnNameWithAlies != null) {
			this.whereClauseRightbean = new GtnFrameworkColumnBean();
			this.whereClauseRightbean.setColumnNameWithAlies(rightPartColumnNameWithAlies);
		}
	}

	public void setConditionalValue(GtnFrameworkDataType conditionValueType, Object value) {
		switch (conditionValueType) {
		case INTEGER:
			intConditionValue = (int) value;
			break;
		case STRING:
			stringConditionValue = (String) value;
			break;
		case DATE:
			dateConditionValue = (Date) value;
			break;
		default:
			finalValue = null;
		}

	}

	public GtnFrameworkDataType getConditionValueType() {
		return conditionValueType;
	}

	public void setConditionValueType(GtnFrameworkDataType conditionValueType) {
		this.conditionValueType = conditionValueType;
	}
}
