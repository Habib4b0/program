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

    public static final String LONG_RUNNING_QUERIES = "LONG_RUNNING_QUERIES";
    public static final String BOOTSTRAP = "bootstrap";
    public static final String STORED_PROCEDURE_INFO = "STORED_PROCEDURE_INFO";
    public static final String INDEX_FRAGMENTATION_STATISTICS = "INDEX_FRAGMENTATION_STATISTICS";
    public static final String ERROR = "Error";
    public static final String SELECT_DATE_MSG = "Please Select any Date";
    public static final String EMPTY = "";
    public static final String QUERY = "Query";
    public static final String PROCEDURE = "Procedure";
    public static final String CONTINUOUS_DOTS = "...";
    public static final String SPACE = " ";
    public static final String UNDERSCORE = "_";
    public static final String LONG_RUNNING_QUERY = "LONG RUNNING QUERIES";
    public static final String STORED_PROCEDURE_INFOR = "STORED PROCEDURE INFO";
    public static final String INDEX_FRAGMENTATION_STATISTIC = "INDEX FRAGMENTATION STATISTICS";
    public static final String CPU = "CPU";
    public static final String IO = "IO";
    public static final String LONG = "LONG";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String HOUR_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss a";
    public static final String ENABLED = "Enabled";
    public static final String OCCURS_ONCE_AT = "Occurs Once At";
    public static final String OCCURS_EVERY = "Occurs Every";
    public static final String END_DATE = "End Date";
    public static final String NO_END_DATE = "No End Date";
    public static final String QUERY_STATISTICS = "Query Statistics";
    public static final String CPU_IO_STATISTIC = "CPU IO Statistics";
    public static final String SCHEDULE_JOB = "Scheduled Jobs";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String SELECT_TIME = "Select Time";
    public static final String DISABLED = "Disabled";
    public static final String ALL = "All";
    public static final String STAR = "\\*";
    public static final String PERCENT = "\\%";
    public static final String CPU_IO_STATISTICS = "CPU IO Statistics";
    public static final String SECS = " Secs";
    public static final String MB = " MB";
    public static final String SELECT_ONE = "Select One -";
    public static final String PERCENTAGE_WITH_SPACE = " %";
    public static final String PERCENTAGE = "%";
    public static final String JOB = "Job";
    public static final String SUCCESS = "SUCCESS";
    public static final String SHOW_ALL = "Show All";
    public static final String STATUS = "status";
    public static final String FAIL = "FAIL";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String NULL_STRING = "null";
    public static final String DOUBLE_QUOTE = "\"\"";
    public static final String QUOTE = "\"";
    public static final String EXCEL_STRING_FORMAT = "\" %s \",";
    public static final String SERVER_LOGGING = "Server Logging";
    public static final String INBOUND = "Inbound";
    public static final String OUTBOUND = "Outbound";
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    private static final Object[] QUERY_TABLE_COLUMNS = new Object[]{
        "dataBase_Name", "text_Query", "value"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    private static final String[] QUERY_TABLE_HEADER = new String[]{"Database", QUERY, "Value"};
    private static final String[] INTERFACES_LIST = new String[]{"ACCRUAL_INBOUND_INTERFACE", "COMPANY_TRADE_CLASS_INTERFACE",
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
    

    private static final String[] OUT_BOUND_INTERFACE_LIST=new String[]{
            "Accrual Rate Projection" , "ADJUSTMENT_DETAILS_GTN" , "ADJUSTMENT_DETAILS_RESERVE" , "Cff_Outbound",  "Hierarchy_Definition",    "Relationship_Builder"};
    
	/**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    private static final Object[] PROCEDURE_TABLE_COLUMNS = new Object[]{"dataBase_Name", "procedure_Name",
        "text_Query", "value"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    private static final String[] PROCEDURE_TABLE_HEADER = new String[]{"Database", "Procedure Name", QUERY, "Value"};
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    private static final Object[] INDEX_TABLE_COLUMNS = new Object[]{
        "index_Name", "table_Name", "fragmentation_Percentage", "space_Used_Percentage", "total_Rows", "distributed_Rows"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    private static final String[] INDEX_TABLE_HEADER = new String[]{"Index Name", "Table Name", "Fragmentation Percentage",
        "Space Used Percentage", "Total Rows", "Distributed Rows"};
    /**
     * The Constant CPU_IO_TABLE_COLUMNS.
     */
    private static final Object[] JOB_TABLE_COLUMNS = new Object[]{
        "jobname", "enabled", "description", "occurs", "occurs_detail", "frequency", "execution_Time", "last_Execution_Day", "status"};
    /**
     * The Constant CPU_TABLE_HEADER.
     */
    private static final String[] JOB_TABLE_HEADER = new String[]{"Name", "Enabled", "Description",
        "Occurs", "Occurs detail", "Frequency", "Last Execution Duration", "Last Execution Date", "Last Execution Status"};
    public static final String TIMEONLY = "time-only";
    public static final String DOWNLOAD = "DOWNLOAD";
    public static final String RETURN_RESERVE_INTERFACE = "RETURN_RESERVE_INTERFACE";
    public static final String BEST_PRICE_INTERFACE = "BEST_PRICE_INTERFACE";
    public static final String CPI_INTERFACE = "CPI_INTERFACE";
    public static final String CUSTOMER_GTS_FORECAST_INTERFACE = "CUSTOMER_GTS_FORECAST_INTERFACE";
    public static final String MASTER_DATA_ATTRIBUTE_INTERFACE = "MASTER_DATA_ATTRIBUTE_INTERFACE";
    public static final String FORMULA_DETAILS_INTERFACE = "FORMULA_DETAILS_INTERFACE";
    public static final String ACTUAL_MASTER_INTERFACE = "ACTUAL_MASTER_INTERFACE";
    public static final String RETURNS_INTERFACE = "RETURNS_INTERFACE";
    public static final String ITEM_IDENTIFIER_INTERFACE = "ITEM_IDENTIFIER_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT = "INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE";
    public static final String DEMAND_ACTUAL_INTERFACE = "DEMAND_ACTUAL_INTERFACE";
    public static final String DEMAND_FORECAST_INTERFACE = "DEMAND_FORECAST_INTERFACE";
    public static final String CONTRACT_HEADER_INTERFACE = "CONTRACT_HEADER_INTERFACE";
    public static final String COMPANY_TRADE_CLASS_INTERFACE = "COMPANY_TRADE_CLASS_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN = "INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE";
    public static final String AVERAGE_SHELF_LIFE_INTERFACE = "AVERAGE_SHELF_LIFE_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER = "INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE";
    public static final String COMPANY_PARENT_INTERFACE = "COMPANY_PARENT_INTERFACE";
    public static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE = "ADJUSTED_DEMAND_ACTUAL_INTERFACE";
    public static final String PRICE_SCHEDULE_INTERFACE = "PRICE_SCHEDULE_INTERFACE";
    public static final String COMPANY_MASTER_INTERFACE = "COMPANY_MASTER_INTERFACE";
    public static final String FORECAST_SALES_INTERFACE = "FORECAST_SALES_INTERFACE";
    public static final String REBATE_SCHEDULE_INTERFACE = "REBATE_SCHEDULE_INTERFACE";
    public static final String CUSTOMER_GTS_ACTUAL_INTERFACE = "CUSTOMER_GTS_ACTUAL_INTERFACE";
    public static final String GL_BALANCE_INTERFACE = "GL_BALANCE_INTERFACE";
    public static final String GL_POSTING_INTERFACE = "GL_POSTING_INTERFACE";
    public static final String ITEM_FAMILY_PLAN_INTERFACE = "ITEM_FAMILY_PLAN_INTERFACE";
    public static final String LOT_MASTER_INTERFACE = "LOT_MASTER_INTERFACE";
    public static final String ITEM_HIERARCHY_DEFINATION_INTERFACE = "ITEM_HIERARCHY_DEFINATION_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF = "INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE";
    public static final String COMPANY_FAMILY_PLAN_INTERFACE = "COMPANY_FAMILY_PLAN_INTERFACE";
    public static final String ADJUSTED_DEMAND_FORECAST_INTERFACE = "ADJUSTED_DEMAND_FORECAST_INTERFACE";
    public static final String ITEM_MASTER_INTERFACE = "ITEM_MASTER_INTERFACE";
    public static final String REBATE_PLAN_INTERFACE = "REBATE_PLAN_INTERFACE";
    public static final String COMPANY_IDENTIFIER_INTERFACE = "COMPANY_IDENTIFIER_INTERFACE";
    public static final String SALES_MASTER_INTERFACE = "SALES_MASTER_INTERFACE";
    public static final String ITEM_HIERARCHY_INTERFACE = "ITEM_HIERARCHY_INTERFACE";
    public static final String ITEM_PRICING_INTERFACE = "ITEM_PRICING_INTERFACE";
    public static final String GL_COST_CENTER_INTERFACE = "GL_COST_CENTER_INTERFACE";
	
	public static Object[] getQueryTableColumns() {
		return QUERY_TABLE_COLUMNS.clone();
	}

	public static String[] getQueryTableHeader() {
		return QUERY_TABLE_HEADER.clone();
	}

    public static String[] getInterfaceslist() {
		return INTERFACES_LIST.clone();
	}
  
    public static String[] getOutboundinterfacelist() {
		return OUT_BOUND_INTERFACE_LIST.clone();
	}

	public static Object[] getProcedureTableColumns() {
		return PROCEDURE_TABLE_COLUMNS.clone();
	}

	public static String[] getProcedureTableHeader() {
		return PROCEDURE_TABLE_HEADER.clone();
	}

	public static Object[] getIndexTableColumns() {
		return INDEX_TABLE_COLUMNS.clone();
	}

	public static String[] getIndexTableHeader() {
		return INDEX_TABLE_HEADER.clone();
	}

	public static Object[] getJobTableColumns() {
		return JOB_TABLE_COLUMNS.clone();
	}

	public static String[] getJobTableHeader() {
		return JOB_TABLE_HEADER.clone();
	}
}
