package org.r2.etl.common.util;


/**
 * This file contains all interface file path.
 *
 * @author stpl
 */
public final class FilePathUtil {
	
	/** The variable used for logger. */
	public static final String VIEW_RESOL_PRE = "/WEB-INF/jsp/";

	
	/** The variable used for logger. */
	public static final String VIEW_RESOL_SUFF = ".jsp";

        /** The Constant BACKSLASH. */
	public static final String BACKSLASH="/";
              
	/** The Constant TEMP_PATH. */
	public static final String TEMP_PATH=BACKSLASH+"temp";
        
	public static  String latestServerName="";
	        
	
	/** The Constant LOG_FILE_NAME. */
	public static final String LOG_FILE_NAME="Error_Log.txt";
	
	/** The Constant MAIL_CONFIGURATION_FILE_NAME. */
	public static final String MAIL_CONFIGURATION_FILE_NAME="/MailConfiguration.properties";
	
	/** The Constant MAIL_CONFIGURATION_FILE_NAME. */
	public static final String ERROR_MAIL_FILE_NAME="MailConfiguration.properties";
	
	/** The Constant DATABASE_CONFIGURATION_FILE_NAME. */
	public static final String DATABASE_CONFIGURATION_FILE_NAME="/EtlConfiguration.properties";
        
        public static final String PROCEDURE_CONFIGURATION_FILE_NAME="/ETLProcedureInput.properties";

	
	/** The Constant CONTENT_CONFIGURATION_FILE_NAME. */
	public static final String CONTENT_CONFIGURATION_FILE_NAME="/ContentConfiguration.properties";

	/** The Constant CONTRACT_HEADER_INTERFACE. */
	public static final String CONTRACT_HEADER_INTERFACE="/CONTRACT_HEADER_INTERFACE/CONTRACT_HEADER_INTERFACE.kjb";
	
	/** The Constant CONTRACT_HEADER_INTERFACE_HISTORY. */
	public static final String CONTRACT_HEADER_INTERFACE_HISTORY="/CONTRACT_HEADER_INTERFACE_HISTORY/CONTRACT_HEADER_INTERFACE_HISTORY.kjb";
	
	/** The Constant COMPANY_FAMILYPLAN_INTERFACE. */
	public static final String COMPANY_FAMILYPLAN_INTERFACE="/COMPANY_FAMILYPLAN_INTERFACE/COMPANY_FAMILYPLAN_INTERFACE.kjb";
	/** The Constant COMPANY_FAMILYPLAN_INTERFACE. */
	public static final String COMPANY_FAMILYPLAN_INTERFACE_HISTORY="/COMPANY_FAMILYPLAN_INTERFACE_HISTORY/COMPANY_FAMILYPLAN_INTERFACE_HISTORY.kjb";
	
	/** The Constant ITEM_FAMILY_PLAN_INTERFACE. */
	public static final String ITEM_FAMILY_PLAN_INTERFACE="/ITEM_FAMILY_PLAN_INTERFACE/ITEM_FAMILYPLAN_INTERFACE.kjb";
	/** The Constant ITEM_FAMILY_PLAN_INTERFACE. */
	public static final String ITEM_FAMILY_PLAN_INTERFACE_HISTORY="/ITEM_FAMILY_PLAN_INTERFACE_HISTORY/ITEM_FAMILYPLAN_INTERFACE_HISTORY.kjb";
	
	/** The Constant PRICE_SCHEDULE_INTERFACE. */
	public static final String PRICE_SCHEDULE_INTERFACE="/PRICE_SCHEDULE_INTERFACE/PRICE_SCHEDULE_INTERFACE.kjb";
	
	/** The Constant PRICE_SCHEDULE_INTERFACE. */
	public static final String PRICE_SCHEDULE_INTERFACE_HISTORY="/PRICE_SCHEDULE_INTERFACE_HISTORY/PRICE_SCHEDULE_INTERFACE_HISTORY.kjb";
	
	/** The Constant REBATE_SCHEDULE_INTERFACE. */
	public static final String REBATE_SCHEDULE_INTERFACE="/REBATE_SCHEDULE_INTERFACE/REBATE_SCHEDULE_INTERFACE.kjb";
	/** The Constant REBATE_SCHEDULE_INTERFACE. */
	public static final String REBATE_SCHEDULE_INTERFACE_HISTORY="/REBATE_SCHEDULE_INTERFACE_HISTORY/REBATE_SCHEDULE_INTERFACE_HISTORY.kjb";
	
