/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.util;

/**
 *
 * @author Karthik.Raja
 */
public class Constants {

    public static String LONG_RUNNING_QUERIES = "LONG_RUNNING_QUERIES";
    public static String BOOTSTRAP = "bootstrap";
    public static String STORED_PROCEDURE_INFO = "STORED_PROCEDURE_INFO";
    public static String INDEX_FRAGMENTATION_STATISTICS = "INDEX_FRAGMENTATION_STATISTICS";
    public static String ERROR = "Error";
    public static String SelectDateMSG = "Please Select any Date";
    public static String EMPTY = "";
    public static String Query = "Query";
    public static String Procedure = "Procedure";
    public static String Continous_Dots = "...";
    public static String SPACE = " ";
    public static String UNDERSCORE = "_";
    public static String Long_Running_Queries = "LONG RUNNING QUERIES";
    public static String Stored_Procedure_Info = "STORED PROCEDURE INFO";
    public static String Index_Fragmentation_Statistics = "INDEX FRAGMENTATION STATISTICS";
    public static String CPU = "CPU";
    public static String IO = "IO";
    public static String LONG = "LONG";
    public static String Date_Format = "dd-MM-yyyy";
    public static String Hour_Date_Format = "dd/MM/yyyy HH:mm:ss";
    public static String Time_Format = "HH:mm:ss a";
    public static String ENABLED = "Enabled";
    public static String OCCURS_ONCE_AT = "Occurs Once At";
    public static String OCCURS_EVERY = "Occurs Every";
    public static String END_DATE = "End Date";
    public static String NO_END_DATE = "No End Date";
    public static String Query_Statistics = "Query Statistics";
    public static String CPU_IO_Statistics = "CPU IO Statistics";
    public static String Schedule_Job = "Scheduled Jobs";
    public static String YES = "Yes";
    public static String NO = "No";
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String SELECT_TIME = "Select Time";
    public static String DISABLED = "Disabled";
    public static String ALL = "All";
    public static String STAR = "\\*";
    public static String PERCENT = "\\%";
    public static String CPU_IO_STATISTICS = "CPU IO Statistics";
    public static String SECS = " Secs";
    public static String MB = " MB";
    public static String SELECT_ONE = "Select One -";
    public static String PERCENTAGE_WITH_SPACE = " %";
    public static String PERCENTAGE = "%";
    public static String JOB = "Job";
    public static String SUCCESS = "SUCCESS";
    public static String SHOW_ALL = "Show All";
    public static String STATUS = "status";
    public static String FAIL = "FAIL";
    public static String FALSE = "false";
    public static String TRUE = "true";
    public static String NULL_STRING = "null";
    public final static String DOUBLE_QUOTE = "\"\"";
    public final static String QUOTE = "\"";
    public final static String EXCEL_STRING_FORMAT = "\" %s \",";
    public static String Server_Logging = "Server Logging";
    public static String INBOUND = "Inbound";
    public static String OUTBOUND = "Outbound";
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    public static final Object[] QUERY_TABLE_COLUMNS = new Object[]{
        "dataBase_Name", "text_Query", "value"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    public static final String[] QUERY_TABLE_HEADER = new String[]{"Database", "Query", "Value"};
    public static final String[] interFacesList = new String[]{"ACCRUAL_INBOUND_INTERFACE", "COMPANY_TRADE_CLASS_INTERFACE",
        "GL_COST_CENTER_INTERFACE", "ITEM_MASTER_INTERFACE", "ACTUAL_MASTER_INTERFACE",
        "CONTRACT_HEADER_INTERFACE", "GL_POSTING_INTERFACE", "ITEM_PRICING_INTERFACE",
        "ADJUSTED_DEMAND_ACTUAL_INTERFACE", "CPI_INTERFACE",
        "INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE", "LOT_MASTER_INTERFACE",
        "ADJUSTED_DEMAND_FORECAST_INTERFACE", "CUSTOMER_GTS_ACTUAL_INTERFACE",
        "INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE", "MASTER_DATA_ATTRIBUTE_INTERFACE",
        "AVERAGE_SHELF_LIFE_INTERFACE", "CUSTOMER_GTS_FORECAST_INTERFACE",
        "INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE",
        "PRICE_SCHEDULE_INTERFACE", "BEST_PRICE_INTERFACE", "DEMAND_ACTUAL_INTERFACE",
        "INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE", "REBATE_PLAN_INTERFACE",
        "COMPANY_FAMILY_PLAN_INTERFACE", "DEMAND_FORECAST_INTERFACE",
        "ITEM_FAMILY_PLAN_INTERFACE", "REBATE_SCHEDULE_INTERFACE", "COMPANY_IDENTIFIER_INTERFACE",
        "FORECAST_SALES_INTERFACE", "ITEM_HIERARCHY_DEFINATION_INTERFACE",
        "RETURN_RESERVE_INTERFACE", "COMPANY_MASTER_INTERFACE", "FORMULA_DETAILS_INTERFACE",
        "ITEM_HIERARCHY_INTERFACE", "RETURNS_INTERFACE", "COMPANY_PARENT_INTERFACE",
        "GL_BALANCE_INTERFACE", "ITEM_IDENTIFIER_INTERFACE", "SALES_MASTER_INTERFACE"};
    
    public static String[] outBoundInterfaceList=new String[]{
            "Accrual Rate Projection" , "ADJUSTMENT_DETAILS_GTN" , "ADJUSTMENT_DETAILS_RESERVE" , "Cff_Outbound",  "Hierarchy_Definition",    "Relationship_Builder"};
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    public static final Object[] PROCEDURE_TABLE_COLUMNS = new Object[]{"dataBase_Name", "procedure_Name",
        "text_Query", "value"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    public static final String[] PROCEDURE_TABLE_HEADER = new String[]{"Database", "Procedure Name", "Query", "Value"};
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    public static final Object[] INDEX_TABLE_COLUMNS = new Object[]{
        "index_Name", "table_Name", "fragmentation_Percentage", "space_Used_Percentage", "total_Rows", "distributed_Rows"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    public static final String[] INDEX_TABLE_HEADER = new String[]{"Index Name", "Table Name", "Fragmentation Percentage",
        "Space Used Percentage", "Total Rows", "Distributed Rows"};
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    public static final Object[] JOB_TABLE_COLUMNS = new Object[]{
        "jobname", "enabled", "description", "occurs", "occurs_detail", "frequency", "execution_Time", "last_Execution_Day", "status"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    public static final String[] JOB_TABLE_HEADER = new String[]{"Name", "Enabled", "Description",
        "Occurs", "Occurs detail", "Frequency", "Last Execution Duration", "Last Execution Date", "Last Execution Status"};
}
