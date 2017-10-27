package com.stpl.app.global.priceschedule.util;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * Utility Class for Price Schedule.
 *
 * @author manikanta
 */
public class PsUtils {

    /**
     * The Constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The Constant STRING_ASTERISK.
     */
    public static final String STRING_ASTERISK = "*";
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = ConstantsUtils.DATE_FORMAT;
    /**
     * The Constant MMDDYYYYHHMMMSS.
     */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * The Constant STRING_NULL.
     */
    public static final String STRING_NULL = ConstantsUtils.NULL;
    /**
     * The Constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    
    static HashMap<String, String> columnName=new HashMap<String, String>();

    
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PsUtils.class);
    /**
     * The Constant PS_COL_HEADERS.
     */
    public final String[] psColHeaders = new String[]{"System ID",
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name",
        "Price Schedule Type","Price Schedule Status", 
        "Price Schedule Category", "Start Date",
        "End Date", "Price Schedule Designation","Parent ID","Parent Name",
        "Trade Class"};


    /**
     * The Constant PS_SEARCH_TABLE.
     */
    public final Object[] psSearchTable = new Object[]{"psSystemId",
        ConstantsUtils.PS_ID, ConstantsUtils.PS_NO, ConstantsUtils.PS_NAME,
        ConstantsUtils.PS_TYPE,ConstantsUtils.PS_STATUS, 
        "priceScheduleCategory", "priceScheduleStartDate",
        "priceScheduleEndDate", "priceScheduleDesignation","parentId","parentName", ConstantsUtils.TRADE_CLASS};
    /**
     * The Constant PARENT_COL_HEADERS.
     */
    public final String[] parentColHeaders = new String[]{
        "PS ID", "PS No", "PS Name",
        "PS Status", "PS Type"};
    /**
     * The Constant PARENT_COL_OBJECT.
     */
    public final Object[] parentColObject = new Object[]{
        ConstantsUtils.PS_ID, ConstantsUtils.PS_NO,  ConstantsUtils.PS_NAME,
        ConstantsUtils.PS_STATUS, ConstantsUtils.PS_TYPE};
    /**
     * The Constant ITEM_DETAILS_COL.
     */
    public final Object[] itemDetailsCol = new Object[]{ConstantsUtils.CHECK_RECORD,
        ConstantsUtils.ITEM_ID1, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, "brand", "itemStatus",
        ConstantsUtils.CONTRACT_PRICE_START_DATE, ConstantsUtils.CONTRACT_PRICE_END_DATE,
        ConstantsUtils.PRICE_TYPE, ConstantsUtils.PRICE, "suggestedPrice","source","createdUserName","createdDate",
        ConstantsUtils.ATTACHED_DATE_PROPERTY
    };
    /**
     * The Constant ITEM_DETAILS_COL.
     */
    public final Object[] itemDetailsColView = new Object[]{
        ConstantsUtils.IFP_NO, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.PRICE, ConstantsUtils.PRICE_TYPE,
        "contractPrice", ConstantsUtils.CONTRACT_PRICE_START_DATE, ConstantsUtils.CONTRACT_PRICE_END_DATE,
        ConstantsUtils.PRICE_TOLERANCE, ConstantsUtils.PRICE_PROTECTION_START_DATE,
        ConstantsUtils.PRICE_PROTECTION_END_DATE, "priceToleranceTypeValue",
        "priceToleranceIntervalValue", "priceToleranceFrequencyValue", "basePrice",
        "revisionDate", ConstantsUtils.ATTACHED_DATE_PROPERTY};
    
    
     /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public final Object[] priceProtectionCol = new Object[]{ConstantsUtils.CHECK_RECORD,
        ConstantsUtils.ITEM_ID1,ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, "brand", ConstantsUtils.PRICE_PROTECTION_STATUS,
        ConstantsUtils.PRICE_PROTECTION_START_DATE, ConstantsUtils.PRICE_PROTECTION_END_DATE, ConstantsUtils.PRICE_PROTECTION_PRICE_TYPE,
        "nep", "nepFormula","basePriceType","basePriceValue",
        "netBasePrice","netBasePriceFormulaName","subsequentPeriodPriceType","netSubsequentPeriodPrice","netSubsequentPriceFormulaName",        
        "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType",
        ConstantsUtils.PRICE_TOLERANCE,ConstantsUtils.MAC_INC_CHANGE,"resetEligible",ConstantsUtils.RESET_TYPE,
        ConstantsUtils.RESET_DATE, ConstantsUtils.RESET_INTERVAL, ConstantsUtils.RESET_FREQUENCY,"resetPriceType","netResetPriceType","netResetPriceFormulaName",
        ConstantsUtils.NET_PRICE_TYPE,"netPriceTypeFormulaName",
        ConstantsUtils.ATTACHED_DATE_PROPERTY};
    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public final String[] priceProtectionColHeader = new String[]{
        "", "Item ID", ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Brand", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type" ,"Base Price","Net Base Price",
        "Net Base Price Formula","Subsequent Period Price Type","Net Subsequent Period Price","Net Subsequent Period Price Formula",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", 
        "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type","Net Reset Price Type","Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula",
        ConstantsUtils.ATTACHED_DATE};
    
    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public final String[] itemDetailsColHeader = new String[]{
        "", "Item ID", ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Brand","Status",
        "CP Start Date", "CP End Date",
         "Price Type","Price",
         "Suggested Price","Source","Created By","Created Date",
        ConstantsUtils.ATTACHED_DATE};
    
    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public final String[] itemDetailsColHeaderView = new String[]{
        ConstantsUtils.IFP_NO1, ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Price", "Price Type",
        "Contract Price", "Contract Price Start Date", "Contract Price End Date",
        "Price Tolerance", "Price Protection Start Date",
        "Price Protection End Date", "Price Tolerance Type",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Base Price",
        "Revision Date", ConstantsUtils.ATTACHED_DATE};   
    
