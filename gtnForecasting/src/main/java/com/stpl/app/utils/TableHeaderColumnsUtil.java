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

    /**
     * Hierarchy lookup visible columns
     */
    public static final Object[] HIERARCHY_LOOKUP_COLUMNS = new Object[]{"hierarchyName", "highestLevel", "lowestLevel", "createdDateSearch", "modifiedDateSearch"};

    /**
     * Hierarchy lookup column headers
     */
    public static final String[] HIERARCHY_LOOKUP_HEADERS = new String[]{"Hierarchy Name", "Highest Level", "Lowest Level", "Created Date", "Modified Date"};

    /**
     * Dataselection results visible columns
     */
    public static final Object[] DATASELECTION_COLUMNS = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, "customerHierarchy", "customerHierarchyLevel", "productHierarchy", "productHierarchyLevel", "createdBy", "createdDateSearch", "modifiedDateSearch","companyName","businessUnitName"};
    /**
     * Dataselection results column headers
     */
    public static final String[] DATASELECTION_HEADERS = new String[]{"Projection Name", "Description", "Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By", "Created Date", "Last Modified Date","Company","Business Unit"};
    
    /**
     * Dataselection results visible columns
     */
    public static final Object[] DATASELECTION_COLUMNS_ACCRUAL = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, "deductionLevel", "deductionValue", "customerHierarchy", "customerHierarchyLevel", "productHierarchy", "productHierarchyLevel", "createdBy", "createdDateSearch", "modifiedDateSearch", "companyName", "businessUnitName"};
    /**
     * Dataselection results column headers
     */
    public static final String[] DATASELECTION_HEADERS_ACCRUAL = new String[]{"Projection Name", "Description", "Deduction Level", "Deduction Value", "Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By", "Created Date", "Last Modified Date", "Company", "Business Unit"};
    
        /**
     * Dataselection results visible columns
     */
    public static final Object[] DATASELECTION_COLUMNS_RETURNS = new Object[]{Constant.PROJECTION_NAME, Constant.DESCRIPTION, "productHierarchy", "productHierarchyLevel", "createdBy", "createdDateSearch", "modifiedDateSearch", "companyName", "businessUnitName"};
    /**
     * Dataselection results column headers
     */
    public static final String[] DATASELECTION_HEADERS_RETURNS = new String[]{"Projection Name", "Description", "Product Hierarchy", "Product Level", "Created By", "Created Date", "Last Modified Date", "Company", "Business Unit"};
    
    /**
     * Customer group lookup visible columns
     */
    public static final Object[] CUSTOMER_GROUP_LOOKUP_COLUMNS = new Object[]{"customerGroupName", "customerGroupNo", "segmentGroup", "segment"};
    
    public static final Object[] ACCRUAL_CUSTOMER_LOOKUP_COLUMNS = new Object[]{"customerGroupName", "customerGroupNo", "customergroupDescription"};
    /**
     * Customer group lookup results column headers
     */
    public static final String[] CUSTOMER_GROUP_LOOKUP_HEADERS = new String[]{"Customer Group Name", "Customer Group No", "Segment Group", "Segment"};

    public static final String[] ACCRUAL_CUSTOMER_LOOKUP_HEADERS = new String[]{"Customer Group Name", "Customer Group No", "Customer Group Desc."};
    /**
     * Product group lookup visible columns
     */
    public static final Object[] PRODUCT_GROUP_LOOKUP_COLUMNS = new Object[]{"productGroupName", "productGroupNo", "company", "segmentGroup", "segment"};

    public static final Object[] ACCRUAL_PRODUCT_LOOKUP_COLUMNS = new Object[]{"productGroupName", "productGroupNo", "productgroupDescription", "company"};
    /**
     * Product group lookup results column headers
     */
    public static final String[] PRODUCT_GROUP_LOOKUP_HEADERS = new String[]{"Product Group Name", "Product Group No", Constant.COMPANY_SMALL, "Segment Group", "Segment"};

    public static final String[] ACCRUAL_PRODUCT_LOOKUP_HEADERS = new String[]{"Product Group Name", "Product Group No", "Product Group Desc.", Constant.COMPANY_SMALL};
    /**
     * Visible columns for public/private view lookup tables
     */
    public static final Object[] VIEW_LOOKUP_COLUMNS = new Object[]{"viewName", Constant.DESCRIPTION, "from", "to", "customerHierarchy", "customerLevel", "customerGroup", "company", "brandType", "productHierarchy", "productLevel", "productGroup", "createdDateSearch", "modifiedDateSearch", "createdBy","businessUnitSystemName"};

    public static final Object[] VIEW_LOOKUP_COLUMNS_ARP = new Object[]{"viewName", Constant.DESCRIPTION, "from", "to","deductionLevel" ,"deductionValue","customerHierarchy", "customerLevel", "customerGroup", "company", "brandType", "productHierarchy", "productLevel", "productGroup", "createdDateSearch", "modifiedDateSearch", "createdBy","businessUnitSystemName"};
   public static final String[] VIEW_LOOKUP_HEADERS_ARP = new String[]{"View Name", "Description", "Time Period: From", "Time Period: To","Deduction Level","Deduction Value", "Customer Hierarchy", "Customer Level", "Customer Group", Constant.COMPANY_SMALL, "Brand Type", "Product Hierarchy", "Product Level", "Product Group", "Created Date", "Modified Date", "Created By","Business Unit"};
    public static final Object[] VIEW_LOOKUP_COLUMNS_RETURNS = new Object[]{"viewName", Constant.DESCRIPTION, "from", "to", "company","productHierarchy", "productLevel", "productGroup", "createdDateSearch", "modifiedDateSearch", "createdBy","businessUnitSystemName"};
    /**
     * Column headers for public/private view lookup tables
     */
    public static final String[] VIEW_LOOKUP_HEADERS = new String[]{"View Name", "Description", "Time Period: From", "Time Period: To", "Customer Hierarchy", "Customer Level", "Customer Group", Constant.COMPANY_SMALL, "Brand Type", "Product Hierarchy", "Product Level", "Product Group", "Created Date", "Modified Date", "Created By","Business Unit"};

    public static final String[] VIEW_LOOKUP_HEADERS_RETURNS = new String[]{"View Name", "Description", "Time Period: From", "Time Period: To", Constant.COMPANY_SMALL, "Product Hierarchy", "Product Level", "Product Group", "Created Date", "Modified Date", "Created By","Business Unit"};
    /**
     * Visible columns for Alternate Contract lookup table
     */
    public static final Object[] HISTORY_LOOKUP_CONTRACT_COLUMNS = new Object[]{"customer", "contractNumber", "contractName"};
    /**
     * Visible columns for Alternate Contract lookup table for NM
     */
    public static final Object[] HISTORY_LOOKUP_CONTRACT_COLUMNS_NM = new Object[]{"customerId", "contractHolder"};

    /**
     * Visible columns for Alternate brand lookup table
     */
    public static final Object[] HISTORY_LOOKUP_BRAND_COLUMNS = new Object[]{"brandSearch"};
    /**
     * Column headers for Alternate brand lookup table
     */
    public static final String[] HISTORY_LOOKUP_BRAND_HEADERS = new String[]{"Brand Name"};

    /**
     * Column headers for Alternate Contract lookup table
     */
    public static final String[] HISTORY_LOOKUP_CONTRACT_HEADERS = new String[]{"Customer", "Contract Number", "Contract Name"};
    /**
     * Column headers for Alternate Contract lookup table for NM
     */
    public static final String[] HISTORY_LOOKUP_CONTRACT_HEADERS_NM = new String[]{"Customer ID", "Contract Holder"};

    /**
     * Visible columns for available and selected customer and products
     */
    public static final Object[] AVAILABLE_SELECTED_CUSTOMER_PRODUCTS_VC = new Object[]{"relationshipLevelValue"};

    /**
     * Visible columns for available products when level is NDC
     */
    public static final Object[] AVAILABLE_PRODUCTS_NDC_VC = new Object[]{"relationshipLevelValue", "form", "strength"};

    /**
     * Column headers for available products when level is NDC
     */
    public static final String[] AVAILABLE_PRODUCTS_NDC_CH = new String[]{Constant.NDC, "Form", "Strength"};

    /**
     * Column headers for available customer and products
     */
    public static final String[] SELECTED_CUSTOMER_CH = new String[]{"Customer Hierarchy Group Builder"};

    /**
     * Column headers for available customer and products
     */
    public static final String[] AVAILABLE_CUSTOMER_PRODUCTS_DEFAULT_CH = new String[]{"Level"};

    /**
     * Column headers for selected customer and products
     */
    public static final String[] SELECTED_PRODUCTS_CH = new String[]{"Product Hierarchy Group Builder"};
    
    
    /**
     * Data Assumption Tab Visible Column
     */

    public static final Object[] DATA_ASSUMPTION_COLUMNS = new Object[]{"activeFileName", "activeFileCompany", "activeFileBusinessUnit", "activeFiletype", "activeFileVersion", "activeFileFromDate", "activeFileFromPeriod", "activeFileToPeriod"};
   
    
    public static final Object[] DATA_ASSUMPTION_COLUMNS_EXCEL = new Object[]{"activeFileName", "activeFileCompany", "activeFileBusinessUnit", "activeFiletype", "activeFileVersion", "activeFileFromDateString", "activeFileFromPeriodString", "activeFileToPeriodString"};

    /**
     * Data Assumption Tab Visible Header
     */
    public static final String[] DATA_ASSUMPTION_HEADERS = new String[]{"File", "Company", "Business Unit", "Type", "Version", "Active From", "From Period", "To Period"};

    
}
