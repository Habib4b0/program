package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;

public class GtnFrameworkHierarchyRestrictionBean {
	private String actualTableName;
	private String actualColumnName;
	private String referencTableName;
	private String referenceColumnName;
	private String restrictionColumnName;
	private String restrictionValue;
	private int hierarchyTableMasterSid;

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
			String restrictionJoin = "RESTRICTION_JOIN";
			GtnFrameworkJoinClauseBean joinCondition = queryBean.addJoinClauseBean(referencTableName,
					restrictionJoin + referencTableName, GtnFrameworkJoinType.INNER_JOIN);
			joinCondition.addConditionBean(actualTableName + "." + actualColumnName,
					restrictionJoin + referencTableName + "." + referenceColumnName,
					GtnFrameworkOperatorType.EQUAL_TO);
			queryBean.addWhereClauseBean(restrictionJoin + referencTableName + "." + restrictionColumnName, null,
					GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.STRING, restrictionValue);
			return;
		}
		queryBean.addWhereClauseBean(actualTableName + "." + restrictionColumnName, null,
				GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.STRING, restrictionValue);

	}

}