    /**
     * The Constant AVAILABLE_IFP_COL.
     */
    public final Object[] availableIfpCol = new Object[]{
        ConstantsUtils.ITEM_FAMILY_PLAN_ID,ConstantsUtils.IFP_NO, ConstantsUtils.IFP_NAME,ConstantsUtils.IFP_STATUS1,"ifpStartDate","ifpEndDate","ifpType","ifpCategory"};
     /**
     * The Constant AVAILABLE_IFP_COL.
     */
    public final Object[] availableIfpColView = new Object[]{
        ConstantsUtils.ITEM_FAMILY_PLAN_ID,ConstantsUtils.IFP_NO,  ConstantsUtils.IFP_NAME,ConstantsUtils.IFP_STATUS1,"ifpStartDate","ifpEndDate","ifpType","ifpCategory"};
    
    /**
     * The Constant AVAILABLE_IFP_COL_HEADER.
     */
    public final String[] availableIfpColHeader = new String[]{
        "IFP ID",ConstantsUtils.IFP_NO1, "IFP Name","IFP Status","Start Date","End Date","IFP Type","IFP Category"};
    /** The Constant NEP FORMULA LOOKUP COLUMNS IN PS. */
    public final Object[] nepFormulaLookup = new Object[]{
        "nepFormulaType","nepFormulaID", "nepFormulaNo", "nepFormulaName","createdBy","createdDate","modifiedBy","modifiedDate"};
    
    /** The Constant NEP FORMULA LOOKUP HEADER IN PS. */ 
    public final String[] nepFormulaLookupHeader = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name","Created By","Created Date","Modified By","Modified Date"}; 
    
    private PsUtils() {
        //Empty
    }

    private static PsUtils object;

    public static PsUtils getInstance() {
        if (object == null) {
            object = new PsUtils();
        }
        return object;
    }
    /**
     * To Add Fields to Container.
     *
     * @return Container
     */
    public Container searchFields() {
        final List<String> list = new ArrayList<>();
      list.add(ConstantsUtils.SELECT_ONE);
      list.add("IFP Name");
      list.add(ConstantsUtils.IFP_NO1);
              return new IndexedContainer(list);
    }
    /**
     * @return the lOGGER
     */
    public Logger getLOGGER() {
        return LOGGER;
    }
    
    public static String getDBColumnName(String visibleColumnName) {
        return columnName.containsKey(visibleColumnName) ? columnName.get(visibleColumnName) : visibleColumnName;
    }
    
   public static HashMap<String, String> loadColumnName() {
       columnName.clear();
       
       columnName.put(ConstantsUtils.CHECK_RECORD, ConstantsUtils.CHECK_RECORD);
       columnName.put(ConstantsUtils.PS_ID, "psId");
       columnName.put(ConstantsUtils.PS_NO, "psNo");
       columnName.put(ConstantsUtils.PS_NAME, "psName");
       columnName.put(ConstantsUtils.PS_STATUS, "psStatus");
       columnName.put(ConstantsUtils.PS_TYPE, "psType");
       columnName.put("priceScheduleCategory", "psCategory");
       columnName.put("priceScheduleStartDate", "psStartDate");
       columnName.put("priceScheduleEndDate", "psEndDate");
       columnName.put("priceScheduleDesignation", "psDesignation");
       columnName.put(ConstantsUtils.TRADE_CLASS, ConstantsUtils.TRADE_CLASS);
       
       return columnName;
   } 
    
