package com.stpl.gtn.gtn2o.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkQueryGenerator;

public class GtnFrameworkQueryGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnFrameworkQueryGeneratorBean() {
		super();
	}

	public static final String SELECT_KEYWORD = "SELECT ";
	public static final String WHERE_KEYWORD = " WHERE ";
	public static final String ORDER_BY = " ORDER BY ";
	public static final String FROM = " FROM ";
	public static final String AND = " AND ";
	public static final String DISTINCT = " DISTINCT ";

	private String fromTableNameWithAlies;
	private List<GtnFrameworkSelectClauseBean> selectClauseConfigList = new ArrayList<>(0);
	private List<GtnFrameworkJoinClauseBean> joinClauseConfigList = new ArrayList<>(0);
	private List<GtnFrameworkWhereClauseBean> whereClauseConfigList = new ArrayList<>(0);
	private List<GtnFrameworkOrderByClauseBean> orderByClauseConfigList = new ArrayList<>(0);

	public String getFromTableNameWithAlies() {
		return fromTableNameWithAlies;
	}

	public void setFromTableNameWithAlies(String fromTableNameWithAlies) {
		this.fromTableNameWithAlies = fromTableNameWithAlies;
	}

	public List<GtnFrameworkSelectClauseBean> getSelectClauseConfigList() {
		return Collections.unmodifiableList(selectClauseConfigList);
	}

	public List<GtnFrameworkJoinClauseBean> getJoinClauseConfigList() {
		return Collections.unmodifiableList(joinClauseConfigList);
	}

	public List<GtnFrameworkWhereClauseBean> getWhereClauseConfigList() {
		return Collections.unmodifiableList(whereClauseConfigList);
	}

	public List<GtnFrameworkOrderByClauseBean> getOrderByClauseConfigList() {
		return Collections.unmodifiableList(orderByClauseConfigList);
	}

	public void addSelectClauseBean(String columnNameWithAlies) {
		GtnFrameworkSelectClauseBean selectClauseBean = new GtnFrameworkSelectClauseBean();
		selectClauseBean.setColumnNameWithAlies(columnNameWithAlies);
		this.selectClauseConfigList.add(selectClauseBean);
	}

	public GtnFrameworkJoinClauseBean addJoinClauseBean(String joinTableName, String tableAliesName,
			GtnFrameworkJoinType joinType) {
		GtnFrameworkJoinClauseBean joinClauseBean = new GtnFrameworkJoinClauseBean();
		joinClauseBean.initializeVariables(joinTableName, tableAliesName, joinType);
		this.joinClauseConfigList.add(joinClauseBean);
		return joinClauseBean;
	}

	public void addWhereClauseBean(String leftPartColumnNameWithAlies, String rightPartColumnNameWithAlies,
			GtnFrameworkOperatorType operatorType, GtnFrameworkDataType conditionValueType, Object value) {
		GtnFrameworkWhereClauseBean whereClauseBean = new GtnFrameworkWhereClauseBean();
		whereClauseBean.initializeVariables(leftPartColumnNameWithAlies, rightPartColumnNameWithAlies, operatorType,
				conditionValueType, value);
		this.whereClauseConfigList.add(whereClauseBean);
	}

	public void addOrderByClauseBean(String columnNameWithAlies, String orderType) {
		GtnFrameworkOrderByClauseBean orderByClauseBean = new GtnFrameworkOrderByClauseBean();
		orderByClauseBean.setColumnNameWithAlies(columnNameWithAlies, orderType);
		this.orderByClauseConfigList.add(orderByClauseBean);
	}

	public String generateQuery() {
		GtnFrameworkQueryGenerator frameworkQueryGenerator = GtnFrameworkQueryGenerator.getInstance();
		StringBuilder finalGeneratedQuery = new StringBuilder(
				GtnFrameworkQueryGeneratorBean.SELECT_KEYWORD + GtnFrameworkQueryGeneratorBean.DISTINCT);
		StringBuilder generatedSelectClause = frameworkQueryGenerator.generateSelectClauseQuery(this);
		StringBuilder generatedJoinClause = frameworkQueryGenerator.generateJoinClauseQuery(this);
		StringBuilder generatedWhereClause = frameworkQueryGenerator.generateWhereClauseQuery(this);
		StringBuilder generatedOrderByClause = frameworkQueryGenerator.generateOrderByClauseQuery(this);
		generatedSelectClause.append(GtnFrameworkQueryGeneratorBean.FROM).append(getFromTableNameWithAlies());
		finalGeneratedQuery.append(generatedSelectClause).append(generatedJoinClause).append(generatedWhereClause)
				.append(generatedOrderByClause);
		return finalGeneratedQuery.toString();
	}

}