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
    public static final String[] PS_COL_HEADERS = new String[]{"System ID",
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name",
        "Price Schedule Type","Price Schedule Status", 
        "Price Schedule Category", "Start Date",
        "End Date", "Price Schedule Designation","Parent ID","Parent Name",
        "Trade Class"};


    /**
     * The Constant PS_SEARCH_TABLE.
     */
    public static final Object[] PS_SEARCH_TABLE = new Object[]{"psSystemId",
        "priceScheduleId", "priceScheduleNo", "priceScheduleName",
        "priceScheduleType","priceScheduleStatus", 
        "priceScheduleCategory", "priceScheduleStartDate",
        "priceScheduleEndDate", "priceScheduleDesignation","parentId","parentName", "tradeClass"};
    /**
     * The Constant PARENT_COL_HEADERS.
     */
    public static final String[] PARENT_COL_HEADERS = new String[]{
        "PS ID", "PS No", "PS Name",
        "PS Status", "PS Type"};
    /**
     * The Constant PARENT_COL_OBJECT.
     */
    public static final Object[] PARENT_COL_OBJECT = new Object[]{
        "priceScheduleId", "priceScheduleNo", "priceScheduleName",
        "priceScheduleStatus", "priceScheduleType"};
    /**
     * The Constant ITEM_DETAILS_COL.
     */
    public static final Object[] ITEM_DETAILS_COL = new Object[]{"checkRecord",
        "itemID", "itemNo", "itemName", "brand", "itemStatus",
        "contractPriceStartDate", "contractPriceEndDate",
        "priceType", "price", "suggestedPrice","source","createdUserName","createdDate",
        "attachedDate"
    };
    /**
     * The Constant ITEM_DETAILS_COL.
     */
    public static final Object[] ITEM_DETAILS_COL_VIEW = new Object[]{
        "itemFamilyplanNo", "itemNo", "itemName", "price", "priceType",
        "contractPrice", "contractPriceStartDate", "contractPriceEndDate",
        "priceTolerance", "priceProtectionStartDate",
        "priceProtectionEndDate", "priceToleranceTypeValue",
        "priceToleranceIntervalValue", "priceToleranceFrequencyValue", "basePrice",
        "revisionDate", "attachedDate"};
    
    
     /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public static final Object[] PRICE_PROTECTION_COL = new Object[]{"checkRecord",
        "itemID", "itemNo", "itemName", "brand", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType",
        "nep", "nepFormula","basePriceType","basePriceValue",
        "netBasePrice","netBasePriceFormulaName","subsequentPeriodPriceType","netSubsequentPeriodPrice","netSubsequentPriceFormulaName",        
        "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType",
        "priceTolerance","maxIncrementalChange","resetEligible","resetType",
        "resetDate", "resetInterval", "resetFrequency","resetPriceType","netResetPriceType","netResetPriceFormulaName",
        "netPriceType","netPriceTypeFormulaName",
        "attachedDate"};
    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public static final String[] PRICE_PROTECTION_COL_HEADER = new String[]{
        "", "Item ID", "Item No", "Item Name", "Brand", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type" ,"Base Price","Net Base Price",
        "Net Base Price Formula","Subsequent Period Price Type","Net Subsequent Period Price","Net Subsequent Period Price Formula",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", 
        "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type","Net Reset Price Type","Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula",
        "Attached Date"};
    
    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public static final String[] ITEM_DETAILS_COL_HEADER = new String[]{
        "", "Item ID", "Item No", "Item Name", "Brand","Status",
        "CP Start Date", "CP End Date",
         "Price Type","Price",
         "Suggested Price","Source","Created By","Created Date",
        "Attached Date"};
    
    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public static final String[] ITEM_DETAILS_COL_HEADER_VIEW = new String[]{
        "IFP No", "Item No", "Item Name", "Price", "Price Type",
        "Contract Price", "Contract Price Start Date", "Contract Price End Date",
        "Price Tolerance", "Price Protection Start Date",
        "Price Protection End Date", "Price Tolerance Type",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Base Price",
        "Revision Date", "Attached Date"};   
    
    /**
     * The Constant AVAILABLE_IFP_COL.
     */
    public static final Object[] AVAILABLE_IFP_COL = new Object[]{
        "itemFamilyPlanId","itemFamilyplanNo", "itemFamilyplanName","itemFamilyPlanStatus","ifpStartDate","ifpEndDate","ifpType","ifpCategory"};
     /**
     * The Constant AVAILABLE_IFP_COL.
     */
    public static final Object[] AVAILABLE_IFP_COL_VIEW = new Object[]{
        "itemFamilyPlanId","itemFamilyplanNo", "itemFamilyplanName","itemFamilyPlanStatus","ifpStartDate","ifpEndDate","ifpType","ifpCategory"};
    
    /**
     * The Constant AVAILABLE_IFP_COL_HEADER.
     */
    public static final String[] AVAILABLE_IFP_COL_HEADER = new String[]{
        "IFP ID","IFP No", "IFP Name","IFP Status","Start Date","End Date","IFP Type","IFP Category"};
    /** The Constant NEP FORMULA LOOKUP COLUMNS IN PS. */
    public static final Object[] NEP_FORMULA_LOOKUP = new Object[]{
        "nepFormulaType","nepFormulaID", "nepFormulaNo", "nepFormulaName","createdBy","createdDate","modifiedBy","modifiedDate"};
    
    /** The Constant NEP FORMULA LOOKUP HEADER IN PS. */ 
    public static final String[] NEP_FORMULA_LOOKUP_HEADER = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name","Created By","Created Date","Modified By","Modified Date"}; 

    /**
     * To Add Fields to Container.
     *
     * @return Container
     */
    public Container searchFields() {
        final List<String> list = new ArrayList<String>();
      list.add(ConstantsUtils.SELECT_ONE);
      list.add("IFP Name");
      list.add("IFP No");
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
       
       columnName.put("checkRecord", "checkRecord");
       columnName.put("priceScheduleId", "psId");
       columnName.put("priceScheduleNo", "psNo");
       columnName.put("priceScheduleName", "psName");
       columnName.put("priceScheduleStatus", "psStatus");
       columnName.put("priceScheduleType", "psType");
       columnName.put("priceScheduleCategory", "psCategory");
       columnName.put("priceScheduleStartDate", "psStartDate");
       columnName.put("priceScheduleEndDate", "psEndDate");
       columnName.put("priceScheduleDesignation", "psDesignation");
       columnName.put("tradeClass", "tradeClass");
       
       return columnName;
   } 
    
   public static HashMap<String, String> loadAvailableColumnName() {
       columnName.clear();
   
       columnName.put("itemFamilyplanNo", "ifpNo");
       columnName.put("itemFamilyplanName", "ifpName");
       columnName.put("itemFamilyPlanId", "ifpId");
       columnName.put("itemFamilyPlanStatus", "ifpStatus");
      
       
       return columnName;
   } 
   public static HashMap<String, String> loadItemDetailsColumnName() {
       columnName.clear();
       columnName.put("checkRecord", "checkRecord");
       columnName.put("itemFamilyplanNo", "ifpNo");
       columnName.put("itemNo", "itemNo");
       columnName.put("itemName", "itemName");
       columnName.put("price", "psDetailsPrice");
       columnName.put("priceType", "psDetailsPricetype");
       columnName.put("contractPrice", "psDetailsContractPrice");
       columnName.put("contractPriceStartDate", "psDtlsContPriceStartdate");
       columnName.put("contractPriceEndDate", "psDtlsContPriceEnddate");
       columnName.put("priceTolerance", "psDetailsPriceTolerance");
       columnName.put("priceProtectionStartDate", "psDetailsPricPrtcnStdate");
       columnName.put("priceProtectionEndDate", "psDetailsPricPrtcnEddate");
       columnName.put("priceToleranceType", "psDtlsPriceToleranceType");
       columnName.put("priceToleranceInterval", "psDtlsPriceToleranceIntrvl");
       columnName.put("priceToleranceFrequency", "psDtlsPriceToleranceFreq");
       columnName.put("basePrice", "psDetailsBasePrice");
       columnName.put("revisionDate", "psDetailsRevisionDate");
       columnName.put("attachedDate", "psDetailsAttachedDate");
       columnName.put("itemID", "itemId");  
       columnName.put("itemStatus", "status");  
       columnName.put("priceProtectionStatus", "priceProtectionStatus");  
       columnName.put("priceProtectionPriceType", "priceProtectionPriceType");  
       columnName.put("nep", "nep");  
       columnName.put("maxIncrementalChange", "maxIncrementalChange");        
       columnName.put("resetType", "resetType");     
       columnName.put("resetDate", "resetDate");     
       columnName.put("resetInterval", "resetInterval");     
       columnName.put("resetFrequency", "resetFrequency");     
       columnName.put("netPriceType", "netPriceType");    
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