	/** The Constant REBATE_PLAN_INTERFACE. */
	public static final String REBATE_PLAN_INTERFACE="/REBATE_PLAN_INTERFACE/REBATE_PLAN_INTERFACE.kjb";
	/** The Constant REBATE_PLAN_INTERFACE. */
	public static final String REBATE_PLAN_INTERFACE_HISTORY="/REBATE_PLAN_INTERFACE_HISTORY/REBATE_PLAN_INTERFACE_HISTORY.kjb";
	
	/** The Constant ITEM_HIERARCHY_DEF_INTERFACE. */
	public static final String ITEM_HIERARCHY_DEF_INTERFACE="/ITEM_HIERARCHY_DEF_INTERFACE/ITEM_HIERARCHY_DEF_INTERFACE.kjb";
	
	/** The Constant ITEM_HIERARCHY_DEF_INTERFACE. */
	public static final String ITEM_HIERARCHY_DEF_INTERFACE_HISTORY="/ITEM_HIERARCHY_DEF_INTERFACE_HISTORY/ITEM_HIERARCHY_DEF_INTERFACE_HISTORY.kjb";
	
	/** The Constant AVERAGE_SHELF_LIFE_INTERFACE. */
	public static final String AVERAGE_SHELF_LIFE_INTERFACE="/AVERAGE_SHELF_LIFE_INTERFACE/AVERAGE_SHELF_LIFE_INTERFACE.kjb";
	
	/** The Constant AVERAGE_SHELF_LIFE_INTERFACE. */
	public static final String AVERAGE_SHELF_LIFE_INTERFACE_HISTORY="/AVERAGE_SHELF_LIFE_INTERFACE_HISTORY/AVERAGE_SHELF_LIFE_INTERFACE_HISTORY.kjb";
	
	/** The Constant GL_COST_CENTER_INTERFACE. */
	public static final String GL_COST_CENTER_INTERFACE="/GL_COST_CENTER_INTERFACE/GL_COST_CENTER_INTERFACE.kjb";
	
	/** The Constant GL_COST_CENTER_INTERFACE. */
	public static final String GL_COST_CENTER_INTERFACE_HISTORY="/GL_COST_CENTER_INTERFACE_HISTORY/GL_COST_CENTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant LOT_MASTER_INTERFACE. */
	public static final String LOT_MASTER_INTERFACE="/LOT_MASTER_INTERFACE/LOT_MASTER_INTERFACE.kjb";
	
	/** The Constant LOT_MASTER_INTERFACE. */
	public static final String LOT_MASTER_INTERFACE_HISTORY="/LOT_MASTER_INTERFACE_HISTORY/LOT_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant BEST_PRICE_INTERFACE. */
	public static final String BEST_PRICE_INTERFACE="/BEST_PRICE_INTERFACE/BEST_PRICE_INTERFACE.kjb";
	
	/** The Constant BEST_PRICE_INTERFACE. */
	public static final String BEST_PRICE_INTERFACE_HISTORY="/BEST_PRICE_INTERFACE_HISTORY/BEST_PRICE_INTERFACE_HISTORY.kjb";
	
	/** The Constant COMPANY_MASTER_INTERFACE. */
	public static final String COMPANY_MASTER_INTERFACE="/COMPANY_MASTER_INTERFACE/COMPANY_MASTER_INTERFACE.kjb";
	
	/** The Constant COMPANY_MASTER_INTERFACE_HISTORY. */
	public static final String COMPANY_MASTER_INTERFACE_HISTORY="/COMPANY_MASTER_INTERFACE_HISTORY/COMPANY_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant COMPANY_PARENT_INTERFACE. */
	public static final String COMPANY_PARENT_INTERFACE="/COMPANY_PARENT_INTERFACE/COMPANY_PARENT_INTERFACE.kjb";
	
	/** The Constant COMPANY_TRADE_CLASS_INTERFACE. */
	public static final String COMPANY_TRADE_CLASS_INTERFACE="/COMPANY_TRADE_CLASS_INTERFACE/COMPANY_TRADE_CLASS_INTERFACE.kjb";
	
	/** The Constant COMPANY_IDENTIFIER_INTERFACE. */
	public static final String COMPANY_IDENTIFIER_INTERFACE="/COMPANY_IDENTIFIER_INTERFACE/COMPANY_IDENTIFIER_INTERFACE.kjb";
	
