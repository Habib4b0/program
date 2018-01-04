/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author mohamed.hameed
 */
public class TableHeaderColumnsUtil {

    /**
     * Hierarchy lookup visible columns
     */
    public static final Object[] HIERARCHY_LOOKUP_COLS = new Object[]{"hierarchyName", "highestLevel", "lowestLevel", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    

    /**
     * Hierarchy lookup column headers
     */
    public static final String[] HIERARCHY_LOOKUP_HEADERS = new String[]{"Hierarchy Name", "Highest Level", "Lowest Level", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL};
    

    /**
     * Dataselection results visible columns
     */
    protected static final Object[] DATA_SELECTION_COLS = new Object[]{"projectionName", StringConstantsUtil.DESCRIPTION1, "customerHierarchy", "customerHierarchyLevel", StringConstantsUtil.PRODUCT_HIERARCHY1, "productHierarchyLevel", StringConstantsUtil.CREATED_BY1, StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    /**
     * Dataselection results column headers
     */
    protected static final String[] DATA_SELECTION_HEADERS = new String[]{"Projection Name", StringConstantsUtil.DESCRIPTION, "Customer Hierarchy", "Customer Level", StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, StringConstantsUtil.CREATED_BY_LABEL, StringConstantsUtil.CREATED_DATE_LABEL, "Last Modified Date"};
    
        /**
     * Dataselection results visible columns
     */
    protected static final Object[] DATA_SELECTION_COLS_RETURNS = new Object[]{"projectionName", StringConstantsUtil.DESCRIPTION1, StringConstantsUtil.PRODUCT_HIERARCHY1, "productHierarchyLevel", StringConstantsUtil.CREATED_BY1, StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    /**
     * Dataselection results column headers
     */
    protected static final String[] DATA_SELECTION_HEADER_RETURNS = new String[]{"Projection Name", StringConstantsUtil.DESCRIPTION, StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, StringConstantsUtil.CREATED_BY_LABEL, StringConstantsUtil.CREATED_DATE_LABEL, "Last Modified Date"};
    
    /**
     * Customer group lookup visible columns
     */
    public static final Object[] CUSTOMER_GROUP_LOOKUP_COLS = new Object[]{"customerGroupName", "customerGroupNo", "customergroupDescription"};

    /**
     * Customer group lookup results column headers
     */
    public static final String[] CUST_GRP_LOOKUP_HEADERS = new String[]{"Customer Group Name", "Customer Group #", "Customer Group Description"};

    /**
     * Product group lookup visible columns
     */
    public static final Object[] PROD_GRP_LOOKUP_COLS = new Object[]{"productGroupName", "productGroupNo", "productgroupDescription", StringConstantsUtil.COMPANY_PROPERTY};
  

    /**
     * Product group lookup results column headers
     */
    public static final String[] PRD_GRP_LOOKUP_HEADERS = new String[]{"Product Group Name", "Product Group #","Product Group Description", StringConstantsUtil.COMPANY_LABEL};

    /**
     * Visible columns for protected/private view lookup tables
     */
    public static final Object[] VIEW_LOOKUP_COLS = new Object[]{StringConstantsUtil.COMPANY_PROPERTY, "businessUnitSystemName","viewName", StringConstantsUtil.DESCRIPTION1, "from", "to", "customerHierarchy", "customerLevel", "customerGroup","brandType", StringConstantsUtil.PRODUCT_HIERARCHY1, "productLevel", "productGroup", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH, StringConstantsUtil.CREATED_BY1};
    
    public static final Object[] VIEW_LOOKUP_COLS_RETURNS = new Object[]{"viewName", StringConstantsUtil.DESCRIPTION1, "from", "to", StringConstantsUtil.COMPANY_PROPERTY, StringConstantsUtil.PRODUCT_HIERARCHY1, "productLevel", "productGroup", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH, StringConstantsUtil.CREATED_BY1};
    /**
     * Column headers for protected/private view lookup tables
     */
    public static final String[] VIEW_LOOKUP_HEADER = new String[]{StringConstantsUtil.COMPANY_LABEL,"Business Unit","View Name", StringConstantsUtil.DESCRIPTION, "Time Period: From", "Time Period: To", "Customer Hierarchy", "Customer Level", "Customer Group", "Brand Type", StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, "Product Group", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL, StringConstantsUtil.CREATED_BY_LABEL};

    public static final String[] VIEW_LOOKUP_HEADER_RETURNS = new String[]{"View Name", StringConstantsUtil.DESCRIPTION, "Time Period: From", "Time Period: To", StringConstantsUtil.COMPANY_LABEL, StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, "Product Group", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL, StringConstantsUtil.CREATED_BY_LABEL};
    /**
     * Visible columns for Alternate Contract lookup table
     */
    protected static final Object[] HIST_LOOKUP_CNT_COLS = new Object[]{"customer", "contractNumber", "contractName"};
    /**
     * Visible columns for Alternate Contract lookup table for NM
     */
    protected static final Object[] HIST_LOOKUP_CNT_COLS_NM = new Object[]{"customerId", "contractHolder"};

    /**
     * Visible columns for Alternate brand lookup table
     */
    protected static final Object[] HIST_LOOKUP_BRAND_COLS = new Object[]{"brandSearch"};
    /**
     * Column headers for Alternate brand lookup table
     */
    protected static final String[] HIST_LOOKUP_BRAND_HEADER = new String[]{"Brand Name"};

    /**
     * Column headers for Alternate Contract lookup table
     */
    protected static final String[] HIST_LOOKUP_CNT_HEADER = new String[]{"Customer", "Contract Number", "Contract Name"};
    /**
     * Column headers for Alternate Contract lookup table for NM
     */
    protected static final String[] HIST_LOOKUP_CNT_HEADER_NM = new String[]{"Customer ID", "Contract Holder"};

    /**
     * Visible columns for available and selected customer and products
     */
    protected static final Object[] AVLBLE_SELECTED_CUSTOMER_PROD_VC = new Object[]{"relationshipLevelValue"};

    /**
     * Visible columns for available products when level is NDC
     */
    protected static final Object[] AVAILABLE_PRODS_NDC_VC = new Object[]{"relationshipLevelValue", "form", "strength"};

    /**
     * Column headers for available products when level is NDC
     */
    protected static final String[] AVAILABLE_PRODS_NDC_CH = new String[]{"NDC", "Form", "Strength"};

    /**
     * Column headers for available customer and products
     */
    protected static final String[] SELECTED_CUST_CH = new String[]{"Customer Hierarchy Group Builder"};

    /**
     * Column headers for available customer and products
     */
    protected static final String[] AVAILABLE_CUST_PRD_DEFAULT_CH = new String[]{"Level"};

    /**
     * Column headers for selected customer and products
     */
    protected static final String[] SELECTED_PRODS_CH = new String[]{"Product Hierarchy Group Builder"};
    
    protected static TableHeaderColumnsUtil object;

    /**
     * Constructor
     */
    private TableHeaderColumnsUtil() {
        /*
            Constructor
        */
    }

    public static TableHeaderColumnsUtil getInstance() {
        if (object == null) {
            object = new TableHeaderColumnsUtil();
        }
        return object;
    }
}
