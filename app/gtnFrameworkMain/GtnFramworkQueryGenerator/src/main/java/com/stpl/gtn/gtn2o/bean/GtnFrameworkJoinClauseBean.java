package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

public class GtnFrameworkJoinClauseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkJoinClauseBean() {
		super();
	}

	private List<GtnFrameworkJoinConditionBean> conditionList = new ArrayList<>(0);
	private List<GtnFrameworkJoinConditionBean> joinOrConditionList = new ArrayList<>(0);
	private String joinTableName;
	private String joinTableAliesName;
	private GtnFrameworkJoinType joinType;

	public GtnFrameworkJoinType getJoinType() {
		return joinType;
	}

	public List<GtnFrameworkJoinConditionBean> getConditionList() {
		return Collections.unmodifiableList(conditionList);
	}

	public void addConditionList(GtnFrameworkJoinConditionBean conditionBean) {

		conditionList.add(conditionBean);
	}

	public void addJoinOrConditionList(GtnFrameworkJoinConditionBean conditionBean) {

		joinOrConditionList.add(conditionBean);
	}

	public List<GtnFrameworkJoinConditionBean> getJoinOrConditionList() {
		return Collections.unmodifiableList(joinOrConditionList);
	}

	public String getJoinTableName() {
		return joinTableName;
	}

	public String getJoinTableAliesName() {
		return joinTableAliesName;
	}

	public void addConditionBean(String leftPartColumnNameWithAlies, String rightPartColumnNameWithAlies,
			GtnFrameworkOperatorType joinOperator) {
		GtnFrameworkJoinConditionBean joinConditionBean = new GtnFrameworkJoinConditionBean();
		joinConditionBean.setColumnNameWithAlies(leftPartColumnNameWithAlies, rightPartColumnNameWithAlies,
				joinOperator);
		conditionList.add(joinConditionBean);
	}

	public void addOrConditionBean(String leftPartColumnNameWithAlies, String rightPartColumnNameWithAlies,
			GtnFrameworkOperatorType joinOperator) {
		GtnFrameworkJoinConditionBean joinConditionBean = new GtnFrameworkJoinConditionBean();
		joinConditionBean.setColumnNameWithAlies(leftPartColumnNameWithAlies, rightPartColumnNameWithAlies,
				joinOperator);
		joinOrConditionList.add(joinConditionBean);
	}

	public void initializeVariables(String joinTableName, String tableAliesName, GtnFrameworkJoinType joinType) {
		this.joinTableName = joinTableName;
		this.joinTableAliesName = tableAliesName;
		this.joinType = joinType;
	}

	public void replaceConditionBeanColumn(String replaceString, String replacedString) {
		for (GtnFrameworkJoinConditionBean gtnFrameworkJoinConditionBean : conditionList) {
			gtnFrameworkJoinConditionBean.replaceColumnValue(replaceString, replacedString);
		}
	}

}