	/** The Constant COMPANY_IDENTIFIER_INTERFACE History. */
	public static final String COMPANY_IDENTIFIER_INTERFACE_HISTORY="/COMPANY_IDENTIFIER_INTERFACE_HISTORY/COMPANY_IDENTIFIER_INTERFACE_HISTORY.kjb";
	
	/** The Constant ITEM_MASTER_INTERFACE. */
	public static final String ITEM_MASTER_INTERFACE="/ITEM_MASTER_INTERFACE/ITEM_MASTER_INTERFACE.kjb";
	/** The Constant ITEM_MASTER_INTERFACE. */
	public static final String ITEM_MASTER_INTERFACE_HISTORY="/ITEM_MASTER_INTERFACE_HISTORY/ITEM_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant ITEM_IDENTIFIER_INTERFACE. */
	public static final String ITEM_IDENTIFIER_INTERFACE="/ITEM_IDENTIFIER_INTERFACE/ITEM_IDENTIFIER_INTERFACE.kjb";
	
	/** The Constant ITEM_IDENTIFIER_INTERFACE. */
	public static final String ITEM_IDENTIFIER_INTERFACE_HISTORY="/ITEM_IDENTIFIER_INTERFACE_HISTORY/ITEM_IDENTIFIER_INTERFACE_HISTORY.kjb";
	/** The Constant ITEM_PRICING_INTERFACE. */
	public static final String ITEM_PRICING_INTERFACE="/ITEM_PRICING_INTERFACE/ITEM_PRICING_INTERFACE.kjb";
	
	/** The Constant ITEM_PRICING_INTERFACE. */
	public static final String ITEM_PRICING_INTERFACE_HISTORY="/ITEM_PRICING_INTERFACE_HISTORY/ITEM_PRICING_INTERFACE_HISTORY.kjb";
	
	/** The Constant ACTUAL_MASTER_INTERFACE. */
	public static final String ACTUAL_MASTER_INTERFACE="/ACTUAL_MASTER_INTERFACE/ACTUAL_MASTER_INTERFACE.kjb";
	
	/** The Constant ACTUAL_MASTER_INTERFACE. */
	public static final String ACTUAL_MASTER_INTERFACE_HISTORY="/ACTUAL_MASTER_INTERFACE_HISTORY/ACTUAL_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant ITEM_HIERARCHY_INTERFACE. */
	public static final String ITEM_HIERARCHY_INTERFACE="/ITEM_HIERARCHY_INTERFACE/ITEM_HIERARCHY_INTERFACE.kjb";
	
	/** The Constant ITEM_HIERARCHY_INTERFACE. */
	public static final String ITEM_HIERARCHY_INTERFACE_HISTORY="/ITEM_HIERARCHY_INTERFACE_HISTORY/ITEM_HIERARCHY_INTERFACE_HISTORY.kjb";
	
	/** The Constant SALES_MASTER_INTERFACE. */
	public static final String SALES_MASTER_INTERFACE="/SALES_MASTER_INTERFACE/SALES_MASTER_INTERFACE.kjb";
	/** The Constant SALES_MASTER_INTERFACE. */
	public static final String SALES_MASTER_INTERFACE_HISTORY="/SALES_MASTER_INTERFACE_HISTORY/SALES_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant FORECASTING_MASTER_INTERFACE. */
	public static final String FORECASTING_MASTER_INTERFACE="/FORECASTING_MASTER_INTERFACE/FORECASTING_MASTER_INTERFACE.kjb";
	
	/** The Constant FORECASTING_MASTER_INTERFACE. */
	public static final String FORECASTING_MASTER_INTERFACE_HISTORY="/FORECASTING_MASTER_INTERFACE_HISTORY/FORECASTING_MASTER_INTERFACE_HISTORY.kjb";
	
	/** The Constant GL_BALANCE_INTERFACE. */
	public static final String GL_BALANCE_INTERFACE="/GL_BALANCE_INTERFACE/GL_BALANCE_INTERFACE.kjb";
	/** The Constant GL_BALANCE_INTERFACE. */
	public static final String GL_BALANCE_INTERFACE_HISTORY="/GL_BALANCE_INTERFACE_HISTORY/GL_BALANCE_INTERFACE_HISTORY.kjb";
	
