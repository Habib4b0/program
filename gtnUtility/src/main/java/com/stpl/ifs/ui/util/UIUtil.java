package com.stpl.ifs.ui.util;

import com.stpl.ifs.util.HeaderUtils;

public class UIUtil {

    public static String ITEM_STATUS = "ItemStatus";
    public static String STATE = "State";
    public static String COUNTRY = "Country";
    public static String CFP_TYPE = "CompanyFamilyPlanType";
    public static String TRADE_CLASS = "TradeClass";
    public static String MODE_SEARCH = "Search";
    public static String MODE_ADD = "Add";
    public static String NULL = "null";
    /**
     * The select one.
     */
    public static final String SELECT_ONE = "-Select One-";
    
    public static final Object[] AVAILABLE_ITEM_COL = new Object[]{
        HeaderUtils.ITEM_NO, HeaderUtils.ITEM_NAME, "form", "packageSize"};
    public static final String[] AVAILABLE_ITEM_COL_HEADER = new String[]{
        HeaderUtils.ITEM_NO_HEADER, HeaderUtils.ITEM_NAME_HEADER, "form", "package Size"};
    public static final Object[] SELECTED_ITEM_COL = new Object[]{
        HeaderUtils.ITEM_NO, HeaderUtils.ITEM_NAME};
    public static final String[] SELECTED_ITEM_COL_HEADER = new String[]{
        HeaderUtils.ITEM_NO_HEADER, HeaderUtils.ITEM_NAME_HEADER};
    public static final Object[] ITEM_DETAILS_COL = new Object[]{
        "checkbox", "itemFamilyplanNo", "itemFamilyplanName",
        HeaderUtils.ITEM_NO, HeaderUtils.ITEM_NAME, "uom", "packageSize", "itemStartDate", "itemEndDate", "ifpStartDate", "ifpEndDate", "itemStatus"};
    public static final String[] ITEM_DETAILS_COL_HEADER = new String[]{
        "", "item Familyplan No", "item Familyplan Name",
        HeaderUtils.ITEM_NO_HEADER, HeaderUtils.ITEM_NAME_HEADER, "Uom", "package Size", "item Start Date", "item End Date", "ifp Start Date", "ifp End Date", "item Status"};
    
    
        /**
     * Dataselection results visible columns
     */
    public static final Object[] DATASELECTION_COLUMNS = new Object[]{"projectionName", "description", "customerHierarchy", "customerHierarchyLevel", "productHierarchy", "productHierarchyLevel", "createdBy", "createdDateSearch", "modifiedDateSearch"};

    /**
     * Dataselection results column headers
     */
    public static final String[] DATASELECTION_HEADERS = new String[]{"Projection Name", "Description", "Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By", "Created Date", "Last Modified Date"};

    
     /**
     * Enum for Length constants
     */
    public enum LengthConstants {

        LENGTH_500(500),
        LENGTH_200(200);
        private int constant;

        private LengthConstants(int constant) {
            this.constant = constant;
        }

        public int getConstant() {
            return constant;
        }
    }
}
