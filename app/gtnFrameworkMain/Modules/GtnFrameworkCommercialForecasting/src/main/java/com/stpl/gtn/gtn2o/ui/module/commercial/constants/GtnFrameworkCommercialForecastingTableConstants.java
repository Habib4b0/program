package com.stpl.gtn.gtn2o.ui.module.commercial.constants;

import java.util.Date;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingTableConstants {

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_SERACH_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, String.class, String.class, Integer.class, String.class,
			Integer.class, String.class, Date.class, Date.class, String.class,
			String.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_SERACH_TABLE_HERDERS = new String[] { "Projection Name",
			"Description", "Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By",
			"Created Date", "Last Modified Date", "Company Name", "Business Unit" };

	public static final Object[] GTN_COMMERCIAL_FORECAST_SERACH_TABLE_COLUMNS = new Object[] { "projectionName",
			"projectionDescription", "customerHierarchy", "customerLevel", "productHierarchy", "productLevel",
			"createdBy", "createdDate", "lastModifiedDate", "company", "businessUnit" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, Integer.class, Integer.class, Date.class, Date.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_HEADER = new String[] {
			"HierarchyName", "Highest Level", "Lowest Level", "Created Date", "Modified Date" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_COLUMNS = new Object[] {
			"prodHierarchyLookupHierName", "prodHierarchyLookupHighestLevel", "prodHierarchyLookupLowestLevel",
			"prodHierarchyLookupCreatedDate", "prodHierarchyLookupModifiedDate" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, String.class, String.class, String.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_HEADER = new String[] {
			"Product Group Name", "Product Group No", "Product Group Desc", "Company" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_COLUMNS = new Object[] {
			"prodGroupLookupProductGroupNameFilterView", "prodGroupLookupProductGroupNoFilterView",
			"prodGroupLookupProductGroupDescFilterView", "prodGroupLookupCompanyNameFilterView" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, String.class, String.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_HEADER = new String[] {
			"Customer Group Name", "Customer Group No", "Customer Group Description" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_COLUMNS = new Object[] {
			"custGroupLookupCustomerGroupNameFilterView", "custGroupLookupCustomerGroupNoFilterView",
			"custGroupLookupCusomterDescFilterView" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, Integer.class, Integer.class, Date.class, Date.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_HEADER = new String[] {
			"Hierarchy Name", "Highest Level", "Lowest Level", "Created Date", "Modified Date" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_COLUMNS = new Object[] {
			"custHierarchyLookupHierName", "custHierarchyLookupHighestLevel", "custHierarchyLookupLowestLevel",
			"custHierarchyLookupCreatedDate", "custHierarchyLookupModifiedDate" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, String.class, String.class, String.class, String.class,
			Integer.class, String.class, String.class, String.class, String.class,
			Integer.class, String.class, Date.class, Date.class, String.class,
			String.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS_HERDERS = new String[] {
			"View Name", "Description", "Time Period:From", "Time Period:To", "Customer Hierarchy", "Customer Level",
			"Customer Group", "Company", "Brand Type", "Product Hierarchy", "Product Level", "Product Group",
			"Created Date", "Modified Date", "Created By", "Business Unit" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS = new Object[] {
			"privateViewLookupNameFilter", "privateViewLookupViewDescriptionFilter",
			"privateViewLookupFromTimePeriodFilter", "privateViewLookupToTimePeriodFilter",
			"privateViewLookupCustomerHierarchy", "privateViewLookupCustomerLevel", "privateViewLookupCustomerGroup",
			"privateViewLookupCompanyFilter", "privateViewLookupBrandType", "privateViewLookupProductHierarchyFilter",
			"privateViewLookupProductLevelFilter", "privateViewLookupProductGroupFilter",
			"privateViewLookupCreatedDateFilter", "privateViewLookupModifiedByFilter",
			"privateViewLookupreatedByFilter", "privateViewLookupBusinessUnitFilter" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class, String.class, String.class, String.class, String.class,
			Integer.class, String.class, String.class, String.class, String.class,
			Integer.class, String.class, Date.class, Date.class, String.class,
			String.class };

	public static final String[] GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_HERDERS = new String[] { "View Name",
			"Description", "Time Period:From", "Time Period:To", "Customer Hierarchy", "Customer Level",
			"Customer Group", "Company", "Brand Type", "Product Hierarchy", "Product Level", "Product Group",
			"Created Date", "Modified Date", "Created By", "Business Unit" };

	public static final Object[] GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_COLUMNS = new Object[] {
			"publicViewLookupNameFilter", "publicViewLookupViewDescriptionFilter",
			"publicViewLookupFromTimePeriodFilter", "publicViewLookupToTimePeriodFilter",
			"publicViewLookupCustomerHierarchy", "publicViewLookupCustomerLevel", "publicViewLookupCustomerGroup",
			"publicViewLookupCompanyFilter", "publicViewLookupBrandType", "publicViewLookupProductHierarchyFilter",
			"publicViewLookupProductLevelFilter", "publicViewLookupProductGroupFilter",
			"publicViewLookupCreatedDateFilter", "publicViewLookupModifiedByFilter", "publicViewLookupreatedByFilter",
			"publicViewLookupBusinessUnitFilter" };

	public static final Class<?>[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_COLUMNS_DATA_TYPE = new Class<?>[] {
			String.class};

	public static final String[] GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_HERDERS = new String[] { "LEVEL" };

	public static final Object[] GTN_COMMERCIAL_FORECAST_CUSTOMER_TABLE_COLUMNS = new Object[] { "customerLevel" };

	public static final String[] GTN_COMMERICAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_FILE_RECORD_HEADER = new String[] {
			"relationshipLevelSid", "levelValue", "levelNo", "hierarchyNo", "levelName", "form", "strength" };

	public static final String[] GTN_COMMERICAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_FILE_RECORD_HEADER = new String[] {
			"relationshipLevelSid", "levelValue", "levelNo", "hierarchyNo", "levelName" };
}