	/** The Constant CPI_INDEX_INTERFACE. */
	public static final String CPI_INDEX_INTERFACE="/CPI_INDEX_INTERFACE/CPI_INDEX_INTERFACE.kjb";
	/** The Constant CPI_INDEX_INTERFACE. */
	public static final String CPI_INDEX_INTERFACE_HISTORY="/CPI_INDEX_INTERFACE_HISTORY/CPI_INDEX_INTERFACE_HISTORY.kjb";
	
	/** The Constant FORMULA_DETAILS_INTERFACE. */
	public static final String FORMULA_DETAILS_INTERFACE="/FORMULA_DETAILS_INTERFACE/FORMULA_DETAILS_INTERFACE.kjb";
	
	/** The Constant FORMULA_DETAILS_INTERFACE_HISTORY. */
	public static final String FORMULA_DETAILS_INTERFACE_HISTORY="/FORMULA_DETAILS_INTERFACE_HISTORY/FORMULA_DETAILS_INTERFACE_HISTORY.kjb";
	
	/** The Constant MASTER_DATA_INTERFACE. */
	public static final String MASTER_DATA_INTERFACE_HISTORY="/MASTER_DATA_INTERFACE_HISTORY/MASTER_DATA_INTERFACE_HISTORY.kjb";
	
	/** The Constant MASTER_DATA_INTERFACE. */
	public static final String MASTER_DATA_INTERFACE="/MASTER_DATA_INTERFACE/MASTER_DATA_INTERFACE.kjb";
	
	/** The Constant COMPANY PARENT HISTORICAL LOAD. */
	public static final String COMPANY_PARENT_INTERFACE_HISTORY="/COMPANY_PARENT_HISTORY_INTERFACE/COMPANY_PARENT_HISTORY.kjb";
	
	/** The Constant COMPANY_TRADE_CLASS_INTERFACE HISTORY. */
	public static final String COMPANY_TRADE_CLASS_INTERFACE_HISTORY="/COMPANY_TRADE_CLASS_HISTORY_INTERFACE/COMPANY_TRADE_CLASS_HISTORY_INTERFACE.kjb";
	/** The Constant ACCRUAL_MASTER_INTERFACE. */
	public static final String ACCRUAL_MASTER_INBOUND="/ACCRUAL_MASTER_INBOUND/ACCRUAL_MASTER_INTERFACE.kjb";
	
	/** The Constant ACCRUAL_OUTBOUND_INTERFACE. */
	public static final String ACCRUAL_DETAIL_OUTBOUND="/ACCRUAL_DETAIL_OUTBOUND/ACCRUAL_OUTBOUND_INTERFACE.kjb";
	
	/** The Constant GL_POSTING_INTERFACE. */
	public static final String GL_POSTING_INTERFACE="/GL_POSTING_INTERFACE/Gl_POSTING_INTERFACE.kjb";
	/** The Constant DEMAND_ACTUAL_INTERFACE. */
	public static final String DEMAND_ACTUAL_INTERFACE="/DEMAND_ACTUAL_INTERFACE/DEMAND_ACTUAL_INTERFACE.kjb";
	/** The Constant DEMAND_FORECAST_INTERFACE. */
	public static final String DEMAND_FORECAST_INTERFACE="/DEMAND_FORECAST_INTERFACE/DEMAND_FORECAST_INTERFACE.kjb";
	/** The Constant INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE. */
	public static final String INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE="/INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE/INVENTORY_WITHDRAWAL_ACTUAL_DETAIL.kjb";
	/** The Constant INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE. */
	public static final String INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE="/INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE/INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY.kjb";
	/** The Constant INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE. */
	public static final String INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE="/INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE/INVENTORY_WITHDRAWAL_PROJECTED_DETAIL.kjb";
	/** The Constant INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE. */
	public static final String INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE="/INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE/INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY.kjb";
	/** The Constant RETURNS_INTERFACE. */
	public static final String RETURNS_INTERFACE1="/RETURNS_INTERFACE/RETURNS_MASTER_INTERFACE.kjb";
	
