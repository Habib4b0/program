/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.gtnforecasting.utils.Constant;

/**
 *
 * @author soundarrajan
 */
public class TableHeaderColumnsUtil {

    public static final String MODIFIED_DATE_SEARCH = "modifiedDateSearch";
    public static final String CREATED_DATE_SEARCH = "createdDateSearch";

    /**
     * Hierarchy lookup visible columns
     */
    private static final Object[] hierarchyLookupColumns = new Object[]{"hierarchyName", "highestLevel", "lowestLevel", CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH};
    public static final String CREATED_DATE_LABEL = "Created Date";
    public static final String MODIFIED_DATE = "Modified Date";

    /**
     * Hierarchy lookup column headers
     */
    private static final String[] hierarchyLookupHeaders = new String[]{"Hierarchy Name", "Highest Level", "Lowest Level", CREATED_DATE_LABEL, MODIFIED_DATE};
    public static final String BUSINESS_UNIT_NAME = "businessUnitName";
    public static final String CREATED_BY_PROPERTY = "createdBy";
    public static final String COMPANY_NAME_PROPERTY = "companyName";
    public static final String CUSTOMER_HIERARCHY = "customerHierarchy";
    public static final String PRODUCT_HIERARCHY_LEVEL_PROPERTY = "productHierarchyLevel";
    public static final String PRODUCT_HIERARCHY1 = "productHierarchy";

