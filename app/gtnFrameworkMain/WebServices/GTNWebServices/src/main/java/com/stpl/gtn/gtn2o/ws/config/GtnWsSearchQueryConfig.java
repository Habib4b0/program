package com.stpl.gtn.gtn2o.ws.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;

public class GtnWsSearchQueryConfig {

	private String countQuery;
	private String searchQuery;
	private String countAliasAtEnd = "";
	private String countQuerySelectClause = "";
	private Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
	private List<GtnWebServiceOrderByCriteria> orderByClause;
	private List<String> whereClauseList;
	private List<String> whereClauseLeftPartList = new ArrayList<>(0);

	private String parentSearchWhereClause;

	public GtnWsSearchQueryConfig(GtnWsSearchQueryConfig serachQueryConfig) {
		super();
		this.countQuery = serachQueryConfig.countQuery;
		this.searchQuery = serachQueryConfig.searchQuery;
		this.countAliasAtEnd = serachQueryConfig.countAliasAtEnd;
		this.countQuerySelectClause = serachQueryConfig.countQuerySelectClause;
		this.fieldToColumnDetailsMap = serachQueryConfig.fieldToColumnDetailsMap;
		this.orderByClause = serachQueryConfig.orderByClause;
		this.whereClauseList = serachQueryConfig.whereClauseList;
		this.parentSearchWhereClause = serachQueryConfig.parentSearchWhereClause;
		this.whereClauseLeftPartList = serachQueryConfig.whereClauseLeftPartList;
	}

	public String getParentSearchWhereClause() {
		return parentSearchWhereClause;
	}

	public void setParentSearchWhereClause(String parentSearchWhereClause) {
		this.parentSearchWhereClause = parentSearchWhereClause;
	}

	public GtnWsSearchQueryConfig() {
		// constructor
	}

	public String getCountQuery() {
		return countQuery;
	}

	public void setCountQuery(String countQuery) {
		this.countQuery = countQuery;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<GtnWebServiceOrderByCriteria> getOrderByClause() {
		return orderByClause == null ? orderByClause : Collections.unmodifiableList(orderByClause);
	}

	public void setOrderByClause(List<GtnWebServiceOrderByCriteria> orderByClause) {
		this.orderByClause = Collections.unmodifiableList(orderByClause);
	}

	public List<String> getWhereClauseList() {
		return whereClauseList == null ? whereClauseList : Collections.unmodifiableList(whereClauseList);
	}

	public void setWhereClauseList(List<String> whereClauseList) {
		this.whereClauseList = Collections.unmodifiableList(whereClauseList);
	}

	public Map<String, GtnWsColumnDetailsConfig> getFieldToColumnDetailsMap() {
		return fieldToColumnDetailsMap;
	}

	public void setFieldToColumnDetailsMap(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		this.fieldToColumnDetailsMap = fieldToColumnDetailsMap;
	}

	public String getCountQuerySelectClause() {
		return countQuerySelectClause;
	}

	public void setCountQuerySelectClause(String countQuerySelectClause) {
		this.countQuerySelectClause = countQuerySelectClause;
	}

	public String getCountAliasAtEnd() {
		return countAliasAtEnd;
	}

	public void setCountAliasAtEnd(String countAliasAtEnd) {
		this.countAliasAtEnd = countAliasAtEnd;
	}

	public List<String> getWhereClauseLeftPartList() {
		return Collections.unmodifiableList(whereClauseLeftPartList);
	}

	public void addWhereClauseLeftPart(String whereClauseLeftPart) {
		this.whereClauseLeftPartList.add(whereClauseLeftPart);
	}

}
