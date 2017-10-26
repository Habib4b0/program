package com.stpl.app.global.rebateschedule.util;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class for declaring table column header names .
 */
public final class RsUtils {

    /**
     * Private constructor for utility class.
     */
    private RsUtils(){
        
    }
    
    final static Map<String, String> columnMap = new ConcurrentHashMap<>();
    final static Map<String, String> viewColumnMap = new ConcurrentHashMap<>();
    final static Map<String, String> rebatePlanLookupColumnMap = new ConcurrentHashMap<>();
    
    public static final String REBATE_PLAN_NO = "rebatePlanNo";
    public static final String PERCENCTAGE = "%";
    
    public final static String CALC_FORMULA = "Formula";
    public final static String CALC_REBATE_PLAN = "Rebate Plan";
    public final static String CALC_DEDUCTION_CALENDAR = "Deduction Calendar";
    public final static String NET_SALES_FORMULA = "Net Sales Formula";
    public final static String NET_SALES_RULE = "Net Sales Rule";
    public final static String EVALUATION_RULE = "Evaluation Rule";
    public final static String CALCULATION_RULE = "Calculation Rule";
    public final static String EVALUATION_RULE_BUNDLE = "Evaluation Rule Bundle";
    public final static String CALCULATION_RULE_BUNDLE = "Calculation Rule Bundle";
    final static Map<String, String> netSalesLookupColumnMap = new ConcurrentHashMap<>();
    
    /** The Constant REBATE_PLAN_TYPE. */
    public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";
    
    /**
     * The Constant PARENT_COL_HEADERS.
     */
    public static final String[] PARENT_COL_HEADERS = new String[]{
        "Rebate Schedule ID", "Rebate Schedule No",
        "Rebate Schedule Name",
        "Rebate Schedule Status", "Rebate Schedule Type",
        "Rebate Program Type"};
    /**
     * The Constant PARENT_COL_COLUMNS.
     */
    public static final Object[] PARENT_COL_COLUMNS = new Object[]{
        "rebateScheduleId", "rebateScheduleNo", "rebateScheduleName",
        "rebateScheduleStatus", "rebateScheduleType",
        "rebateProgramType"};
    /** The Constant rebateScheduleId. */
    public static final String REBATE_AMOUNT= "Rebate Amount";
    public static final String REBATE_PLAN_NAME= "Rebate Plan Name";
       
    static HashMap<String, String> rsColumnName=new HashMap<String, String>();
    static HashMap<String, String> rsRebateSetupColumnName=new HashMap<String, String>();
    
    public final static String DEDUCTION_CALENDAR_NAME = "Deduction Calendar Name";
    
    
    public static String getRsDBColumnName(String visibleColumnName) {
         return rsColumnName.get(visibleColumnName);  
       
    } 
    public static String getRsRebateDBColumnName(String visibleColumnName) {
         return rsRebateSetupColumnName.get(visibleColumnName);  
       
    } 
       
