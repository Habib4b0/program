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
    
    private static RsUtils object;

    public static RsUtils getInstance() {
        if (object == null) {
            object = new RsUtils();
        }
        return object;
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
    public final static String ATTACHED_DATE_LABEL = "Attached Date";
    final static Map<String, String> netSalesLookupColumnMap = new ConcurrentHashMap<>();
    
    /** The Constant REBATE_PLAN_TYPE. */
    public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";
    
    /**
     * The Constant PARENT_COL_HEADERS.
     */
    public final String[] parentColHeader = new String[]{
        "Rebate Schedule ID", "Rebate Schedule No",
        "Rebate Schedule Name",
        "Rebate Schedule Status", "Rebate Schedule Type",
        "Rebate Program Type"};
    /**
     * The Constant PARENT_COL_COLUMNS.
     */
    public final Object[] parentColColumns = new Object[]{
        "rebateScheduleId", "rebateScheduleNo", "rebateScheduleName",
        "rebateScheduleStatus", "rebateScheduleType",
        ConstantsUtils.REBATE_PROGRAM_TYPE};
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
        final List<String> list = new ArrayList<>();
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
       rsColumnName.put(ConstantsUtils.REBATE_PROGRAM_TYPE, ConstantsUtils.REBATE_PROGRAM_TYPE);
       return rsColumnName;
   }    
   
   public static HashMap<String, String> loadRsRebateSetupColumnName() {
       rsRebateSetupColumnName.put(ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NO);
       rsRebateSetupColumnName.put(ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_NAME);
       rsRebateSetupColumnName.put(ConstantsUtils.FORMULA_ID, "rsDetailsFormulaId");
       rsRebateSetupColumnName.put(ConstantsUtils.FORMULA_NAME, "rsDetailsFormulaName");
       rsRebateSetupColumnName.put(ConstantsUtils.REBATE_AMOUNT, "rsDetailsRebateAmount");
       rsRebateSetupColumnName.put(ConstantsUtils.START_DATE, "itemRebateStartDate");
       rsRebateSetupColumnName.put(ConstantsUtils.END_DATE, "itemRebateEndDate");
       rsRebateSetupColumnName.put(ConstantsUtils.ATTACHED_STATUS, "rsDetailsAttachedStatus");
       rsRebateSetupColumnName.put(ConstantsUtils.FORMULA_TYPE, "rsDetailsFormulaType");
       rsRebateSetupColumnName.put(ConstantsUtils.FORMULA_METHD_ID, "rsDetailsFormulaMethodId");
       rsRebateSetupColumnName.put(ConstantsUtils.FORMULA_NO, "rsDetailsFormulaNo");
       rsRebateSetupColumnName.put(ConstantsUtils.REBATE_PLAN_NAME, "rsDetailsRebatePlanName");
       rsRebateSetupColumnName.put(ConstantsUtils.ATTACHED_DATE_PROPERTY, "rsDetailsAttachedDate");
       rsRebateSetupColumnName.put(ConstantsUtils.REVISION_DATE, ConstantsUtils.MODIFIEDDATE);
       return rsRebateSetupColumnName;
   }  
    
    public static Map<String, String> loadColumnName() {
        if (columnMap.isEmpty()) {
            columnMap.put(ConstantsUtils.CHECK_BOX, "CHECK_RECORD");
            columnMap.put(ConstantsUtils.ITEM_NO, "ITEM_NO");
            columnMap.put(ConstantsUtils.ITEM_NAME, "ITEM_NAME");
            columnMap.put(ConstantsUtils.FORMULA_ID, "RS_DETAILS_FORMULA_ID");            
            columnMap.put(ConstantsUtils.REBATE_AMOUNT, "RS_DETAILS_REBATE_AMOUNT");
            columnMap.put(ConstantsUtils.START_DATE, "ITEM_REBATE_START_DATE");
            columnMap.put(ConstantsUtils.END_DATE, "ITEM_REBATE_END_DATE");
            columnMap.put(ConstantsUtils.ATTACHED_STATUS, "status");
            columnMap.put(ConstantsUtils.FORMULA_TYPE, "RS_DETAILS_FORMULA_TYPE");
            columnMap.put(ConstantsUtils.FORMULA_METHD_ID, "RS_DETAILS_FORMULA_METHOD_ID");            
            columnMap.put(ConstantsUtils.ATTACHED_DATE_PROPERTY, "RS_DETAILS_ATTACHED_DATE");
            columnMap.put(ConstantsUtils.REVISION_DATE, "RS_DETAILS_MODIFIED_DATE");
            columnMap.put(ConstantsUtils.REBATE_PLAN_NAME, "rpName");
            columnMap.put(ConstantsUtils.REBATE_PLAN_NO,"REBATE_PLAN_NO");
            columnMap.put(ConstantsUtils.FORMULA_NAME,"FORMULA_NAME");
            columnMap.put(ConstantsUtils.FORMULA_NO, "FORMULA_NO");
            columnMap.put("bundleNo","BUNDLE_NO");
            columnMap.put("deductionCalendarName","DEDUCTION_CALENDAR_NAME");
            columnMap.put("deductionCalendarNo","DEDUCTION_CALENDAR_NO");
        }
        return columnMap;

    }
    
    public static Map<String, String> loadViewColumnName() {
        if (viewColumnMap.isEmpty()) {
            viewColumnMap.put(ConstantsUtils.ITEM_NO, "ITEM_NO");
            viewColumnMap.put(ConstantsUtils.ITEM_NAME, "ITEM_NAME");
            viewColumnMap.put(ConstantsUtils.FORMULA_ID, "FORMULA_ID");
            viewColumnMap.put(ConstantsUtils.FORMULA_NAME, "FORMULA_NAME");
            viewColumnMap.put(ConstantsUtils.REBATE_AMOUNT, "REBATE_AMOUNT");
            viewColumnMap.put(ConstantsUtils.START_DATE, "ITEM_REBATE_START_DATE");
            viewColumnMap.put(ConstantsUtils.END_DATE, "ITEM_REBATE_END_DATE");
            viewColumnMap.put("attachedStatusValue", "status");
            viewColumnMap.put(ConstantsUtils.FORMULA_TYPE, "FORMULA_TYPE");
            viewColumnMap.put(ConstantsUtils.FORMULA_METHD_ID, "FORMULA_METHOD_ID");
            viewColumnMap.put(ConstantsUtils.FORMULA_NO, "FORMULA_NO");
            viewColumnMap.put(ConstantsUtils.ATTACHED_DATE_PROPERTY, "ITEM_RS_ATTACHED_DATE");
            viewColumnMap.put(ConstantsUtils.REVISION_DATE, "MODIFIED_DATE");
            viewColumnMap.put(ConstantsUtils.REBATE_PLAN_NAME, "rpName");
        }
        return viewColumnMap;

    }
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] rebateSetupDefault = new Object[]{
        ConstantsUtils.CHECK_BOX, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ATTACHED_STATUS,ConstantsUtils.START_DATE, ConstantsUtils.END_DATE};
    
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public final String[] rebateSetupDefaultHeader = new String[]{
        "", ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAMES, ConstantsUtils.RS_STATUS_LABEL,ConstantsUtils.RS_START_DATE_LABEL, ConstantsUtils.RS_END_DATE_LABEL};    
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] rebateSetupFormula = new Object[]{
        ConstantsUtils.CHECK_BOX, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ATTACHED_STATUS,ConstantsUtils.START_DATE, ConstantsUtils.END_DATE,ConstantsUtils.FORMULA_TYPE,
        ConstantsUtils.FORMULA_NO, ConstantsUtils.FORMULA_NAME, ConstantsUtils.NET_SALES_FORMULA_NO,ConstantsUtils.NET_SALES_RULE,ConstantsUtils.EVALUATION_RULE,ConstantsUtils.EVALUATION_RULE_BUNDLE,ConstantsUtils.CALCULATION_RULE,
        ConstantsUtils.CALCULATION_RULE_BUNDLE, ConstantsUtils.ATTACHED_DATE_PROPERTY};
   
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public final String[] rebateSetupFormulaHeader = new String[]{
        "", ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAMES, ConstantsUtils.RS_STATUS_LABEL,ConstantsUtils.RS_START_DATE_LABEL, ConstantsUtils.RS_END_DATE_LABEL,"Formula Type","Formula No", "Formula Name", NET_SALES_FORMULA,NET_SALES_RULE,EVALUATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE,CALCULATION_RULE_BUNDLE, ATTACHED_DATE_LABEL};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] rebateSetupDeductionCalender = new Object[]{
        ConstantsUtils.CHECK_BOX, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ATTACHED_STATUS,ConstantsUtils.START_DATE, ConstantsUtils.END_DATE,"deductionCalendarNo","deductionCalendarName",ConstantsUtils.EVALUATION_RULE,ConstantsUtils.EVALUATION_RULE_BUNDLE,ConstantsUtils.CALCULATION_RULE,ConstantsUtils.CALCULATION_RULE_BUNDLE,ConstantsUtils.ATTACHED_DATE_PROPERTY};
    
  /** The Constant ITEM_DETAILS_HEADER_IN_RS. */
    public final String[] rebateSetupDeductionCalenderHeader = new String[]{
        "", ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAMES, ConstantsUtils.RS_STATUS_LABEL,ConstantsUtils.RS_START_DATE_LABEL, ConstantsUtils.RS_END_DATE_LABEL,"Deduction Calendar No",DEDUCTION_CALENDAR_NAME,EVALUATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE,CALCULATION_RULE_BUNDLE,ATTACHED_DATE_LABEL};
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] rebateSetupRebatePlan = new Object[]{
        ConstantsUtils.CHECK_BOX, ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ATTACHED_STATUS,ConstantsUtils.START_DATE, ConstantsUtils.END_DATE,"bundleNo",ConstantsUtils.REBATE_PLAN_NO,ConstantsUtils.REBATE_PLAN_NAME, ConstantsUtils.NET_SALES_FORMULA_NO,ConstantsUtils.NET_SALES_RULE,ConstantsUtils.EVALUATION_RULE,ConstantsUtils.EVALUATION_RULE_BUNDLE,ConstantsUtils.CALCULATION_RULE,ConstantsUtils.CALCULATION_RULE_BUNDLE, ConstantsUtils.ATTACHED_DATE_PROPERTY};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final String[] rebateSetupRebatePlanHeader = new String[]{
        "", ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAMES, ConstantsUtils.RS_STATUS_LABEL,ConstantsUtils.RS_START_DATE_LABEL, ConstantsUtils.RS_END_DATE_LABEL,"Bundle No","Rebate Plan No",REBATE_PLAN_NAME, NET_SALES_FORMULA, ATTACHED_DATE_LABEL};  
    
      /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] rebatePlanLookup = new Object[]{
        "rebatePlanId", ConstantsUtils.REBATE_PLAN_NO, ConstantsUtils.REBATE_PLAN_NAME, "rebatePlanStatus","rebatePlanType","rebateStructure","rangeBasedOn","netSalesFormula" ,ConstantsUtils.NET_SALES_RULE,"rebateBasedOn","createdDate",ConstantsUtils.CREATEDBY,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final String[] rebatePlanLookupHeader = new String[]{
        "Rebate Plan ID", "Rebate Plan No", REBATE_PLAN_NAME, "Rebate Plan Status","Rebate Plan Type" ,"Rebate Structure","Range Based On",NET_SALES_FORMULA ,NET_SALES_RULE,"Rebate Based On","Created Date",ConstantsUtils.CREATED_BY,ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY}; 
      
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] formulaLookup = new Object[]{
        ConstantsUtils.FORMULA_TYPE, ConstantsUtils.FORMULA_ID, ConstantsUtils.FORMULA_NO, ConstantsUtils.FORMULA_NAME, "version"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final String[] formulaLookupHeader = new String[]{
        "Formula Type", "Formula ID", "Formula No", "Formula Name", "Version"}; 
    
      /** The Constant DEDUCTION_LOOKUP_COLUMN. */
    public final Object[] deductionLookupColumn = new Object[]{
        "deductionNo", "deductionName", "deductionDesc","deductionCategory","creationDate",ConstantsUtils.CREATEDBY,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY}; 
    
    /** The Constant DEDUCTION_LOOKUP_HEADER. */
    public final String[] deductionLookupHeader = new String[]{
        "Deduction Calendar No", DEDUCTION_CALENDAR_NAME, "Deduction Calendar Desc","Category","Creation Date",ConstantsUtils.CREATED_BY,ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY}; 

    public static String getColumnName(String key){
        
        return columnMap.get(key);
    }
    
    public static String getViewColumnName(String key){
        
        return viewColumnMap.get(key);
    }
       
    public static void loadRebatePlanLookupColumnMap() {
        rebatePlanLookupColumnMap.put("rebatePlanId", "RP.REBATE_PLAN_ID");
        rebatePlanLookupColumnMap.put(ConstantsUtils.REBATE_PLAN_NO, "RP.REBATE_PLAN_NO");
        rebatePlanLookupColumnMap.put(ConstantsUtils.REBATE_PLAN_NAME, "RP.REBATE_PLAN_NAME");
        rebatePlanLookupColumnMap.put("rebatePlanStatus", "RP.REBATE_PLAN_STATUS");
        rebatePlanLookupColumnMap.put("rebatePlanType", "RP.REBATE_PLAN_TYPE");
        rebatePlanLookupColumnMap.put("rebateStructure", "RP.REBATE_STRUCTURE");
        rebatePlanLookupColumnMap.put("rebateBasedOn", "RP.REBATE_BASED_ON");
        rebatePlanLookupColumnMap.put("rangeBasedOn", "RP.REBATE_RANGE_BASED_ON");
        rebatePlanLookupColumnMap.put(ConstantsUtils.CREATEDBY, " RP.CREATED_BY");
        rebatePlanLookupColumnMap.put("createdDate", "RP.CREATED_DATE");
        rebatePlanLookupColumnMap.put(ConstantsUtils.MODIFIEDBY, "RP.MODIFIED_BY");
        rebatePlanLookupColumnMap.put(ConstantsUtils.MODIFIEDDATE, " RP.MODIFIED_DATE");
        rebatePlanLookupColumnMap.put("netSalesFormula", "NSF.NET_SALES_FORMULA_NAME");
        rebatePlanLookupColumnMap.put(ConstantsUtils.NET_SALES_RULE, "CDR.RULE_NAME");
 
    }

    public static String getRebatePlanLookupColumnMap(String key) {
        return rebatePlanLookupColumnMap.get(key);
    }
    
     /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final Object[] NET_SALES_LOOKUP = new Object[]{
        "netSalesFormulaType", "netSalesFormulaId", ConstantsUtils.NET_SALES_FORMULA_NO, "netSalesFormulaName","nsfcreatedDate", "nsfcreatedBy", "nsfmodifiedDate", "nsfmodifiedBy"};
    
    /** The Constant ITEM_DETAILS_COLUMNS_IN_RS. */
    public final String[] NET_SALES_LOOKUP_HEADER = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name","Creation Date", ConstantsUtils.CREATED_BY, ConstantsUtils.MODIFIED_DATE, ConstantsUtils.MODIFIED_BY }; 
    
}