	/** The Constant ADJUSTED_DEMAND_ACTUAL_INTERFACE. */
	public static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE1="/ADJUSTED_DEMAND_ACTUAL_INTERFACE/ADJUSTED_DEMAND_ACTUAL_INTERFACE.kjb";
	/** The Constant ADJUSTED_DEMAND_FORECASTINTERFACE. */
	public static final String ADJUSTED_DEMAND_FORECASTINTERFACE1="/ADJUSTED_DEMAND_FORECAST_INTERFACE/ADJUSTED_DEMAND_FORECAST_INTERFACE.kjb";
	/** The Constant CUSTOMER_GTS_ACTUAL_INTERFACE. */
	public static final String CUSTOMER_GTS_ACTUAL_INTERFACE1="/CUSTOMER_GTS_ACTUAL_INTERFACE/CUSTOMER_GTS_ACTUAL.kjb";
	/** The Constant CUSTOMER_GTS_FORECAST_INTERFACE. */
	public static final String CUSTOMER_GTS_FORECAST_INTERFACE1="/CUSTOMER_GTS_FORECAST_INTERFACE/CUSTOMER_GTS_FORECAST.kjb";
	
	/** The Constant RETURN_RESERVE_INTERFACE. */
	public static final String RETURN_RESERVE_INTERFACE1="/RETURN_RESERVE_INTERFACE/RETURN_RESERVE_INTERFACE.kjb";
	/** The Constant CFF_OUTBOUND_INTERFACE. */
	public static final String CFF_OUTBOUND_INTERFACE1="/CFF_OUTBOUND_INTERFACE/CFF_OUTBOUND_INTERFACE.kjb";
	
	/** The Constant ARP_OUTBOUND_INTERFACE. */
	public static final String ARP_OUTBOUND_INTERFACE1="/ARP_OUTBOUND_INTERFACE/ARP_OUTBOUND_INTERFACE.kjb";
	
	
	/** The Constant ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE. */
	public static final String ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE1="/ADJUSTMENT_GTN_DETAIL_INTERFACE/ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE.kjb";
	/** The Constant ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE. */
	public static final String ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE1="/ADJUSTMENT_RESERVE_DETAIL_INTERFACE/ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE.kjb";

        /** The Constant ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE. */
	public static final String ITEM_UOM_INTERFACE="/ITEM_UOM_INTERFACE/ITEM_UOM_INTERFACE.kjb";
        
       public static final String RETURN_RATE_FORECAST_INTERFACE="/RETURN_RATE_FORECAST_INTERFACE/RETURN_RATE_FORECAST_MASTER_INTERFACE.kjb";

       public static final String TESTING_INTERFACE="/TESTING_INTERFACE/ENCRYTEDFILE.kjb";
       
