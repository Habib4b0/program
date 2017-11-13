package com.stpl.gtn.gtn2o.ws.config;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

public class GtnWsSearchQueryConfigProvider {

	private static final GtnWsSearchQueryConfigProvider gtnWsSearchQueryConfigProvider = new GtnWsSearchQueryConfigProvider();

	public GtnWsColumnDetailsConfig getColumnStringConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig stringColumnConfig = new GtnWsColumnDetailsConfig();
		stringColumnConfig.setDbColumnName(dbColumnName);
		stringColumnConfig.setDataType(GtnFrameworkWebserviceConstant.STRING);
		stringColumnConfig.setTableAlias(tableAlias);
		return stringColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnUserConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType("User");
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnDateConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType("Date");
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnIntegerConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType(GtnFrameworkWebserviceConstant.INTEGER);
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnBigDecimalConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType(GtnFrameworkWebserviceConstant.BIG_DECIMAL);
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnHelperConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType(GtnFrameworkWebserviceConstant.HELPER);
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnBooleanConfig(String dbColumnName, String tableAlias) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName(dbColumnName);
		userColumnConfig.setDataType(GtnFrameworkWebserviceConstant.A_BOOLEAN);
		userColumnConfig.setTableAlias(tableAlias);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnStringConfig(String dbColumnName, String tableAlias, String aliasName) {
		GtnWsColumnDetailsConfig stringColumnColumnConfig = getColumnStringConfig(dbColumnName, tableAlias);
		stringColumnColumnConfig.setAliasName(aliasName);
		return stringColumnColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnUserConfig(String dbColumnName, String tableAlias, String aliasName) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnUserConfig(dbColumnName, tableAlias);
		userColumnConfig.setAliasName(aliasName);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnDateConfig(String dbColumnName, String tableAlias, String aliasName) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnDateConfig(dbColumnName, tableAlias);
		userColumnConfig.setAliasName(aliasName);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnIntegerConfig(String dbColumnName, String tableAlias, String aliasName) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnIntegerConfig(dbColumnName, tableAlias);
		userColumnConfig.setAliasName(aliasName);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnStringConfig(String dbColumnName, String tableAlias, String aliasName,
			String whereClauseColumn) {
		GtnWsColumnDetailsConfig stringColumnColumnConfig = getColumnStringConfig(dbColumnName, tableAlias, aliasName);
		stringColumnColumnConfig.setWhereClauseColumn(whereClauseColumn);
		return stringColumnColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnUserConfig(String dbColumnName, String tableAlias, String aliasName,
			String whereClauseColumn) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnUserConfig(dbColumnName, tableAlias, aliasName);
		userColumnConfig.setWhereClauseColumn(whereClauseColumn);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnDateConfig(String dbColumnName, String tableAlias, String aliasName,
			String whereClauseColumn) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnDateConfig(dbColumnName, tableAlias, aliasName);
		userColumnConfig.setWhereClauseColumn(whereClauseColumn);
		return userColumnConfig;
	}

	public GtnWsColumnDetailsConfig getColumnIntegerConfig(String dbColumnName, String tableAlias, String aliasName,
			String whereClauseColumn) {
		GtnWsColumnDetailsConfig userColumnConfig = getColumnIntegerConfig(dbColumnName, tableAlias, aliasName);
		userColumnConfig.setWhereClauseColumn(whereClauseColumn);
		return userColumnConfig;
	}

	public static GtnWsSearchQueryConfigProvider getInstance() {
		return gtnWsSearchQueryConfigProvider;
	}

}
