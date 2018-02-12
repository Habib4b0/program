package com.stpl.app.bpm.dto;

import java.io.Serializable;

/**
 * @author KarthikeyanS
 *
 */
public class HeirarchyDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970220600933977495L;

	private String hierarchyName;
	private String hierarchyType;
	private String hierarchyCategory;
	private String levelName;
	private int levelNo;
	private String userDefinedOrLinked;
	private String tableName;
	private String fieldName;
	private int noOfLevel;
	private int versionNo;
	private Boolean hierarchyNameFlag;
	private int hierarchyDefinitionSid;
	private int hierarchylevelDefinitionSid;
	private String ruleName;
	private String udc1;
	private String udc2;
	private String udc3;
	private String udc4;
	private String udc5;
	
	private String inclusionRuleType;
	private String inclusionRule;
	private String exclusionRuleType;
	private String exclusionRule;
	private String inclusionCondition = "";
	private String exclusionCondition = "";
	
	private String displayColumn = "";
	private String selectedColumn = "";
	private String dummyColumn = "";

	public String getInclusionCondition() {
		return inclusionCondition;
	}

	public void setInclusionCondition(String inclusionCondition) {
		this.inclusionCondition = inclusionCondition;
	}

	public String getExclusionCondition() {
		return exclusionCondition;
	}

	public void setExclusionCondition(String exclusionCondition) {
		this.exclusionCondition = exclusionCondition;
	}

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public String getHierarchyCategory() {
		return hierarchyCategory;
	}

	public void setHierarchyCategory(String hierarchyCategory) {
		this.hierarchyCategory = hierarchyCategory;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public String getUserDefinedOrLinked() {
		return userDefinedOrLinked;
	}

	public void setUserDefinedOrLinked(String userDefinedOrLinked) {
		this.userDefinedOrLinked = userDefinedOrLinked;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}





	public Boolean getHierarchyNameFlag() {
		return hierarchyNameFlag;
	}

	public void setHierarchyNameFlag(Boolean hierarchyNameFlag) {
		this.hierarchyNameFlag = hierarchyNameFlag;
	}

	public int getNoOfLevel() {
		return noOfLevel;
	}

	public void setNoOfLevel(int noOfLevel) {
		this.noOfLevel = noOfLevel;
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		this.hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public int getHierarchylevelDefinitionSid() {
		return hierarchylevelDefinitionSid;
	}

	public void setHierarchylevelDefinitionSid(int hierarchylevelDefinitionSid) {
		this.hierarchylevelDefinitionSid = hierarchylevelDefinitionSid;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	public String getUdc1() {
		return udc1;
	}

	public void setUdc1(String udc1) {
		this.udc1 = udc1;
	}

	public String getUdc2() {
		return udc2;
	}

	public void setUdc2(String udc2) {
		this.udc2 = udc2;
	}

	public String getUdc3() {
		return udc3;
	}

	public void setUdc3(String udc3) {
		this.udc3 = udc3;
	}

	public String getUdc4() {
		return udc4;
	}

	public void setUdc4(String udc4) {
		this.udc4 = udc4;
	}

	public String getUdc5() {
		return udc5;
	}

	public void setUdc5(String udc5) {
		this.udc5 = udc5;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	

	public String getInclusionRuleType() {
		return inclusionRuleType;
	}

	public void setInclusionRuleType(String inclusionRuleType) {
		this.inclusionRuleType = inclusionRuleType;
	}

	public String getInclusionRule() {
		return inclusionRule;
	}

	public void setInclusionRule(String inclusionRule) {
		this.inclusionRule = inclusionRule;
	}


	public String getExclusionRuleType() {
		return exclusionRuleType;
	}

	public void setExclusionRuleType(String exclusionRuleType) {
		this.exclusionRuleType = exclusionRuleType;
	}

	public String getExclusionRule() {
		return exclusionRule;
	}

	public void setExclusionRule(String exclusionRule) {
		this.exclusionRule = exclusionRule;
	}

	public String getDisplayColumn() {
		return displayColumn;
	}

	public void setDisplayColumn(String displayColumn) {
		this.displayColumn = displayColumn;
	}

	public String getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(String selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public String getDummyColumn() {
		return dummyColumn;
	}

	public void setDummyColumn(String dummyColumn) {
		this.dummyColumn = dummyColumn;
	}

}
