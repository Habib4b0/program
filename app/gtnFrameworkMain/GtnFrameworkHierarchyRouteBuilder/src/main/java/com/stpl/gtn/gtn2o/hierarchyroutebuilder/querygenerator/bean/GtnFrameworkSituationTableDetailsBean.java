package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

@Service
@Scope(value = "prototype")
public class GtnFrameworkSituationTableDetailsBean {
	private int situationTableDetailsSid;
	private String leftColumnName;
	private String rightColumnName;
	private String conditionType;
	private String expressionColumnOrder;
	private int joinCoditionOrderSid;
	private int joinCoditionReferenceSid;

	public GtnFrameworkSituationTableDetailsBean() {
		super();
	}

	public int getSituationTableDetailsSid() {
		return situationTableDetailsSid;
	}

	public void setSituationTableDetailsSid(int situationTableDetailsSid) {
		this.situationTableDetailsSid = situationTableDetailsSid;
	}

	public String getLeftColumnName() {
		return leftColumnName;
	}

	public void setLeftColumnName(String leftColumnName) {
		this.leftColumnName = leftColumnName;
	}

	public String getRightColumnName() {
		return rightColumnName;
	}

	public void setRightColumnName(String rightColumnName) {
		this.rightColumnName = rightColumnName;
	}

	public String getConditionType() {
		return conditionType;
	}

	public GtnFrameworkOperatorType getConditionTypeEnum() {
		if (conditionType != null && !conditionType.isEmpty())
			return GtnFrameworkOperatorType.valueOf(conditionType);
		return GtnFrameworkOperatorType.EQUAL_TO;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getExpressionColumnOrder() {
		return expressionColumnOrder;
	}

	public void setExpressionColumnOrder(String expressionColumnOrder) {
		this.expressionColumnOrder = expressionColumnOrder;
	}

	public int getJoinCoditionOrderSid() {
		return joinCoditionOrderSid;
	}

	public void setJoinCoditionOrderSid(int joinCoditionOrderSid) {
		this.joinCoditionOrderSid = joinCoditionOrderSid;
	}

	public int getJoinCoditionReferenceSid() {
		return joinCoditionReferenceSid;
	}

	public void setJoinCoditionReferenceSid(int joinCoditionReferenceSid) {
		this.joinCoditionReferenceSid = joinCoditionReferenceSid;
	}

	public void addOnConditions(GtnFrameworkJoinClauseBean joinConditionBean) {
		joinConditionBean.addConditionBean(leftColumnName, rightColumnName, getConditionTypeEnum());
	}

}