    public static ComboBox getComboBox(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(NumericConstants.SEVEN);

        select.setInputPrompt(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        return select;
    }
    
    
     public static Container searchFields() {
        final List<String> list = new ArrayList<String>();
      list.add(ConstantsUtils.SELECT_ONE);
      list.add("IFP ID");
      list.add("IFP No");
      list.add("IFP Name");
      list.add("IFP Status");
      list.add("IFP Type");      
      list.add("IFP Start Date");
      list.add("IFP End Date");

      list.add("IFP Category");
      return new IndexedContainer(list);
    }
   public static HashMap<String, String> loadRsColumnName() {       
       rsColumnName.put("rebateScheduleId", "rsId");
       rsColumnName.put("rebateScheduleNo", "rsNo");
       rsColumnName.put("rebateScheduleName", "rsName");
       rsColumnName.put("rebateScheduleStatus", "rsStatus");
       rsColumnName.put("rebateScheduleType", "rsType");
       rsColumnName.put("rebateProgramType", "rebateProgramType");
       return rsColumnName;
   }    
   
   public static HashMap<String, String> loadRsRebateSetupColumnName() {
       rsRebateSetupColumnName.put("itemNo", "itemNo");
       rsRebateSetupColumnName.put("itemName", "itemName");
       rsRebateSetupColumnName.put("formulaId", "rsDetailsFormulaId");
       rsRebateSetupColumnName.put("formulaName", "rsDetailsFormulaName");
       rsRebateSetupColumnName.put("rebateAmount", "rsDetailsRebateAmount");
       rsRebateSetupColumnName.put("startDate", "itemRebateStartDate");
       rsRebateSetupColumnName.put("endDate", "itemRebateEndDate");
       rsRebateSetupColumnName.put("attachedStatus", "rsDetailsAttachedStatus");
       rsRebateSetupColumnName.put("formulaType", "rsDetailsFormulaType");
       rsRebateSetupColumnName.put("formulaMethodId", "rsDetailsFormulaMethodId");
       rsRebateSetupColumnName.put("formulaNo", "rsDetailsFormulaNo");
       rsRebateSetupColumnName.put("rebatePlanName", "rsDetailsRebatePlanName");
       rsRebateSetupColumnName.put("attachedDate", "rsDetailsAttachedDate");
       rsRebateSetupColumnName.put("revisionDate", "modifiedDate");
       return rsRebateSetupColumnName;
   }  
    
    public static Map<String, String> loadColumnName() {
        if (columnMap.isEmpty()) {
            columnMap.put("checkbox", "CHECK_RECORD");
            columnMap.put("itemNo", "ITEM_NO");
            columnMap.put("itemName", "ITEM_NAME");
            columnMap.put("formulaId", "RS_DETAILS_FORMULA_ID");            
            columnMap.put("rebateAmount", "RS_DETAILS_REBATE_AMOUNT");
            columnMap.put("startDate", "ITEM_REBATE_START_DATE");
            columnMap.put("endDate", "ITEM_REBATE_END_DATE");
            columnMap.put("attachedStatus", "status");
            columnMap.put("formulaType", "RS_DETAILS_FORMULA_TYPE");
            columnMap.put("formulaMethodId", "RS_DETAILS_FORMULA_METHOD_ID");            
            columnMap.put("attachedDate", "RS_DETAILS_ATTACHED_DATE");
            columnMap.put("revisionDate", "RS_DETAILS_MODIFIED_DATE");
            columnMap.put("rebatePlanName", "rpName");
            columnMap.put("rebatePlanNo","REBATE_PLAN_NO");
            columnMap.put("formulaName","FORMULA_NAME");
            columnMap.put("formulaNo", "FORMULA_NO");
            columnMap.put("bundleNo","BUNDLE_NO");
            columnMap.put("deductionCalendarName","DEDUCTION_CALENDAR_NAME");
            columnMap.put("deductionCalendarNo","DEDUCTION_CALENDAR_NO");
        }
        return columnMap;

    }
    
    public static Map<String, String> loadViewColumnName() {
        if (viewColumnMap.isEmpty()) {
            viewColumnMap.put("itemNo", "ITEM_NO");
            viewColumnMap.put("itemName", "ITEM_NAME");
            viewColumnMap.put("formulaId", "FORMULA_ID");
            viewColumnMap.put("formulaName", "FORMULA_NAME");
            viewColumnMap.put("rebateAmount", "REBATE_AMOUNT");
            viewColumnMap.put("startDate", "ITEM_REBATE_START_DATE");
            viewColumnMap.put("endDate", "ITEM_REBATE_END_DATE");
            viewColumnMap.put("attachedStatusValue", "status");
            viewColumnMap.put("formulaType", "FORMULA_TYPE");
            viewColumnMap.put("formulaMethodId", "FORMULA_METHOD_ID");
            viewColumnMap.put("formulaNo", "FORMULA_NO");
            viewColumnMap.put("attachedDate", "ITEM_RS_ATTACHED_DATE");
            viewColumnMap.put("revisionDate", "MODIFIED_DATE");
            viewColumnMap.put("rebatePlanName", "rpName");
        }
        return viewColumnMap;

    }
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] REBATE_SETUP_DEFAULT = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatus","startDate", "endDate"};
    
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public static final String[] REBATE_SETUP_DEFAULT_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status","RS Start Date", "RS End Date"};    
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] REBATE_SETUP_FORMULA = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatus","startDate", "endDate","formulaType","formulaNo", "formulaName", "netSalesFormulaNo","netSalesRule","evaluationRule","evaluationRuleBundle","calculationRule","calculationRuleBundle", "attachedDate"};
   
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public static final String[] REBATE_SETUP_FORMULA_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status","RS Start Date", "RS End Date","Formula Type","Formula No", "Formula Name", "Net Sales Formula","Net Sales Rule","Evaluation Rule","Evaluation Rule Bundle","Calculation Rule","Calculation Rule Bundle", "Attached Date"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] REBATE_SETUP_DEDUCTION_CALENDAR = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatus","startDate", "endDate","deductionCalendarNo","deductionCalendarName","evaluationRule","evaluationRuleBundle","calculationRule","calculationRuleBundle","attachedDate"};
    
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public static final String[] REBATE_SETUP_DEDUCTION_CALENDAR_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status","RS Start Date", "RS End Date","Deduction Calendar No","Deduction Calendar Name","Evaluation Rule","Evaluation Rule Bundle","Calculation Rule","Calculation Rule Bundle","Attached Date"};
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] REBATE_SETUP_REBATE_PLAN = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatus","startDate", "endDate","bundleNo","rebatePlanNo","rebatePlanName", "netSalesFormulaNo","netSalesRule","evaluationRule","evaluationRuleBundle","calculationRule","calculationRuleBundle", "attachedDate"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final String[] REBATE_SETUP_REBATE_PLAN_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status","RS Start Date", "RS End Date","Bundle No","Rebate Plan No","Rebate Plan Name", "Net Sales Formula", "Attached Date"};  
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] REBATE_PLAN_LOOKUP = new Object[]{
        "rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus","rebatePlanType","rebateStructure","rangeBasedOn","netSalesFormula" ,"netSalesRule","rebateBasedOn","createdDate","createdBy","modifiedDate","modifiedBy"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final String[] REBATE_PLAN_LOOKUP_HEADER = new String[]{
        "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name", "Rebate Plan Status","Rebate Plan Type" ,"Rebate Structure","Range Based On","Net Sales Formula" ,"Net Sales Rule","Rebate Based On","Created Date","Created By","Modified Date","Modified By"}; 
      
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] FORMULA_LOOKUP = new Object[]{
        "formulaType", "formulaID", "formulaNo", "formulaName", "version"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final String[] FORMULA_LOOKUP_HEADER = new String[]{
        "Formula Type", "Formula ID", "Formula No", "Formula Name", "Version"}; 
    
      /** The Constant DEDUCTION_LOOKUP_COLUMN. */
    public static final Object[] DEDUCTION_LOOKUP_COLUMN = new Object[]{
        "deductionNo", "deductionName", "deductionDesc","deductionCategory","creationDate","createdBy","modifiedDate","modifiedBy"}; 
    
    /** The Constant DEDUCTION_LOOKUP_HEADER. */
    public static final String[] DEDUCTION_LOOKUP_HEADER = new String[]{
        "Deduction Calendar No", "Deduction Calendar Name", "Deduction Calendar Desc","Category","Creation Date","Created By","Modified Date","Modified By"}; 

    public static String getColumnName(String key){
        
        return columnMap.get(key);
    }
    
    public static String getViewColumnName(String key){
        
        return viewColumnMap.get(key);
    }
       
    public static void loadRebatePlanLookupColumnMap() {
        rebatePlanLookupColumnMap.put("rebatePlanId", "RP.REBATE_PLAN_ID");
        rebatePlanLookupColumnMap.put("rebatePlanNo", "RP.REBATE_PLAN_NO");
        rebatePlanLookupColumnMap.put("rebatePlanName", "RP.REBATE_PLAN_NAME");
        rebatePlanLookupColumnMap.put("rebatePlanStatus", "RP.REBATE_PLAN_STATUS");
        rebatePlanLookupColumnMap.put("rebatePlanType", "RP.REBATE_PLAN_TYPE");
        rebatePlanLookupColumnMap.put("rebateStructure", "RP.REBATE_STRUCTURE");
        rebatePlanLookupColumnMap.put("rebateBasedOn", "RP.REBATE_BASED_ON");
        rebatePlanLookupColumnMap.put("rangeBasedOn", "RP.REBATE_RANGE_BASED_ON");
        rebatePlanLookupColumnMap.put("createdBy", " RP.CREATED_BY");
        rebatePlanLookupColumnMap.put("createdDate", "RP.CREATED_DATE");
        rebatePlanLookupColumnMap.put("modifiedBy", "RP.MODIFIED_BY");
        rebatePlanLookupColumnMap.put("modifiedDate", " RP.MODIFIED_DATE");
        rebatePlanLookupColumnMap.put("netSalesFormula", "NSF.NET_SALES_FORMULA_NAME");
        rebatePlanLookupColumnMap.put("netSalesRule", "CDR.RULE_NAME");
 
    }

    public static String getRebatePlanLookupColumnMap(String key) {
        return rebatePlanLookupColumnMap.get(key);
    }
    
     /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final Object[] NET_SALES_LOOKUP = new Object[]{
        "netSalesFormulaType", "netSalesFormulaId", "netSalesFormulaNo", "netSalesFormulaName","nsfcreatedDate", "nsfcreatedBy", "nsfmodifiedDate", "nsfmodifiedBy"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public static final String[] NET_SALES_LOOKUP_HEADER = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name","Creation Date", "Created By", "Modified Date", "Modified By" }; 
    
}
