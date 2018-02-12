package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

public class GtnFrameworkHierarchyRestrictionBean implements Comparable<GtnFrameworkHierarchyRestrictionBean> {
	private String actualTableName;
	private String actualColumnName;
	private String referencTableName;
	private String referenceColumnName;
	private String restrictionColumnName;
	private String restrictionValue;
	private int hierarchyTableMasterSid;
	private Integer joinSequence;
	private String operatorType;

	private static final String restrictionJoin = "RESTRICTION_JOIN";

	public GtnFrameworkHierarchyRestrictionBean() {
		super();

	}

	public String getActualTableName() {
		return actualTableName;
	}

	public void setActualTableName(String actualTableName) {
		this.actualTableName = actualTableName;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

	public String getReferencTableName() {
		return referencTableName;
	}

	public void setReferencTableName(String referencTableName) {
		this.referencTableName = referencTableName;
	}

	public String getReferenceColumnName() {
		return referenceColumnName;
	}

	public void setReferenceColumnName(String referenceColumnName) {
		this.referenceColumnName = referenceColumnName;
	}

	public String getRestrictionColumnName() {
		return restrictionColumnName;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public void setJoinSequence(Integer joinSequence) {
		this.joinSequence = joinSequence;
	}

	public void setRestrictionColumnName(String restrictionColumnName) {
		this.restrictionColumnName = restrictionColumnName;
	}

	public String getRestrictionValue() {
		return restrictionValue;
	}

	public void setRestrictionValue(String restrictionValue) {
		this.restrictionValue = restrictionValue;
	}

	public int getHierarchyTableMasterSid() {
		return hierarchyTableMasterSid;
	}

	public void setHierarchyTableMasterSid(int hierarchyTableMasterSid) {
		this.hierarchyTableMasterSid = hierarchyTableMasterSid;
	}


	public void addrestrictionForTable(GtnFrameworkQueryGeneratorBean queryBean) {
		if (referencTableName != null && !referencTableName.isEmpty()) {
			addJoinAndRestriction(queryBean);
			return;
		}
		queryBean.addWhereClauseBean(actualTableName + "." + restrictionColumnName, null,
				GtnFrameworkOperatorType.getOperaterType(operatorType), GtnFrameworkDataType.STRING, restrictionValue);

	}

	private void addJoinAndRestriction(GtnFrameworkQueryGeneratorBean queryBean) {

		GtnFrameworkJoinClauseBean joinCondition = queryBean.addJoinClauseBean(referencTableName,
				restrictionJoin + referencTableName, GtnFrameworkJoinType.INNER_JOIN);
		joinCondition.addConditionBean(getRestrictionJoinAliasName() + "." + actualColumnName,
				restrictionJoin + referencTableName + "." + referenceColumnName,
				GtnFrameworkOperatorType.getOperaterType(operatorType));
		if (restrictionColumnName != null && !restrictionColumnName.isEmpty()) {
			queryBean.addWhereClauseBean(restrictionJoin + referencTableName + "." + restrictionColumnName, null,
					GtnFrameworkOperatorType.getOperaterType(operatorType), GtnFrameworkDataType.STRING,
					restrictionValue);
		}

	}


	private String getRestrictionJoinAliasName() {
		if (joinSequence == 1)
			return actualTableName;
		return restrictionJoin + actualTableName;
	}

	public static void buildRestrictionQuery(List<GtnFrameworkHierarchyRestrictionBean> restrictionBeanList,
			GtnFrameworkQueryGeneratorBean queryBean) {
		Collections.sort(restrictionBeanList);
		for (GtnFrameworkHierarchyRestrictionBean gtnFrameworkHierarchyRestrictionBean : restrictionBeanList) {
			gtnFrameworkHierarchyRestrictionBean.addrestrictionForTable(queryBean);
		}
	}

	@Override
	public int compareTo(GtnFrameworkHierarchyRestrictionBean object) {
		return this.joinSequence - object.joinSequence;
	}

}
