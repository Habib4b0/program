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
    public final String[] ifpColHeaders = new String[]{
        "IFP ID", "IFP No", "IFP Name",
        ConstantsUtils.IFP_STATUS_LABEL, "IFP Type",
        "IFP Category", "IFP Start Date",
        ConstantsUtils.IFP_ENDDATE, "IFP Designation",
        "Total Dollar Commitment", "Commitment Period",
        "Total Volume Commitment", "Total Market share Commitment"};
    
    /** The Constant IFP_SEARCH_TABLE. */
    public final Object[] ifpSearchTable = new Object[]{
        ConstantsUtils.IFP_ID, ConstantsUtils.IFP_NO, ConstantsUtils.IFP_NAME,
        ConstantsUtils.IFP_STATUS, ConstantsUtils.IFP_TYPE,
        "itemFamilyplanCategory", ConstantsUtils.IFP_START_DATE,
        ConstantsUtils.IFP_END_DATE, "itemFamilyplanDesignation",
        "totalDollarCommitment", "commitmentPeriod",
        "totalVolumeCommitment", "totalMarketshareCommitment"};
    
    /** The Constant AVAILABLE_ITEM_COL. */
    public final Object[] availableItemColumn = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_DESC, "itemStatus", "displayForm","strength",ConstantsUtils.THERAPEUTIC_CLASS,ConstantsUtils.BRAND};
    
    /** The Constant AVAILABLE_ITEM_COL_HEADER. */
    public final String[] availableItemColumnHeader = new String[]{
        "Item No", ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEMS_DESC,ConstantsUtils.ITEMSTATUS, "Form",ConstantsUtils.STRENGH,ConstantsUtils.THERAPEUTIC_CLASS1,ConstantsUtils.BRAND1};
    
    /** The Constant SELECTED_ITEM_COL. */
    public final Object[] selectedItemColumn = new Object[]{ConstantsUtils.ITEM_NO,
        ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_DESC,"itemStatus","displayForm","strength",
        ConstantsUtils.THERAPEUTIC_CLASS,ConstantsUtils.BRAND};
    
    /** The Constant SELECTED_ITEM_COL_HEADER. */
    public final String[] selectedItemColumnHeader = new String[]{
        "Item No", ConstantsUtils.ITEM_NAMES,ConstantsUtils.ITEMS_DESC,ConstantsUtils.ITEMSTATUS, "Form",ConstantsUtils.STRENGH,ConstantsUtils.THERAPEUTIC_CLASS1,ConstantsUtils.BRAND1};
    
    /** The Constant ITEM_DETAILS_COL. */
    public final Object[] itemDetailsColumn = new Object[]{"checkbox",
        ConstantsUtils.ITEM_NO,ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_DESC,ConstantsUtils.IFP_STATUS,
        ConstantsUtils.IFP_START_DATE, ConstantsUtils.IFP_END_DATE, ConstantsUtils.ITEM_STATUS,
         ConstantsUtils.FORM, ConstantsUtils.STRENGTH,ConstantsUtils.THERAPEUTIC_CLASS, ConstantsUtils.BRAND,"attachedDate",
        "modifiedDate","modifiedBy","createdDate","createdBy"  
        };
    
    /** The Constant ITEM_DETAILS_COL_HEADER. */
    public final String[] itemDetailsColulmnHeader = new String[]{"",
        ConstantsUtils.ITEMS_NO,ConstantsUtils.ITEM_NAMES,ConstantsUtils.ITEMS_DESC,ConstantsUtils.IFP_STATUS_LABEL, ConstantsUtils.IFP_STARTDATE, ConstantsUtils.IFP_ENDDATE, ConstantsUtils.ITEMSTATUS,
       "Form",ConstantsUtils.STRENGH,"Therapy Class", ConstantsUtils.BRAND1,"Attached Date" ,"Modified Date","Modified By", "Created Date","Created By"};
    
    /** The Constant ITEM_DETAILS_COL_VIEW. */
    public final Object[] itemDetailsColumnView = new Object[]{
       ConstantsUtils.ITEM_NO,ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_DESC,"displayIFPStatus",
        ConstantsUtils.IFP_START_DATE, ConstantsUtils.IFP_END_DATE, ConstantsUtils.ITEM_STATUS,
         ConstantsUtils.FORM, ConstantsUtils.STRENGTH,ConstantsUtils.THERAPEUTIC_CLASS, ConstantsUtils.BRAND,"attachedDate",
        "modifiedDate","modifiedBy","createdDate","createdBy"  
        };
    
    /** The Constant ITEM_DETAILS_COL_HEADER_VIEW. */
    public final String[] itemDetailsColumnHeaderView = new String[]{
        ConstantsUtils.ITEMS_NO,ConstantsUtils.ITEM_NAMES,ConstantsUtils.ITEMS_DESC,ConstantsUtils.IFP_STATUS_LABEL, ConstantsUtils.IFP_STARTDATE, ConstantsUtils.IFP_ENDDATE, ConstantsUtils.ITEMSTATUS,
        "Form",ConstantsUtils.STRENGH,ConstantsUtils.THERAPEUTIC_CLASS1, ConstantsUtils.BRAND1,"Attached Date","Modified Date","Modified By", "Created Date","Created By" };
    
    /**
     * Gets the native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public NativeSelect getNativeSelect(final NativeSelect select,
            final List<HelperDTO> helperList) {
        LOGGER.debug("getNativeSelect method ");
        for (int i=0;i<helperList.size();i++) {
            select.addItem(helperList.get(i).getDescription());
        }
        LOGGER.debug("Retiurn native select "+select);
        return select;
    }
    
    /**
     * Search fields.
     *
     * @return the container
     */
    public void searchFields(ComboBox list) {
        list.addItem(ConstantsUtils.SELECT_ONE);        
        list.addItem(ConstantsUtils.BRAND1);
        list.addItem("Form");
         list.addItem(ConstantsUtils.ITEMS_ID);
        list.addItem(ConstantsUtils.ITEM_NAMES);
        list.addItem(ConstantsUtils.ITEMS_NO);
        list.addItem(ConstantsUtils.ITEMSTATUS);
        list.addItem(ConstantsUtils.STRENGH);
        list.addItem(ConstantsUtils.THERAPEUTIC_CLASS1);
        list.addItem("UOM");  
    }
    
    /**
     * Gets the selet null.
     *
     * @param select the select
     * @return the selet null
     */
    public static NativeSelect getSeletNull(final NativeSelect select) {
        LOGGER.debug("getSeletNull method");
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        LOGGER.debug("Return Native select");
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
        LOGGER.debug("getNativeSelectForIdentifier method");
        
        for (int i = 0; i < helperList.size(); i++) {
			final HelperDTO helperDTO = helperList.get(i);
			select.addItem(helperDTO.getDescription());
		}
        
        select.addItem("EditList");
         LOGGER.debug("Return Native select");
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
