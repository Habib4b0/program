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
    public final Object[] withoutIdenSearch = new Object[]{ConstantsUtils.ITEM_ID, ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE,
        ConstantsUtils.MANUFACTURED_ID, "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM,
        ConstantsUtils.SECONDARY_UOM, ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
        "pediatricExclusiveEndDate", "clottingFactorStartDate",
        "clottingFactorEndDate"};
    /**
     * The Constant WITHOUT_IDEN_SEARCH_HEADER.
     */
    public final String[] withoutIdenSearchHeader = new String[]{"item Id", ConstantsUtils.ITEMS_NO,
        ConstantsUtils.ITEM_NAMES, "item Desc", "item Status", "item Type", "item Code",
        "package Size", ConstantsUtils.UPPS, "item Start Date", "item End Date",
        "manufacturer Id", "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
        "secondary Uom", "item Class", "pediatric Exclusive Start Date",
        "pediatric Exclusive End Date", "clotting Factor Start Date",
        "clotting Factor End Date"};
    /**
     * The Constant WITH_IDEN_SEARCH.
     */
    public final Object[] withIdenSearch = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,
        "identifierTypeDesc",  ConstantsUtils.IDENTIFIER, ConstantsUtils.ITEM_CODE, ConstantsUtils.PACKAGE_SIZE,
        ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE, ConstantsUtils.MANUFACTURED_ID,
        "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
        "pediatricExclusiveEndDate", "clottingFactorStartDate",
        "clottingFactorEndDate"};
    /**
     * The Constant WITH_IDEN_SEARCH_HEADER.
     */
    public final String[] withIdenSearchHeader = new String[]{"item Id", ConstantsUtils.ITEMS_NO,
        ConstantsUtils.ITEM_NAMES, "item Desc", "item Status", "item Type", "identifier Type Description", ConstantsUtils.IDENTIFIER, "item Code",
        "package Size", ConstantsUtils.UPPS, "item Start Date", "item End Date",
        "manufacturer Id", "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
        "secondary Uom", "item Class", "pediatric Exclusive Start Date",
        "pediatric Exclusive End Date", "clotting Factor Start Date",
        "clotting Factor End Date"};
  
    
    /** The Constant AVAILABLE_ITEM_COL. */
    public final Object[] availableItemCol = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME, "displayItemStatus", "displayForm","strength","therapeuticClass","brand"};
    
    /** The Constant AVAILABLE_ITEM_COL_HEADER. */
    public final String[] availableItemColHeader = new String[]{
        "Item No", ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEMSTATUS, "Form","Strength","Therapeutic Class",ConstantsUtils.BRAND1};
    
    
    
    /**
     * The Constant SELECTED_ITEM_COL.
     */
    public final Object[] selectedItemCol = new Object[]{
        ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME};
    /**
     * The Constant SELECTED_ITEM_COL_HEADER.
     */
    public final String[] selectedItemColHeader = new String[]{
        "item No", ConstantsUtils.ITEM_NAMES};
    /**
     * The Constant COL_WITHOUT_IDEN.
     */
    public final Object[] colWithoutIden = new Object[]{ConstantsUtils.ITEM_ID_LABEL,
        ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Item Description", ConstantsUtils.ITEMSTATUS,
        ConstantsUtils.ITEMS_TYPE};
    /**
     * The Constant QUALIFIER_ITEM.
     */
    public final Object[] qualifierItem = new Object[]{
        ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.ITEM_IRT_QUALIFIER_NAME};
    /**
     * The Constant COL_WITH_IDEN.
     */
    public final Object[] colWithIden = new Object[]{ConstantsUtils.ITEM_ID_LABEL,
        ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Item Description", ConstantsUtils.ITEMSTATUS,
        ConstantsUtils.ITEMS_TYPE, "Qualifier", "Identifier"};
    /**
     * The Constant ITEMINFO_COL_ORDER.
     */
    public final Object[] itemInfoColOrder = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE};
    /**
     * The Constant ADDINFO_COL_ORDER.
     */
    public final Object[] addInfoColOrder = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
        ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
        ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE};
    /**
     * The Constant FORM_COL_ORDER.
     */
    public final Object[] formColOrder = new Object[]{ConstantsUtils.ITEM_ID,
        ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_NO, ConstantsUtils.MANUFACTURED_ID, ConstantsUtils.ITEM_DESC,
        ConstantsUtils.ITEM_STATUS, ConstantsUtils.IDENTIFIER, "identifierTypeDesc"};
    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    public final String[] colHeadersEnglish = new String[]{
        ConstantsUtils.ITEM_ID_LABEL, ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEMS_NO, "item Description",
        ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEMS_TYPE, "Manufacturer Id", "Identifier Type"};
    /**
     * The Constant IDEN_FORM_COL_ORDER.
     */
    public final Object[] idenFormColOrder = new Object[]{
        ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.ENTITY_CODE,
        ConstantsUtils.START_DATE,  ConstantsUtils.END_DATE, ConstantsUtils.IDENTIFIER_STATUS};
    /**
     * The Constant IDEN_FORM_COL_ORDER_HEADER.
     */
    public final String[] IdenFormColHeader = new String[]{
        "item Qualifier Name", "item Identifier", "entity Code",
        "start Date", "end Date", "identifier Status"};
    /**
     * The Constant PRICING_FORM_COL_ORDER.
     */
    public final Object[] pricingFormColOrder = new Object[]{
        "identifierCodeQualifierName", "itemPrice", "itemUom",
        ConstantsUtils.ENTITY_CODE, ConstantsUtils.START_DATE, ConstantsUtils.END_DATE, ConstantsUtils.IDENTIFIER_STATUS};
    /**
     * The Constant PRICING_FORM_COL_ORDER_HEADER.
     */
    public final String[] pricingFormColHeader = new String[]{
        "Pricing Qualifier Name", "item Price", "item Uom",
        "entity Code", "start Date", "end Date", "identifier Status"};
    /**
     * The Constant PRICING_GRID_COL_ORDER.
     */
    public final Object[] pricingGridColOrder = new Object[]{
        ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.ENTITY_CODE,
        ConstantsUtils.START_DATE, ConstantsUtils.END_DATE, ConstantsUtils.IDENTIFIER_STATUS};
    /**
     * Constructor
     */
    private UIUtils(){
    	//Empty
    }
}