   public static HashMap<String, String> loadAvailableColumnName() {
       columnName.clear();
   
       columnName.put(ConstantsUtils.IFP_NO, "ifpNo");
       columnName.put( ConstantsUtils.IFP_NAME, "ifpName");
       columnName.put(ConstantsUtils.ITEM_FAMILY_PLAN_ID, "ifpId");
       columnName.put(ConstantsUtils.IFP_STATUS1, "ifpStatus");
      
       
       return columnName;
   } 
   public static HashMap<String, String> loadItemDetailsColumnName() {
       columnName.clear();
       columnName.put(ConstantsUtils.CHECK_RECORD, ConstantsUtils.CHECK_RECORD);
       columnName.put(ConstantsUtils.IFP_NO, "ifpNo");
       columnName.put(ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NO);
       columnName.put(ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_NAME);
       columnName.put(ConstantsUtils.PRICE, "psDetailsPrice");
       columnName.put(ConstantsUtils.PRICE_TYPE, "psDetailsPricetype");
       columnName.put("contractPrice", "psDetailsContractPrice");
       columnName.put(ConstantsUtils.CONTRACT_PRICE_START_DATE, "psDtlsContPriceStartdate");
       columnName.put(ConstantsUtils.CONTRACT_PRICE_END_DATE, "psDtlsContPriceEnddate");
       columnName.put(ConstantsUtils.PRICE_TOLERANCE, "psDetailsPriceTolerance");
       columnName.put(ConstantsUtils.PRICE_PROTECTION_START_DATE, "psDetailsPricPrtcnStdate");
       columnName.put(ConstantsUtils.PRICE_PROTECTION_END_DATE, "psDetailsPricPrtcnEddate");
       columnName.put("priceToleranceType", "psDtlsPriceToleranceType");
       columnName.put("priceToleranceInterval", "psDtlsPriceToleranceIntrvl");
       columnName.put("priceToleranceFrequency", "psDtlsPriceToleranceFreq");
       columnName.put("basePrice", "psDetailsBasePrice");
       columnName.put("revisionDate", "psDetailsRevisionDate");
       columnName.put(ConstantsUtils.ATTACHED_DATE_PROPERTY, "psDetailsAttachedDate");
       columnName.put(ConstantsUtils.ITEM_ID1, "itemId");  
       columnName.put("itemStatus", "status");  
       columnName.put(ConstantsUtils.PRICE_PROTECTION_STATUS, ConstantsUtils.PRICE_PROTECTION_STATUS);  
       columnName.put(ConstantsUtils.PRICE_PROTECTION_PRICE_TYPE, ConstantsUtils.PRICE_PROTECTION_PRICE_TYPE);  
       columnName.put("nep", "nep");  
       columnName.put(ConstantsUtils.MAC_INC_CHANGE, ConstantsUtils.MAC_INC_CHANGE);        
       columnName.put(ConstantsUtils.RESET_TYPE, ConstantsUtils.RESET_TYPE);     
       columnName.put(ConstantsUtils.RESET_DATE, ConstantsUtils.RESET_DATE);     
       columnName.put(ConstantsUtils.RESET_INTERVAL, ConstantsUtils.RESET_INTERVAL);     
       columnName.put(ConstantsUtils.RESET_FREQUENCY, ConstantsUtils.RESET_FREQUENCY);     
       columnName.put(ConstantsUtils.NET_PRICE_TYPE, ConstantsUtils.NET_PRICE_TYPE);    
       columnName.put("createdBy", "CREATED_BY");     
       columnName.put("modifiedBy", "MODIFIED_BY");   
       columnName.put("createdUserName", "PS_DETAILS_CREATED_BY");     
       return columnName;
   } 
   
    /**
     * Returns true if the object is null or string object is empty.
     *
     * @param object
     * @return
     */
    public static boolean checkNullValues(Object object) {
        boolean isNullOrEmpty;
        if (object instanceof String) {
            String checkString = (String) object;
            isNullOrEmpty = StringUtils.isBlank(checkString) || ConstantsUtils.NULL.equals(checkString);
        } else {
            isNullOrEmpty = object == null;
        }
        return isNullOrEmpty;
    }
   }
