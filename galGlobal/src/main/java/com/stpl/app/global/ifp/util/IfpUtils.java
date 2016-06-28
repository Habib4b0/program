package com.stpl.app.global.ifp.util;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;


/**
 * The Class IfpUtils.
 */
public class IfpUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(IfpUtils.class);
    
    /** The Constant EMPTY. */
    public static final String EMPTY = "";
    
    /** The Constant STRING_ASTERISK. */
    public static final String STRING_ASTERISK = "*";
    
    /** The Constant MMDDYYYY. */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    
    /** The Constant MMDDYYYYHHMMMSS. */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    
    /** The Constant STRING_NULL. */
    public static final String STRING_NULL = ConstantsUtils.NULL;
    
    /** The Constant STRING_ZERO. */
    public static final String STRING_ZERO = "0";
    
    /** The Constant CHAR_PERCENT. */
    public static final char CHAR_PERCENT = '%';
    
    /** The Constant CHAR_ASTERISK. */
    public static final char CHAR_ASTERISK = '*';

    /** The Constant IFP_COL_HEADERS. */
    public static final String[] IFP_COL_HEADERS = new String[]{
        "IFP ID", "IFP No", "IFP Name",
        "IFP Status", "IFP Type",
        "IFP Category", "IFP Start Date",
        "IFP End Date", "IFP Designation",
        "Total Dollar Commitment", "Commitment Period",
        "Total Volume Commitment", "Total Market share Commitment"};
    
    /** The Constant IFP_SEARCH_TABLE. */
    public static final Object[] IFP_SEARCH_TABLE = new Object[]{
        ConstantsUtils.IFP_ID, ConstantsUtils.IFP_NO, ConstantsUtils.IFP_NAME,
        ConstantsUtils.IFP_STATUS, ConstantsUtils.IFP_TYPE,
        "itemFamilyplanCategory", "itemFamilyplanStartDate",
        "itemFamilyplanEndDate", "itemFamilyplanDesignation",
        "totalDollarCommitment", "commitmentPeriod",
        "totalVolumeCommitment", "totalMarketshareCommitment"};
    
    /** The Constant AVAILABLE_ITEM_COL. */
    public static final Object[] AVAILABLE_ITEM_COL = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME,"itemDesc", "itemStatus", "displayForm","strength","therapeuticClass","brand"};
    
    /** The Constant AVAILABLE_ITEM_COL_HEADER. */
    public static final String[] AVAILABLE_ITEM_COL_HEADER = new String[]{
        "Item No", "Item Name", "Item Desc","Item Status", "Form","Strength","Therapeutic Class","Brand"};
    
    /** The Constant SELECTED_ITEM_COL. */
    public static final Object[] SELECTED_ITEM_COL = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME,"itemDesc","itemStatus","displayForm","strength",
        "therapeuticClass","brand"};
    
    /** The Constant SELECTED_ITEM_COL_HEADER. */
    public static final String[] SELECTED_ITEM_COL_HEADER = new String[]{
        "Item No", "Item Name","Item Desc","Item Status", "Form","Strength","Therapeutic Class","Brand"};
    
    /** The Constant ITEM_DETAILS_COL. */
    public static final Object[] ITEM_DETAILS_COL = new Object[]{"checkbox",
        ConstantsUtils.ITEM_NO,ConstantsUtils.ITEM_NAME,"itemDesc",ConstantsUtils.IFP_STATUS,
        "itemFamilyplanStartDate", "itemFamilyplanEndDate", ConstantsUtils.ITEM_STATUS,
         ConstantsUtils.FORM, ConstantsUtils.STRENGTH,"therapeuticClass", "brand","attachedDate",
        "modifiedDate","modifiedBy","createdDate","createdBy"  
        };
    
    /** The Constant ITEM_DETAILS_COL_HEADER. */
    public static final String[] ITEM_DETAILS_COL_HEADER = new String[]{"",
        ConstantsUtils.ITEMS_NO,"Item Name","Item Desc","IFP Status", ConstantsUtils.IFP_STARTDATE, "IFP End Date", "Item Status",
       "Form","Strength","Therapy Class", "Brand","Attached Date" ,"Modified Date","Modified By", "Created Date","Created By"};
    
    /** The Constant ITEM_DETAILS_COL_VIEW. */
    public static final Object[] ITEM_DETAILS_COL_VIEW = new Object[]{
       ConstantsUtils.ITEM_NO,ConstantsUtils.ITEM_NAME,"itemDesc","displayIFPStatus",
        "itemFamilyplanStartDate", "itemFamilyplanEndDate", ConstantsUtils.ITEM_STATUS,
         ConstantsUtils.FORM, ConstantsUtils.STRENGTH,"therapeuticClass", "brand","attachedDate",
        "modifiedDate","modifiedBy","createdDate","createdBy"  
        };
    
    /** The Constant ITEM_DETAILS_COL_HEADER_VIEW. */
    public static final String[] ITEM_DETAILS_COL_HEADER_VIEW = new String[]{
        ConstantsUtils.ITEMS_NO,"Item Name","Item Desc","IFP Status", ConstantsUtils.IFP_STARTDATE, "IFP End Date", "Item Status",
        "Form","Strength","Therapeutic Class", "Brand","Attached Date","Modified Date","Modified By", "Created Date","Created By" };
    
    /**
     * Gets the native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public NativeSelect getNativeSelect(final NativeSelect select,
            final List<HelperDTO> helperList) {
        LOGGER.info("getNativeSelect method ");
        for (int i=0;i<helperList.size();i++) {
            select.addItem(helperList.get(i).getDescription());
        }
        LOGGER.info("Retiurn native select "+select);
        return select;
    }
    
    /**
     * Search fields.
     *
     * @return the container
     */
    public void searchFields(ComboBox list) {
        list.addItem(ConstantsUtils.SELECT_ONE);        
        list.addItem("Brand");
        list.addItem("Form");
         list.addItem(ConstantsUtils.ITEMS_ID);
        list.addItem("Item Name");
        list.addItem(ConstantsUtils.ITEMS_NO);
        list.addItem("Item Status");
        list.addItem("Strength");
        list.addItem("Therapeutic Class");
        list.addItem("UOM");  
    }
    
    /**
     * Gets the selet null.
     *
     * @param select the select
     * @return the selet null
     */
    public static NativeSelect getSeletNull(final NativeSelect select) {
        LOGGER.info("getSeletNull method");
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        LOGGER.info("Return Native select");
        return select;
    }
    
    /**
     * Gets the native select for identifier.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select for identifier
     */
    public NativeSelect getNativeSelectForIdentifier(final NativeSelect select,
            final List<HelperDTO> helperList) {
        LOGGER.info("getNativeSelectForIdentifier method");
        
        for (int i = 0; i < helperList.size(); i++) {
			final HelperDTO helperDTO = helperList.get(i);
			select.addItem(helperDTO.getDescription());
		}
        
        select.addItem("EditList");
         LOGGER.info("Return Native select");
        return select;
    }
    
    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public NativeSelect getStatusSelect(final NativeSelect select) {
        select.addItem("Active");
        select.addItem("InActive");
        return select;
    }
}
