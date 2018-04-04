package com.stpl.gtn.gtn2o.ws.config;

public class GtnWsColumnDetailsConfig {

	private String aliasName;
	private String dbColumnName;
	private String dataType;
	private String tableAlias;
	private String whereClauseColumn;
	private String helperTableAliasName;
	private String helperTableColumnName;

	public GtnWsColumnDetailsConfig() {
	}

	public GtnWsColumnDetailsConfig(String aliasName, String dbName, String dataType, String tableAlias) {
		this.aliasName = aliasName;
		this.dbColumnName = dbName;
		this.dataType = dataType;
		this.tableAlias = tableAlias;
	}

	public GtnWsColumnDetailsConfig(String dbName, String dataType, String tableAlias) {
		this.dbColumnName = dbName;
		this.dataType = dataType;
		this.tableAlias = tableAlias;
	}

	public String getColumnNameForSelectClause() {
		StringBuilder selectClause = new StringBuilder();
		getColumnName(selectClause);
		return selectClause.append(' ').toString();
	}

	private void getColumnName(StringBuilder selectClause) {
		if (tableAlias != null && !"".equals(tableAlias)) {
			selectClause.append(tableAlias).append('.').append(dbColumnName);
		} else {
			selectClause.append(dbColumnName);
		}
	}

	public String getColumnNameForWhereClause() {
		StringBuilder selectClause = new StringBuilder();
		if (tableAlias != null && !"".equals(tableAlias)) {
			selectClause.append(tableAlias).append('.');
		}
		if (whereClauseColumn != null && !"".equals(whereClauseColumn)) {
			selectClause.append(whereClauseColumn);
		} else {

			selectClause.append(dbColumnName);
		}

		return selectClause.append(' ').toString();
	}

	public String getHelperTableMappedColumnNameForOrderByClause() {
		StringBuilder selectClause = new StringBuilder();
		selectClause.append(helperTableAliasName).append('.');

		selectClause.append(helperTableColumnName);

		return selectClause.append(' ').toString();
	}

	public String getColumnNameForWhereAndOrderByClause() {
		StringBuilder selectClause = new StringBuilder();
		if (tableAlias != null && !"".equals(tableAlias)) {
			selectClause.append(tableAlias).append('.');
		}
		if (whereClauseColumn != null && !"".equals(whereClauseColumn)) {
			selectClause.append(whereClauseColumn);
		} else {
			selectClause.append(dbColumnName);
		}

		return selectClause.append(' ').toString();
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getWhereClauseColumn() {
		return whereClauseColumn;
	}

	public void setWhereClauseColumn(String whereClauseColumn) {
		this.whereClauseColumn = whereClauseColumn;
	}

	public String getHelperTableAliasName() {
		return helperTableAliasName;
	}

	public void setHelperTableAliasName(String helperTableAliasName) {
		this.helperTableAliasName = helperTableAliasName;
	}

	public String getHelperTableColumnName() {
		return helperTableColumnName;
	}

	public void setHelperTableColumnName(String helperTableColumnName) {
		this.helperTableColumnName = helperTableColumnName;
	}

}