    /**
     * Dataselection results visible columns
     */
    private static final Object[] dataSelectionColumns = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, CUSTOMER_HIERARCHY, "customerHierarchyLevel", PRODUCT_HIERARCHY1, PRODUCT_HIERARCHY_LEVEL_PROPERTY, CREATED_BY_PROPERTY, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, COMPANY_NAME_PROPERTY, BUSINESS_UNIT_NAME};
    public static final String PROJECTION_NAME_LABEL = "Projection Name";
    public static final String PRODUCT_LEVEL_LABEL = "Product Level";
    public static final String CUSTOMER_LEVEL_LABEL = "Customer Level";
    public static final String PRODUCT_HIERARCHY = "Product Hierarchy";
    public static final String COMPANY_LABEL = "Company";
    public static final String LAST_MODIFIED_DATE_LABEL = "Last Modified Date";
    public static final String CUSTOMER_HIERARCHY_LABEL = "Customer Hierarchy";
    public static final String DESCRIPTION = "Description";
    public static final String CREATED_BY_LABEL = "Created By";
    public static final String BUSINESS_UNIT = "Business Unit";
    /**
     * Dataselection results column headers
     */
    private static final String[] dataSelectionHeaders = new String[]{PROJECTION_NAME_LABEL, DESCRIPTION, CUSTOMER_HIERARCHY_LABEL, CUSTOMER_LEVEL_LABEL, PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, CREATED_BY_LABEL, CREATED_DATE_LABEL, LAST_MODIFIED_DATE_LABEL, COMPANY_LABEL, BUSINESS_UNIT};
    
    /**
     * Dataselection results visible columns
     */
    private static final Object[] dataselectionColumnsAccrual = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, "deductionLevel", "deductionValue", CUSTOMER_HIERARCHY, "customerHierarchyLevel", PRODUCT_HIERARCHY1, PRODUCT_HIERARCHY_LEVEL_PROPERTY, CREATED_BY_PROPERTY, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, COMPANY_NAME_PROPERTY, BUSINESS_UNIT_NAME};
    /**
     * Dataselection results column headers
     */
    private static final String[] dataSelectionHeadersAccrual = new String[]{PROJECTION_NAME_LABEL, DESCRIPTION, "Deduction Level", "Deduction Value", CUSTOMER_HIERARCHY_LABEL, CUSTOMER_LEVEL_LABEL, PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, CREATED_BY_LABEL, CREATED_DATE_LABEL, LAST_MODIFIED_DATE_LABEL, COMPANY_LABEL, BUSINESS_UNIT};
    
        /**
     * Dataselection results visible columns
     */
    private static final Object[] dataSelectionColumnReturns = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, PRODUCT_HIERARCHY1, PRODUCT_HIERARCHY_LEVEL_PROPERTY, CREATED_BY_PROPERTY, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, COMPANY_NAME_PROPERTY, BUSINESS_UNIT_NAME};
    /**
     * Dataselection results column headers
     */
    private static final String[] dataSelectionHeaderReturns = new String[]{PROJECTION_NAME_LABEL, DESCRIPTION, PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, CREATED_BY_LABEL, CREATED_DATE_LABEL, LAST_MODIFIED_DATE_LABEL, COMPANY_LABEL, BUSINESS_UNIT};
    
    /**
     * Customer group lookup visible columns
     */
    private static final Object[] customerGroupLookupColumns = new Object[]{"customerGroupName", "customerGroupNo", "segmentGroup", "segment"};
    
    private static final Object[] accrualCustomerLookupColumns = new Object[]{"customerGroupName", "customerGroupNo", "customergroupDescription"};
    /**
     * Customer group lookup results column headers
     */
    private static final String[] customerGroupLookupHeaders = new String[]{"Customer Group Name", "Customer Group No", "Segment Group", Constant.SEGMENT_LABEL};

    private static final String[] accrualCustomerLookupHeaders = new String[]{"Customer Group Name", "Customer Group No", "Customer Group Desc."};
    public static final String COMPANY_PROPERTY = "company";
    /**
     * Product group lookup visible columns
     */
    private static final Object[] productGroupLookupColumns = new Object[]{"productGroupName", "productGroupNo", COMPANY_PROPERTY, "segmentGroup", "segment"};

    private static final Object[] accrualProductLookupColumns = new Object[]{"productGroupName", "productGroupNo", "productgroupDescription", COMPANY_PROPERTY};
    /**
     * Product group lookup results column headers
     */
    private static final String[] productGroupLookupHeaders = new String[]{"Product Group Name", "Product Group No", Constant.COMPANY_SMALL, "Segment Group", Constant.SEGMENT_LABEL};

    private static final String[] accrualProductLookUpHeaders = new String[]{"Product Group Name", "Product Group No", "Product Group Desc.", Constant.COMPANY_SMALL};
    public static final String PRODUCT_LEVEL = "productLevel";
    public static final String PRODUCT_GROUP = "productGroup";
    public static final String VIEW_NAME = "viewName";
    public static final String BUSINESS_UNIT_SYSTEM_NAME = "businessUnitSystemName";
    /**
     * Visible columns for public/private view lookup tables
     */
    private static final Object[] viewLookupColumns = new Object[]{VIEW_NAME, Constant.DESCRIPTION, "from", "to", CUSTOMER_HIERARCHY, "customerLevel", "customerGroup", COMPANY_PROPERTY, "brandType", PRODUCT_HIERARCHY1, PRODUCT_LEVEL, PRODUCT_GROUP, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, CREATED_BY_PROPERTY, BUSINESS_UNIT_SYSTEM_NAME};

    private static final Object[] viewLookupColumnsArp = new Object[]{VIEW_NAME, Constant.DESCRIPTION, "from", "to","deductionLevel" ,"deductionValue", CUSTOMER_HIERARCHY, "customerLevel", "customerGroup", COMPANY_PROPERTY, "brandType", PRODUCT_HIERARCHY1, PRODUCT_LEVEL, PRODUCT_GROUP, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, CREATED_BY_PROPERTY, BUSINESS_UNIT_SYSTEM_NAME};
    public static final String TIME_PERIOD_FROM_LABEL = "Time Period: From";
    public static final String TIME_PERIOD_TO_LABEL = "Time Period: To";
    public static final String VIEW_NAME_LABEL = "View Name";
    public static final String PRODUCT_GROUP_LABEL = "Product Group";
    private static final String[] viewLookupHeadersARP = new String[]{VIEW_NAME_LABEL, DESCRIPTION, TIME_PERIOD_FROM_LABEL, TIME_PERIOD_TO_LABEL,"Deduction Level","Deduction Value", CUSTOMER_HIERARCHY_LABEL, CUSTOMER_LEVEL_LABEL, "Customer Group", Constant.COMPANY_SMALL, "Brand Type", PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, PRODUCT_GROUP_LABEL, CREATED_DATE_LABEL, MODIFIED_DATE, CREATED_BY_LABEL, BUSINESS_UNIT};
    private static final Object[] viewLookupColumnReturns = new Object[]{VIEW_NAME, Constant.DESCRIPTION, "from", "to", COMPANY_PROPERTY, PRODUCT_HIERARCHY1, PRODUCT_LEVEL, PRODUCT_GROUP, CREATED_DATE_SEARCH, MODIFIED_DATE_SEARCH, CREATED_BY_PROPERTY, BUSINESS_UNIT_SYSTEM_NAME};
    /**
     * Column headers for public/private view lookup tables
     */
    private static final String[] viewLookupHeaders = new String[]{VIEW_NAME_LABEL, DESCRIPTION, TIME_PERIOD_FROM_LABEL, TIME_PERIOD_TO_LABEL, CUSTOMER_HIERARCHY_LABEL, CUSTOMER_LEVEL_LABEL, "Customer Group", Constant.COMPANY_SMALL, "Brand Type", PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, PRODUCT_GROUP_LABEL, CREATED_DATE_LABEL, MODIFIED_DATE, CREATED_BY_LABEL, BUSINESS_UNIT};

    private static final String[] viewLookupHeadersReturns = new String[]{VIEW_NAME_LABEL, DESCRIPTION, TIME_PERIOD_FROM_LABEL, TIME_PERIOD_TO_LABEL, Constant.COMPANY_SMALL, PRODUCT_HIERARCHY, PRODUCT_LEVEL_LABEL, PRODUCT_GROUP_LABEL, CREATED_DATE_LABEL, MODIFIED_DATE, CREATED_BY_LABEL, BUSINESS_UNIT};
    /**
     * Visible columns for Alternate Contract lookup table
     */
    private static final Object[] historyLookupContractColumns = new Object[]{"customer", "contractNumber", "contractName"};
    /**
     * Visible columns for Alternate Contract lookup table for NM
     */
    private static final Object[] historyLookupContractColumnsNm = new Object[]{"customerId", "contractHolder"};

    /**
     * Visible columns for Alternate brand lookup table
     */
    private static final Object[] historyLookupBrandColumns = new Object[]{"brandSearch"};
    /**
     * Column headers for Alternate brand lookup table
     */
    private static final String[] historyLookupBrandHeaders = new String[]{"Brand Name"};

    /**
     * Column headers for Alternate Contract lookup table
     */
    private static final String[] historyLookupcontractHeaders = new String[]{"Customer", "Contract Number", "Contract Name"};
    /**
     * Column headers for Alternate Contract lookup table for NM
     */
    private static final String[] historyLookupcontractHeadersNm = new String[]{"Customer ID", "Contract Holder"};

    /**
     * Visible columns for available and selected customer and products
     */
    private static final Object[] availableSelectedCustomerProductsVc = new Object[]{"relationshipLevelValue"};

    /**
     * Visible columns for available products when level is NDC
     */
    private static final Object[] availableProductsNdcVc = new Object[]{"relationshipLevelValue", "form", "strength"};

    /**
     * Column headers for available products when level is NDC
     */
    private static final String[] availableProductsNdcCh = new String[]{Constant.NDC, "Form", "Strength"};

    /**
     * Column headers for available customer and products
     */
    private static final String[] selectedCustomerCh = new String[]{"Customer Hierarchy Group Builder"};

    /**
     * Column headers for available customer and products
     */
    private static final String[] availableCustomerProductsDefaultCh = new String[]{"Level"};

    /**
     * Column headers for selected customer and products
     */
    private static final String[] selectedProductsCh = new String[]{"Product Hierarchy Group Builder"};
    
    
    /**
     * Data Assumption Tab Visible Column
     */

    private static final Object[] dataAssumptionColumns = new Object[]{"activeFileName", "activeFileCompany", "activeFileBusinessUnit", "activeFiletype", "activeFileVersion", "activeFileFromDate", "activeFileFromPeriod", "activeFileToPeriod"};
   
    
    private static final Object[] dataAssumptionsColumnsExcel = new Object[]{"activeFileName", "activeFileCompany", "activeFileBusinessUnit", "activeFiletype", "activeFileVersion", "activeFileFromDateString", "activeFileFromPeriodString", "activeFileToPeriodString"};

    /**
     * Data Assumption Tab Visible Header
     */
    private static final String[] dataAssumptionHeaders = new String[]{"File", COMPANY_LABEL, BUSINESS_UNIT, "Type", "Version", "Active From", "From Period", "To Period"};

    private static TableHeaderColumnsUtil object;

    /**
     * Constructor
     */
    private TableHeaderColumnsUtil() {
        //Empty
    }

    public static TableHeaderColumnsUtil getInstance() {
        if (object == null) {
            object = new TableHeaderColumnsUtil();
        }
        return object;
    }

	public static Object[] getHierarchyLookupColumns() {
		return hierarchyLookupColumns.clone();
	}

	public static String[] getHierarchyLookupHeaders() {
		return hierarchyLookupHeaders.clone();
	}

	public static Object[] getDataSelectionColumns() {
		return dataSelectionColumns.clone();
	}

	public static String[] getDataSelectionHeaders() {
		return dataSelectionHeaders.clone();
	}

	public static Object[] getDataSelectionColumnsAccrual() {
		return dataselectionColumnsAccrual.clone();
	}

	public static String[] getDataSelectionHeadersAccrual() {
		return dataSelectionHeadersAccrual.clone();
	}

	public static Object[] getDataSelectionColumnReturns() {
		return dataSelectionColumnReturns.clone();
	}

	public static String[] getDataSelectionHeaderReturns() {
		return dataSelectionHeaderReturns.clone();
	}

	public static Object[] getCustomerGroupLookupColumns() {
		return customerGroupLookupColumns.clone();
	}

	public static Object[] getAccrualCustomerLookupColumns() {
		return accrualCustomerLookupColumns.clone();
	}

	public static String[] getCustomerGroupLookupHeaders() {
		return customerGroupLookupHeaders.clone();
	}

	public static String[] getAccrualCustomerLookupHeaders() {
		return accrualCustomerLookupHeaders.clone();
	}

	public static Object[] getProductGroupLookupColumns() {
		return productGroupLookupColumns.clone();
	}

	public static Object[] getAccrualProductLookupColumns() {
		return accrualProductLookupColumns.clone();
	}

	public static String[] getProductGroupLookupHeaders() {
		return productGroupLookupHeaders.clone();
	}

	public static String[] getAccrualProductLookupHeaders() {
		return accrualProductLookUpHeaders.clone();
	}

	public static Object[] getViewLookupColumns() {
		return viewLookupColumns.clone();
	}

	public static Object[] getViewLookupColumnsArp() {
		return viewLookupColumnsArp.clone();
	}

	public static String[] getViewLookupHeadersArp() {
		return viewLookupHeadersARP.clone();
	}

	public static Object[] getViewLookupColumsReturns() {
		return viewLookupColumnReturns.clone();
	}

	public static String[] getViewLookupHeaders() {
		return viewLookupHeaders.clone();
	}

	public static String[] getViewLookupHeadersReturns() {
		return viewLookupHeadersReturns.clone();
	}

	public static Object[] getHistoryLookupContractColumns() {
		return historyLookupContractColumns.clone();
	}

	public static Object[] getHistoryLookupContractColumnsNm() {
		return historyLookupContractColumnsNm.clone();
	}

	public static Object[] getHistoryLookupBrandColumns() {
		return historyLookupBrandColumns.clone();
	}

	public static String[] getHistoryLookupBrandHeaders() {
		return historyLookupBrandHeaders.clone();
	}

	public static String[] getHistoryLookupContractHeaders() {
		return historyLookupcontractHeaders.clone();
	}

	public static String[] getHistoryLookupContractHeadersNm() {
		return historyLookupcontractHeadersNm.clone();
	}

	public static Object[] getAvailableSelectedCustomerProductsVc() {
		return availableSelectedCustomerProductsVc.clone();
	}

	public static Object[] getAvailableProductsNdcVc() {
		return availableProductsNdcVc.clone();
	}

	public static String[] getAvailableProductsNdcCh() {
		return availableProductsNdcCh.clone();
	}

	public static String[] getSelectedCustomerCh() {
		return selectedCustomerCh.clone();
	}

	public static String[] getAvailableCustomerProductsDefaultCh() {
		return availableCustomerProductsDefaultCh.clone();
	}

	public static String[] getSelectedProductsCh() {
		return selectedProductsCh.clone();
	}

	public static Object[] getDataAssumptionColumns() {
		return dataAssumptionColumns.clone();
	}

	public static Object[] getDataAssumptionsColumnsExcel() {
		return dataAssumptionsColumnsExcel.clone();
	}

	public static String[] getDataAssumptionHeaders() {
		return dataAssumptionHeaders.clone();
	}
}
