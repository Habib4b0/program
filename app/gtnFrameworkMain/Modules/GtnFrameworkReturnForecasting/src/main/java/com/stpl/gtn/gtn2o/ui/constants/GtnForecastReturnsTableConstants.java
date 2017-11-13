package com.stpl.gtn.gtn2o.ui.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnForecastReturnsTableConstants {

	private GtnForecastReturnsTableConstants() {
		super();
	}

	private static final String[] GTN_RETURNS_FORECAST_SERACH_TABLE_HEADERS = new String[] { "Projection Name",
			GtnFrameworkCommonConstants.DESCRIPTION, GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_HEADER,
			GtnFrameworkCommonConstants.PRODUCT_LEVEL_HEADER, GtnFrameworkCommonConstants.CREATED_BY_HEADER,
			GtnFrameworkCommonConstants.CREATED_DATE_HEADER, "Last Modified Date", "Company Name",
			GtnFrameworkCommonConstants.BUSINESS_UNIT_HEADER };
	private static final Object[] GTN_RETURNS_FORECAST_SERACH_TABLE_COLUMNS = new Object[] { "projectionName",
			"projectionDescription", "productHierarchy", "productLevel", "createdBy", "createdDate", "lastModifiedDate",
			"company", "businessUnit" };
	private static final Class<?>[] GTN_RETURNS_FORECAST_SERACH_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	private static final Class<?>[] GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	private static final String[] GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS_HEADERS = new String[] { "View Name",
			"Description", "Time Period:From", "Time Period:To", GtnFrameworkCommonConstants.COMPANY_RETURNS,
			GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_HEADER, GtnFrameworkCommonConstants.PRODUCT_LEVEL_HEADER, "Product Group",
			GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
			GtnFrameworkCommonConstants.CREATED_BY_HEADER, GtnFrameworkCommonConstants.BUSINESS_UNIT_HEADER };

	private static final Object[] GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS = new Object[] { "viewNameFilter",
			"viewDescriptionFilter", "fromTimePeriodFilter", "toTimePeriodFilter", "companyFilter",
			"productHierarchyFilter", "productLevelFilter", "productGroupFilter", "createdDateFilter",
			"modifiedByFilter", "createdByFilter", "businessUnitFilter" };

	private static final Class<?>[] GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	private static final String[] GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_HEADER = new String[] { "Product Group Name",
			"Product Group No", "Product Group Desc", "Company" };

	private static final Object[] GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_COLUMNS = new Object[] {
			"productGroupNameFilterView", "productGroupNoFilterView", "productGroupDescFilterView",
			"companyNameFilterView" };

	private static final Class<?>[] GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
			GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_UTIL_DATE };

	private static final String[] GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_HEADER = new String[] { "HierarchyName",
			"Highest Level", "Lowest Level", GtnFrameworkCommonConstants.CREATED_DATE_HEADER,
			GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER };

	private static final Object[] GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_COLUMNS = new Object[] { "hierName",
			"highestLevel", "lowestLevel", "createdDate", "modifiedDate" };

	private static final String[] GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_HEADERS = new String[] { "View Name",
			"Description", "Time Period:From", "Time Period:To", "Company",
			GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_HEADER, GtnFrameworkCommonConstants.PRODUCT_LEVEL_HEADER, "Product Group",
			GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
			GtnFrameworkCommonConstants.CREATED_BY_HEADER, GtnFrameworkCommonConstants.BUSINESS_UNIT_HEADER };

	private static final String[] GTN_RETURNS_FORECAST_DUAL_LIST_BOX_FILE_RECORD_HEADER = new String[] {
			"relationshipLevelSid", GtnFrameworkCommonConstants.LEVEL_VALUE, "levelNo", "hierarchyNo", "levelName",
			"form", "strength" };

	private static final String[] GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_VISIBLE_COLUMNS = new String[] {
			GtnFrameworkCommonConstants.LEVEL_VALUE };

	private static final String[] GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_VISIBLE_COLUMNS_NDC = new String[] {
			GtnFrameworkCommonConstants.LEVEL_VALUE, "form", "strength" };

	private static final String[] GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_HEADERS_NDC = new String[] { "NDC", "Form",
			"Strength" };

	private static final Class<?>[] GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	private static final Object[] GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_COLUMNS = new Object[] { "viewNameFilter",
			"viewDescriptionFilter", "fromTimePeriodFilter", "toTimePeriodFilter", "companyFilter",
			"productHierarchyFilter", "productLevelFilter", "productGroupFilter", "createdDateFilter",
			"modifiedByFilter", "createdByFilter", "businessUnitFilter" };

	public static Object[] getGtnReturnsForecastSerachTableColumns() {
		return GTN_RETURNS_FORECAST_SERACH_TABLE_COLUMNS.clone();
	}

	public static Class<?>[] getGtnReturnsForecastSerachTableColumnsDataType() {
		return GTN_RETURNS_FORECAST_SERACH_TABLE_COLUMNS_DATA_TYPE.clone();
	}

	public static Class<?>[] getGtnReturnsForecastPrivateViewTableColumnsDataType() {
		return GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS_DATA_TYPE.clone();
	}

	public static Object[] getGtnReturnsForecastPrivateViewTableColumns() {
		return GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS.clone();
	}

	public static Class<?>[] getGtnReturnsForecastProductGroupTableColumnsDataType() {
		return GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_COLUMNS_DATA_TYPE.clone();
	}

	public static String[] getGtnReturnsForecastProductGroupTableHeader() {
		return GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_HEADER.clone();
	}

	public static Object[] getGtnReturnsForecastProductGroupTableColumns() {
		return GTN_RETURNS_FORECAST_PRODUCT_GROUP_TABLE_COLUMNS.clone();
	}

	public static Class<?>[] getGtnReturnsForecastHeirarchyTableColumnsDataType() {
		return GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_COLUMNS_DATA_TYPE.clone();
	}

	public static String[] getGtnReturnsForecastHeirarchyTableHeader() {
		return GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_HEADER.clone();
	}

	public static Object[] getGtnReturnsForecastHeirarchyTableColumns() {
		return GTN_RETURNS_FORECAST_HEIRARCHY_TABLE_COLUMNS.clone();
	}

	public static String[] getGtnReturnsForecastSerachTableHeaders() {
		return GTN_RETURNS_FORECAST_SERACH_TABLE_HEADERS.clone();
	}

	public static String[] getGtnReturnsForecastPrivateViewTableColumnsHeaders() {
		return GTN_RETURNS_FORECAST_PRIVATE_VIEW_TABLE_COLUMNS_HEADERS.clone();
	}

	public static String[] getGtnReturnsForecastPublicViewTableHeaders() {
		return GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_HEADERS.clone();
	}

	public static String[] getGtnReturnsForecastDualListBoxFileRecordHeader() {
		return GTN_RETURNS_FORECAST_DUAL_LIST_BOX_FILE_RECORD_HEADER.clone();
	}

	public static String[] getGtnReturnsForecastDualListBoxLeftVisibleColumns() {
		return GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_VISIBLE_COLUMNS.clone();
	}

	public static String[] getGtnReturnsForecastDualListBoxLeftVisibleColumnsNdc() {
		return GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_VISIBLE_COLUMNS_NDC.clone();
	}

	public static String[] getGtnReturnsForecastDualListBoxLeftHeadersNdc() {
		return GTN_RETURNS_FORECAST_DUAL_LIST_BOX_LEFT_HEADERS_NDC.clone();
	}

	public static Class<?>[] getGtnReturnsForecastPublicViewTableColumnsDataType() {
		return GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_COLUMNS_DATA_TYPE.clone();
	}

	public static Object[] getGtnReturnsForecastPublicViewTableColumns() {
		return GTN_RETURNS_FORECAST_PUBLIC_VIEW_TABLE_COLUMNS.clone();
	}

}
