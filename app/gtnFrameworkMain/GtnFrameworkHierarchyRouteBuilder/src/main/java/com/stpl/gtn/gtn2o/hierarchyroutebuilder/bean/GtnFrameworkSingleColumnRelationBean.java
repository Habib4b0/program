package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkSingleColumnRelationBean {
	private int mastersid;
	private String actualTtableName;
	private String actualColumnName;
	private String referenceTableName;
	private String mappingColumnName;
	private String descColumnName;
	private String primaryKeyColumnName;

	public GtnFrameworkSingleColumnRelationBean(int mastersid, String actualTtableName, String actualColumnName,
			String referenceTableName, String mappingColumnName, String descColumnName, String primaryKeyColumnName) {
		super();
		this.mastersid = mastersid;
		this.actualTtableName = actualTtableName;
		this.actualColumnName = actualColumnName;
		this.referenceTableName = referenceTableName;
		this.mappingColumnName = mappingColumnName;
		this.descColumnName = descColumnName;
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	public GtnFrameworkSingleColumnRelationBean() {

	}

	public int getMastersid() {
		return mastersid;
	}

	public void setMastersid(int mastersid) {
		this.mastersid = mastersid;
	}

	public String getActualTtableName() {
		return actualTtableName;
	}

	public void setActualTtableName(String actualTtableName) {
		this.actualTtableName = actualTtableName;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

	public String getReferenceTableName() {
		return referenceTableName;
	}

	public void setReferenceTableName(String referenceTableName) {
		this.referenceTableName = referenceTableName;
	}

	public String getMappingColumnName() {
		return mappingColumnName;
	}

	public void setMappingColumnName(String mappingColumnName) {
		this.mappingColumnName = mappingColumnName;
	}

	public String getDescColumnName() {
		return descColumnName;
	}

	public void setDescColumnName(String descColumnName) {
		this.descColumnName = descColumnName;
	}

	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	public String getJoinColumnTable() {

		if (isDescriptionColumnAvailable())
			return actualTtableName;
		return referenceTableName;
	}

	public String getWhereClauseColumn() {
		if (isDescriptionColumnAvailable())
			return primaryKeyColumnName;

		return actualColumnName;

	}

	public String getDescriptionClauseColumn() {
		if (isDescriptionColumnAvailable())
			return actualColumnName;
		return descColumnName;
	}

	public String getLevelValueColumnName() {
		String tableAliasName = isDescriptionColumnAvailable() ? actualTtableName : "HELPER_JOIN";
		return tableAliasName + "." + getDescriptionClauseColumn();
	}

	public boolean isDescriptionColumnAvailable() {
		return (descColumnName == null || descColumnName.isEmpty());
	}

	public String getMasterIdColumn() {
		return actualTtableName + "." + getWhereClauseColumn();
	}

	public String getMasterSidColumn() {
		return isDescriptionColumnAvailable() ? primaryKeyColumnName : mappingColumnName;
	}

}