       public static final String ADROIT_ACTUAL_MASTER_CHARGEBACK_INTERFACE="/ADROIT_ACTUAL_MASTER_CHARGEBACK_INTERFACE/ADROIT_ACTUAL_MASTER_CHARGEBACK_INTERFACE.kjb";
    public static final String ADROIT_ACTUAL_MASTER_MEDICAID_INTERFACE = "/ADROIT_ACTUAL_MASTER_MEDICAID_INTERFACE/ADROIT_ACTUAL_MASTER_MEDICAID_INTERFACE.kjb";
    public static final String ADROIT_ACTUAL_MASTER_REBATE_INTERFACE = "/ADROIT_ACTUAL_MASTER_REBATE_INTERFACE/ADROIT_ACTUAL_MASTER_REBATE_INTERFACE.kjb";
    public static final String ADROIT_ACTUAL_MASTER_COUPON_INTERFACE = "/ADROIT_ACTUAL_MASTER_COUPON_INTERFACE/ADROIT_ACTUAL_MASTER_COUPON_INTERFACE.kjb";
    public static final String ADROIT_ITEM_HIERARCHY_INTERFACE = "/ADROIT_ITEM_HIERARCHY_INTERFACE/ADROIT_ITEM_HIERARCHY_INTERFACE.kjb";
    public static final String ADROIT_ITEM_BUSINESS_WAREHOUSE_INTERFACE = "/ADROIT_ITEM_BUSINESS_WAREHOUSE_INTERFACE/ADROIT_ITEM_BUSINESS_WAREHOUSE_INTERFACE.kjb";
    
       
       
       
       
       
    public static final String ADROIT_COMPANY_MASTER_INTERFACE = "/ADROIT_COMPANY_MASTER_INTERFACE/ADROIT_COMPANY_MASTER_INTERFACE.kjb";
    public static final String ADROIT_COMPANY_IDENTIFIER_INTERFACE = "/ADROIT_COMPANY_IDENTIFIER_INTERFACE/ADROIT_COMPANY_IDENTIFIER_INTERFACE.kjb";
    public static final String ADROIT_COMPANY_TRADE_CLASS_INTERFACE = "/ADROIT_COMPANY_TRADE_CLASS_INTERFACE/ADROIT_COMPANY_TRADE_CLASS_INTERFACE.kjb";
    public static final String ADROIT_ITEM_IDENTIFIER_INTERFACE = "/ADROIT_ITEM_IDENTIFIER_INTERFACE/ADROIT_ITEM_IDENTIFIER_INTERFACE.kjb";
    public static final String ADROIT_ITEM_MASTER_INTERFACE = "/ADROIT_ITEM_MASTER_INTERFACE/ADROIT_ITEM_MASTER_INTERFACE.kjb";
    public static final String ADROIT_ITEM_PRICING_INTERFACE = "/ADROIT_ITEM_PRICING_INTERFACE/ADROIT_ITEM_PRICING_INTERFACE.kjb";
    public static final String ADROIT_CONTRACT_HEADER_INTERFACE = "/ADROIT_CONTRACT_HEADER_INTERFACE/ADROIT_CONTRACT_HEADER_INTERFACE.kjb";
    public static final String ADROIT_COMPANY_FAMILY_PLAN_INTERFACE = "/ADROIT_COMPANY_FAMILY_PLAN_INTERFACE/ADROIT_COMPANY_FAMILY_PLAN_INTERFACE.kjb";
    public static final String ADROIT_ITEM_FAMILY_PLAN_INTERFACE = "/ADROIT_ITEM_FAMILY_PLAN_INTERFACE/ADROIT_ITEM_FAMILY_PLAN_INTERFACE.kjb";
    public static final String ADROIT_REBATE_PLAN_INTERFACE = "/ADROIT_REBATE_PLAN_INTERFACE/ADROIT_REBATE_PLAN_INTERFACE.kjb";
    public static final String ADROIT_PRICE_SCHEDULE_INTERFACE = "/ADROIT_PRICE_SCHEDULE_INTERFACE/ADROIT_PRICE_SCHEDULE_INTERFACE.kjb";
    public static final String ADROIT_REBATE_SCHEDULE_INTERFACE = "/ADROIT_REBATE_SCHEDULE_INTERFACE/ADROIT_REBATE_SCHEDULE_INTERFACE.kjb";
    public static final String ADROIT_SALES_MASTER_INTERFACE = "/ADROIT_SALES_MASTER_INTERFACE/ADROIT_SALES_MASTER_INTERFACE.kjb";
    public static final String ADROIT_CUSTOMER_GTS_ACTUAL_INTERFACE = "/ADROIT_CUSTOMER_GTS_ACTUAL_INTERFACE/ADROIT_CUSTOMER_GTS_ACTUAL_INTERFACE.kjb";
    public static final String ADROIT_CUSTOMER_GTS_FORECAST_INTERFACE = "/ADROIT_CUSTOMER_GTS_FORECAST_INTERFACE/ADROIT_CUSTOMER_GTS_FORECAST_INTERFACE.kjb";
    public static final String ADROIT_RETURNS_INTERFACE = "/ADROIT_RETURNS_INTERFACE/ADROIT_RETURNS_INTERFACE.kjb";
    public static final String ADROIT_CPI_INDEX_INTERFACE = "/ADROIT_CPI_INDEX_INTERFACE/ADROIT_CPI_INDEX_INTERFACE.kjb";
    public static final String ADROIT_GL_COST_CENTER_INTERFACE = "/ADROIT_GL_COST_CENTER_INTERFACE/ADROIT_GL_COST_CENTER_INTERFACE.kjb";
    public static final String ADROIT_ITEM_UOM_INTERFACE = "/ADROIT_ITEM_UOM_INTERFACE/ADROIT_ITEM_UOM_INTERFACE.kjb";
    public static final String ADROIT_ACCRUAL_INBOUND_INTERFACE = " /ADROIT_ACCRUAL_INBOUND_INTERFACE/ADROIT_ACCRUAL_INBOUND_INTERFACE.kjb";
    public static final String ADROIT_FORECASTING_MASTER_INTERFACE = " /ADROIT_FORECASTING_MASTER_INTERFACE/ADROIT_FORECASTING_MASTER_INTERFACE.kjb";
	
       
       private FilePathUtil(){
        }
    public static String getLOG_PATH() {
        return latestServerName.isEmpty()?BACKSLASH+"log.txt":BACKSLASH+latestServerName+"_log.txt";
    }


    public static String getERROR_LOG_PATH() {
         return latestServerName.isEmpty()?BACKSLASH+"Error_Log.txt":BACKSLASH+latestServerName+"_Error_Log.txt";
    }

}
