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
    public final Object[] hierarchyLookupColumns = new Object[]{"hierarchyName", "highestLevel", "lowestLevel", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    

    /**
     * Hierarchy lookup column headers
     */
    public final String[] hierarchyLookupHeaders = new String[]{"Hierarchy Name", "Highest Level", "Lowest Level", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL};
    

    /**
     * Dataselection results visible columns
     */
    public final Object[] dataselectionColumns = new Object[]{"projectionName", StringConstantsUtil.DESCRIPTION1, "customerHierarchy", "customerHierarchyLevel", StringConstantsUtil.PRODUCT_HIERARCHY1, "productHierarchyLevel", StringConstantsUtil.CREATED_BY1, StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    /**
     * Dataselection results column headers
     */
    public final String[] dataselectionHeaders = new String[]{"Projection Name", StringConstantsUtil.DESCRIPTION, "Customer Hierarchy", "Customer Level", StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, StringConstantsUtil.CREATED_BY_LABEL, StringConstantsUtil.CREATED_DATE_LABEL, "Last Modified Date"};
    
        /**
     * Dataselection results visible columns
     */
    public final Object[] dataselectionColumnsReturns = new Object[]{"projectionName", StringConstantsUtil.DESCRIPTION1, StringConstantsUtil.PRODUCT_HIERARCHY1, "productHierarchyLevel", StringConstantsUtil.CREATED_BY1, StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH};
    /**
     * Dataselection results column headers
     */
    public final String[] dataselectionHeadersReturns = new String[]{"Projection Name", StringConstantsUtil.DESCRIPTION, StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, StringConstantsUtil.CREATED_BY_LABEL, StringConstantsUtil.CREATED_DATE_LABEL, "Last Modified Date"};
    
    /**
     * Customer group lookup visible columns
     */
    public final Object[] customerGroupLookupColumns = new Object[]{"customerGroupName", "customerGroupNo", "customergroupDescription"};

    /**
     * Customer group lookup results column headers
     */
    public final String[] customerGroupLookupHeaders = new String[]{"Customer Group Name", "Customer Group #", "Customer Group Description"};

    /**
     * Product group lookup visible columns
     */
    public final Object[] productGroupLookupColumns = new Object[]{"productGroupName", "productGroupNo", "productgroupDescription", StringConstantsUtil.COMPANY_PROPERTY};
  

    /**
     * Product group lookup results column headers
     */
    public final String[] productGroupLookupHeaders = new String[]{"Product Group Name", "Product Group #","Product Group Description", StringConstantsUtil.COMPANY_LABEL};

    /**
     * Visible columns for public/private view lookup tables
     */
    public final Object[] viewLookupColumns = new Object[]{StringConstantsUtil.COMPANY_PROPERTY, "businessUnitSystemName","viewName", StringConstantsUtil.DESCRIPTION1, "from", "to", "customerHierarchy", "customerLevel", "customerGroup","brandType", StringConstantsUtil.PRODUCT_HIERARCHY1, "productLevel", "productGroup", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH, StringConstantsUtil.CREATED_BY1};
    
    public final Object[] viewLookupColumnsReturns = new Object[]{"viewName", StringConstantsUtil.DESCRIPTION1, "from", "to", StringConstantsUtil.COMPANY_PROPERTY, StringConstantsUtil.PRODUCT_HIERARCHY1, "productLevel", "productGroup", StringConstantsUtil.CREATED_DATE_SEARCH, StringConstantsUtil.MODIFIED_DATE_SEARCH, StringConstantsUtil.CREATED_BY1};
    /**
     * Column headers for public/private view lookup tables
     */
    public final String[] viewLookupHeaders = new String[]{StringConstantsUtil.COMPANY_LABEL,"Business Unit","View Name", StringConstantsUtil.DESCRIPTION, "Time Period: From", "Time Period: To", "Customer Hierarchy", "Customer Level", "Customer Group", "Brand Type", StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, "Product Group", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL, StringConstantsUtil.CREATED_BY_LABEL};

    public final String[] viewLookupHeadersReturns = new String[]{"View Name", StringConstantsUtil.DESCRIPTION, "Time Period: From", "Time Period: To", StringConstantsUtil.COMPANY_LABEL, StringConstantsUtil.PRODUCT_HIERARCHY_LABEL, StringConstantsUtil.PRODUCT_LEVEL_LABEL, "Product Group", StringConstantsUtil.CREATED_DATE_LABEL, StringConstantsUtil.MODIFIED_DATE_LABEL, StringConstantsUtil.CREATED_BY_LABEL};
    /**
     * Visible columns for Alternate Contract lookup table
     */
    public final Object[] historyLookupContractColumns = new Object[]{"customer", "contractNumber", "contractName"};
    /**
     * Visible columns for Alternate Contract lookup table for NM
     */
    public final Object[] historyLookupContractColumnsNm = new Object[]{"customerId", "contractHolder"};

    /**
     * Visible columns for Alternate brand lookup table
     */
    public final Object[] historyLookupBrandColumns = new Object[]{"brandSearch"};
    /**
     * Column headers for Alternate brand lookup table
     */
    public final String[] historyLookupBrandHeaders = new String[]{"Brand Name"};

    /**
     * Column headers for Alternate Contract lookup table
     */
    public final String[] historyLookupContractHeaders = new String[]{"Customer", "Contract Number", "Contract Name"};
    /**
     * Column headers for Alternate Contract lookup table for NM
     */
    public final String[] historyLookupContractHeadersNm = new String[]{"Customer ID", "Contract Holder"};

    /**
     * Visible columns for available and selected customer and products
     */
    public final Object[] availableSelectedCustomerProductsVc = new Object[]{"relationshipLevelValue"};

    /**
     * Visible columns for available products when level is NDC
     */
    public final Object[] availableProductsNdcVc = new Object[]{"relationshipLevelValue", "form", "strength"};

    /**
     * Column headers for available products when level is NDC
     */
    public final String[] availableProductsNdcCh = new String[]{"NDC", "Form", "Strength"};

    /**
     * Column headers for available customer and products
     */
    public final String[] selectedCustomerCh = new String[]{"Customer Hierarchy Group Builder"};

    /**
     * Column headers for available customer and products
     */
    public final String[] availableCustomerProductsDefaultCh = new String[]{"Level"};

    /**
     * Column headers for selected customer and products
     */
    public final String[] selectedProductsCh = new String[]{"Product Hierarchy Group Builder"};
    
    private static TableHeaderColumnsUtil object;

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
