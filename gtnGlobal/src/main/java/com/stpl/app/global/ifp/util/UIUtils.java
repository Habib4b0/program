package com.stpl.app.global.ifp.util;

import com.stpl.app.util.ConstantsUtils;

/**
 * The Class UIUtils.
 */
public final class UIUtils {

    /**
     * The item type.
     */
    public static final String ITEM_TYPE = "ItemType";
    /**
     * The uom.
     */
    public static final String UOM = "Uom";
    /**
     * The form.
     */
    public static final String FORM = "FORM";
    /**
     * The strength.
     */
    public static final String STRENGTH = "STRENGTH";
    /**
     * The ifp status.
     */
    public static final String IFP_STATUS = "IFP_STATUS";
    
    public static final String STATUS = "STATUS";
    /**
     * The ifp type.
     */
    public static final String IFP_TYPE = "IFP_TYPE";
    /**
     * The ifp designation.
     */
    public static final String IFP_DESIGNATION = "IFP_DESIGNATION";
    /**
     * The ifp category.
     */
    public static final String IFP_CATEGORY = "IFP_CATEGORY";
    /**
     * The Constant WITHOUT_IDEN_SEARCH.
     */
    public static final Object[] WITHOUT_IDEN_SEARCH = new Object[]{ConstantsUtils.ITEM_ID, ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE,
        "manufacturerId", "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM,
        ConstantsUtils.SECONDARY_UOM, ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
        "pediatricExclusiveEndDate", "clottingFactorStartDate",
        "clottingFactorEndDate"};
    /**
     * The Constant WITHOUT_IDEN_SEARCH_HEADER.
     */
    public static final String[] WITHOUT_IDEN_SEARCH_HEADER = new String[]{"item Id", ConstantsUtils.ITEMS_NO,
        ConstantsUtils.ITEM_NAMES, "item Desc", "item Status", "item Type", "item Code",
        "package Size", ConstantsUtils.UPPS, "item Start Date", "item End Date",
        "manufacturer Id", "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
        "secondary Uom", "item Class", "pediatric Exclusive Start Date",
        "pediatric Exclusive End Date", "clotting Factor Start Date",
        "clotting Factor End Date"};
    /**
     * The Constant WITH_IDEN_SEARCH.
     */
    public static final Object[] WITH_IDEN_SEARCH = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,
        "identifierTypeDesc", "identifier", ConstantsUtils.ITEM_CODE, ConstantsUtils.PACKAGE_SIZE,
        ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE, "manufacturerId",
        "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
        "pediatricExclusiveEndDate", "clottingFactorStartDate",
        "clottingFactorEndDate"};
    /**
     * The Constant WITH_IDEN_SEARCH_HEADER.
     */
    public static final String[] WITH_IDEN_SEARCH_HEADER = new String[]{"item Id", ConstantsUtils.ITEMS_NO,
        ConstantsUtils.ITEM_NAMES, "item Desc", "item Status", "item Type", "identifier Type Description", "identifier", "item Code",
        "package Size", ConstantsUtils.UPPS, "item Start Date", "item End Date",
        "manufacturer Id", "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
        "secondary Uom", "item Class", "pediatric Exclusive Start Date",
        "pediatric Exclusive End Date", "clotting Factor Start Date",
        "clotting Factor End Date"};
  
    
    /** The Constant AVAILABLE_ITEM_COL. */
    public static final Object[] AVAILABLE_ITEM_COL = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME, "displayItemStatus", "displayForm","strength","therapeuticClass","brand"};
    
    /** The Constant AVAILABLE_ITEM_COL_HEADER. */
    public static final String[] AVAILABLE_ITEM_COL_HEADER = new String[]{
        "Item No", "Item Name", "Item Status", "Form","Strength","Therapeutic Class","Brand"};
    
    
    
    /**
     * The Constant SELECTED_ITEM_COL.
     */
    public static final Object[] SELECTED_ITEM_COL = new Object[]{
        ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME};
    /**
     * The Constant SELECTED_ITEM_COL_HEADER.
     */
    public static final String[] SELECTED_ITEM_COL_HEADER = new String[]{
        "item No", ConstantsUtils.ITEM_NAMES};
    /**
     * The Constant COL_WITHOUT_IDEN.
     */
    public static final Object[] COL_WITHOUT_IDEN = new Object[]{"Item Id",
        ConstantsUtils.ITEMS_NO, "Item Name", "Item Description", "Item Status",
        "Item Type"};
    /**
     * The Constant QUALIFIER_ITEM.
     */
    public static final Object[] QUALIFIER_ITEM = new Object[]{
        "itemIdentifier", "itemIrtQualifierName"};
    /**
     * The Constant COL_WITH_IDEN.
     */
    public static final Object[] COL_WITH_IDEN = new Object[]{"Item Id",
        ConstantsUtils.ITEMS_NO, "Item Name", "Item Description", "Item Status",
        "Item Type", "Qualifier", "Identifier"};
    /**
     * The Constant ITEMINFO_COL_ORDER.
     */
    public static final Object[] ITEMINFO_COL_ORDER = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE};
    /**
     * The Constant ADDINFO_COL_ORDER.
     */
    public static final Object[] ADDINFO_COL_ORDER = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE};
    /**
     * The Constant FORM_COL_ORDER.
     */
    public static final Object[] FORM_COL_ORDER = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_NO, "manufacturerId", ConstantsUtils.ITEM_DESC,
        ConstantsUtils.ITEM_STATUS, "identifier", "identifierTypeDesc"};
    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    public static final String[] COL_HEADERS_ENGLISH = new String[]{
        "Item Id", "Item Name", ConstantsUtils.ITEMS_NO, "item Description",
        "Item Status", "Item Type", "Manufacturer Id", "Identifier Type"};
    /**
     * The Constant IDEN_FORM_COL_ORDER.
     */
    public static final Object[] IDEN_FORM_COL_ORDER = new Object[]{
        "itemIrtQualifierName", "itemIdentifier", "entityCode",
        "startDate", "endDate", "identifierStatus"};
    /**
     * The Constant IDEN_FORM_COL_ORDER_HEADER.
     */
    public static final String[] IDEN_FORM_COL_ORDER_HEADER = new String[]{
        "item Qualifier Name", "item Identifier", "entity Code",
        "start Date", "end Date", "identifier Status"};
    /**
     * The Constant PRICING_FORM_COL_ORDER.
     */
    public static final Object[] PRICING_FORM_COL_ORDER = new Object[]{
        "identifierCodeQualifierName", "itemPrice", "itemUom",
        "entityCode", "startDate", "endDate", "identifierStatus"};
    /**
     * The Constant PRICING_FORM_COL_ORDER_HEADER.
     */
    public static final String[] PRICING_FORM_COL_ORDER_HEADER = new String[]{
        "Pricing Qualifier Name", "item Price", "item Uom",
        "entity Code", "start Date", "end Date", "identifier Status"};
    /**
     * The Constant PRICING_GRID_COL_ORDER.
     */
    public static final Object[] PRICING_GRID_COL_ORDER = new Object[]{
        "itemIrtQualifierName", "itemIdentifier", "entityCode",
        "startDate", "endDate", "identifierStatus"};
    /**
     * Constructor
     */
    private UIUtils(){
    	//Empty
    }
}
