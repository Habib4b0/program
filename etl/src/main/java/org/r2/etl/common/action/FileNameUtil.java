package org.r2.etl.common.action;
import java.util.HashMap;
import java.util.Map;
import org.r2.etl.common.util.Constants;

public class FileNameUtil {
   private FileNameUtil()
    {}
	private static final  Map<String,String> map=new HashMap<String,String>();
	private static final  Map<String,String> interfaceNameMap=new HashMap<String,String>();
public static Map<String, String> getMap() {
		if(map.isEmpty()){
			map.put(Constants.COMPANY_MASTER, Constants.COMPANY_MASTER);
			map.put(Constants.COM_MASTER_INTF, Constants.COMPANY_MASTER);
			map.put(Constants.COMPANY_PARENT_HIS, COMPANY_PARENT_INTERFACE);
			map.put(COMPANY_PARENT_INTERFACE, COMPANY_PARENT_INTERFACE);
			map.put(COMPANY_TRADE_CLASS_HISTORY_INTERFACE, COMPANY_TRADE_CLASS_INTERFACE);
			map.put(COMPANY_TRADE_CLASS_INTERFACE, COMPANY_TRADE_CLASS_INTERFACE);
			map.put(COMPANY_IDENTIFIER_INTERFACE_HISTORY,Constants.COMPANY_IDENT);
			map.put(Constants.COMPANY_IDENT, Constants.COMPANY_IDENT);			
			map.put(ITEM_MASTER_INTERFACE, ITEM_MASTER_INTERFACE);
			map.put(ITEM_MASTER_INTERFACE_HISTORY, ITEM_MASTER_INTERFACE);
			map.put(ITEM_PRICING_INTERFACE, ITEM_PRICING_INTERFACE);
			map.put(ITEM_PRICING_INTERFACE_HISTORY, ITEM_PRICING_INTERFACE);
			map.put(ITEM_IDENTIFIER_INTERFACE_HISTORY, ITEM_IDENTIFIER_INTERFACE);
			map.put(ITEM_IDENTIFIER_INTERFACE, ITEM_IDENTIFIER_INTERFACE);
			map.put(CONTRACT_HEADER_INTERFACE_HISTORY, CONTRACT_HEADER_INTERFACE);
			map.put(CONTRACT_HEADER_INTERFACE,CONTRACT_HEADER_INTERFACE);			
			map.put(COMPANY_FAMILYPLAN_INTERFACE_HISTORY, COMPANY_FAMILY_PLAN_INTERFACE);
			map.put(COMPANY_FAMILY_PLAN_INTERFACE,COMPANY_FAMILY_PLAN_INTERFACE);			
			map.put(ITEM_FAMILY_PLAN_INTERFACE_HISTORY, ITEM_FAMILY_PLAN_INTERFACE);
			map.put(ITEM_FAMILY_PLAN_INTERFACE,ITEM_FAMILY_PLAN_INTERFACE);			
			map.put(PRICE_SCHEDULE_INTERFACE_HISTORY, PRICE_SCHEDULE_INTERFACE);
			map.put(PRICE_SCHEDULE_INTERFACE,PRICE_SCHEDULE_INTERFACE);			
			map.put(REBATE_PLAN_INTERFACE_HISTORY, REBATE_PLAN_INTERFACE);
			map.put(REBATE_PLAN_INTERFACE,REBATE_PLAN_INTERFACE);			
			map.put(REBATE_SCHEDULE_INTERFACE_HISTORY, REBATE_SCHEDULE_INTERFACE);
			map.put(REBATE_SCHEDULE_INTERFACE,REBATE_SCHEDULE_INTERFACE);
			map.put(ACTUAL_MASTER_INTERFACE_HISTORY, ACTUAL_MASTER_INTERFACE);
			map.put(ACTUAL_MASTER_INTERFACE,ACTUAL_MASTER_INTERFACE);			
			map.put(SALES_MASTER_INTERFACE_HISTORY, SALES_MASTER_INTERFACE);
			map.put(SALES_MASTER_INTERFACE,SALES_MASTER_INTERFACE);			
			map.put(ITEM_HIERARCHY_INTERFACE_HISTORY, ITEM_HIERARCHY_INTERFACE);
			map.put(ITEM_HIERARCHY_INTERFACE,ITEM_HIERARCHY_INTERFACE);			
			map.put(ITEM_HIERARCHY_DEF_INTERFACE_HISTORY, ITEM_HIERARCHY_DEFINATION_INTERFACE);
			map.put(ITEM_HIERARCHY_DEF_INTERFACE, ITEM_HIERARCHY_DEFINATION_INTERFACE);			
			map.put(FORECASTING_MASTER_INTERFACE_HISTORY, FORECAST_SALES_INTERFACE);
			map.put(FORECASTING_MASTER_INTERFACE, FORECAST_SALES_INTERFACE);			
			map.put(GL_BALANCE_INTERFACE_HISTORY, GL_BALANCE_INTERFACE);
			map.put(GL_BALANCE_INTERFACE,GL_BALANCE_INTERFACE);			
			map.put(CPI_INDEX_INTERFACE_HISTORY, CPI_INTERFACE);
			map.put(CPI_INTERFACE,CPI_INTERFACE);			
			map.put(FORMULA_DETAILS_INTERFACE_HISTORY, FORMULA_DETAILS_INTERFACE);
			map.put(FORMULA_DETAILS_INTERFACE,FORMULA_DETAILS_INTERFACE);			
			map.put(MASTER_DATA_INTERFACE_HISTORY, MASTER_DATA_ATTRIBUTE_INTERFACE);
			map.put(MASTER_DATA_INTERFACE, MASTER_DATA_ATTRIBUTE_INTERFACE);			
			map.put(BEST_PRICE_INTERFACE_HISTORY, BEST_PRICE_INTERFACE);
			map.put(BEST_PRICE_INTERFACE,BEST_PRICE_INTERFACE);			
			map.put(AVERAGE_SHELF_LIFE_INTERFACE_HISTORY, AVERAGE_SHELF_LIFE_INTERFACE);
			map.put(AVERAGE_SHELF_LIFE_INTERFACE,AVERAGE_SHELF_LIFE_INTERFACE);			
			map.put(GL_COST_CENTER_INTERFACE_HISTORY, GL_COST_CENTER_INTERFACE);
			map.put(GL_COST_CENTER_INTERFACE,GL_COST_CENTER_INTERFACE);			
			map.put(LOT_MASTER_INTERFACE_HISTORY, LOT_MASTER_INTERFACE);
			map.put(LOT_MASTER_INTERFACE,LOT_MASTER_INTERFACE);
			map.put(ACCRUAL_MASTER_INBOUND, ACCRUAL_INBOUND_INTERFACE);
			map.put(GL_POSTING_INTERFACE, GL_POSTING_INTERFACE);
			map.put(INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF, INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF);			
			map.put(INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER, INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER);			
			map.put(INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT, INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT);			
			map.put(INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN, INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN);
			map.put(DEMAND_ACTUAL_INTERFACE, DEMAND_ACTUAL_INTERFACE);			
			map.put(DEMAND_FORECAST_INTERFACE, DEMAND_FORECAST_INTERFACE);	
			map.put(RETURNS_INTERFACE, RETURNS_INTERFACE);		
			map.put(CUSTOMER_GTS_ACTUAL_INTERFACE, CUSTOMER_GTS_ACTUAL_INTERFACE);			
			map.put(CUSTOMER_GTS_FORECAST_INTERFACE, CUSTOMER_GTS_FORECAST_INTERFACE);
			map.put(ADJUSTED_DEMAND_ACTUAL_INTERFACE, ADJUSTED_DEMAND_ACTUAL_INTERFACE);			
			map.put(ADJUSTED_DEMAND_FORECAST_INTERFACE, ADJUSTED_DEMAND_FORECAST_INTERFACE);
			map.put(RETURN_RESERVE_INTERFACE, RETURN_RESERVE_INTERFACE);
			map.put(ITEM_UOM_INTERFACE, ITEM_UOM_INTERFACE);
		}
		return map;
	}
    public static final String GL_POSTING_INTERFACE = "GL_POSTING_INTERFACE";
    public static final String ACCRUAL_MASTER_INBOUND = "ACCRUAL_MASTER_INBOUND";
    public static final String MASTER_DATA_INTERFACE = "MASTER_DATA_INTERFACE";
    public static final String FORECASTING_MASTER_INTERFACE = "FORECASTING_MASTER_INTERFACE";
    public static final String ITEM_HIERARCHY_DEF_INTERFACE = "ITEM_HIERARCHY_DEF_INTERFACE";
    public static final String RETURN_RESERVE_INTERFACE = "RETURN_RESERVE_INTERFACE";
    public static final String ADJUSTED_DEMAND_FORECAST_INTERFACE = "ADJUSTED_DEMAND_FORECAST_INTERFACE";
    public static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE = "ADJUSTED_DEMAND_ACTUAL_INTERFACE";
    public static final String CUSTOMER_GTS_FORECAST_INTERFACE = "CUSTOMER_GTS_FORECAST_INTERFACE";
    public static final String CUSTOMER_GTS_ACTUAL_INTERFACE = "CUSTOMER_GTS_ACTUAL_INTERFACE";
    public static final String RETURNS_INTERFACE = "RETURNS_INTERFACE";
    public static final String DEMAND_FORECAST_INTERFACE = "DEMAND_FORECAST_INTERFACE";
    public static final String DEMAND_ACTUAL_INTERFACE = "DEMAND_ACTUAL_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN = "INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT = "INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER = "INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE";
    public static final String INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF = "INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE";
    public static final String ACCRUAL_INBOUND_INTERFACE = "ACCRUAL_INBOUND_INTERFACE";
    public static final String LOT_MASTER_INTERFACE_HISTORY = "LOT_MASTER_INTERFACE_HISTORY";
    public static final String GL_COST_CENTER_INTERFACE1 = "GL_COST_CENTER_INTERFACE";
    public static final String GL_COST_CENTER_INTERFACE_HISTORY = "GL_COST_CENTER_INTERFACE_HISTORY";
    public static final String AVERAGE_SHELF_LIFE_INTERFACE_HISTORY = "AVERAGE_SHELF_LIFE_INTERFACE_HISTORY";
    public static final String BEST_PRICE_INTERFACE_HISTORY = "BEST_PRICE_INTERFACE_HISTORY";
    public static final String MASTER_DATA_INTERFACE_HISTORY = "MASTER_DATA_INTERFACE_HISTORY";
    public static final String FORMULA_DETAILS_INTERFACE_HISTORY = "FORMULA_DETAILS_INTERFACE_HISTORY";
    public static final String CPI_INDEX_INTERFACE_HISTORY = "CPI_INDEX_INTERFACE_HISTORY";
    public static final String GL_BALANCE_INTERFACE_HISTORY = "GL_BALANCE_INTERFACE_HISTORY";
    public static final String FORECASTING_MASTER_INTERFACE_HISTORY = "FORECASTING_MASTER_INTERFACE_HISTORY";
    public static final String ITEM_HIERARCHY_DEF_INTERFACE_HISTORY = "ITEM_HIERARCHY_DEF_INTERFACE_HISTORY";
    public static final String ITEM_HIERARCHY_INTERFACE_HISTORY = "ITEM_HIERARCHY_INTERFACE_HISTORY";
    public static final String SALES_MASTER_INTERFACE_HISTORY = "SALES_MASTER_INTERFACE_HISTORY";
    public static final String ACTUAL_MASTER_INTERFACE_HISTORY = "ACTUAL_MASTER_INTERFACE_HISTORY";
    public static final String REBATE_SCHEDULE_INTERFACE_HISTORY = "REBATE_SCHEDULE_INTERFACE_HISTORY";
    public static final String REBATE_PLAN_INTERFACE_HISTORY = "REBATE_PLAN_INTERFACE_HISTORY";
    public static final String PRICE_SCHEDULE_INTERFACE_HISTORY = "PRICE_SCHEDULE_INTERFACE_HISTORY";
    public static final String ITEM_FAMILY_PLAN_INTERFACE_HISTORY = "ITEM_FAMILY_PLAN_INTERFACE_HISTORY";
    public static final String COMPANY_FAMILYPLAN_INTERFACE_HISTORY = "COMPANY_FAMILYPLAN_INTERFACE_HISTORY";
    public static final String CONTRACT_HEADER_INTERFACE_HISTORY = "CONTRACT_HEADER_INTERFACE_HISTORY";
    public static final String LOT_MASTER_INTERFACE = "LOT_MASTER_INTERFACE";
    public static final String GL_COST_CENTER_INTERFACE = "GL_COST_CENTER_INTERFACE";
    public static final String AVERAGE_SHELF_LIFE_INTERFACE = "AVERAGE_SHELF_LIFE_INTERFACE";
    public static final String BEST_PRICE_INTERFACE = "BEST_PRICE_INTERFACE";
    public static final String MASTER_DATA_ATTRIBUTE_INTERFACE = "MASTER_DATA_ATTRIBUTE_INTERFACE";
    public static final String FORMULA_DETAILS_INTERFACE = "FORMULA_DETAILS_INTERFACE";
    public static final String CPI_INTERFACE = "CPI_INTERFACE";
    public static final String GL_BALANCE_INTERFACE = "GL_BALANCE_INTERFACE";
    public static final String FORECAST_SALES_INTERFACE = "FORECAST_SALES_INTERFACE";
    public static final String ITEM_HIERARCHY_DEFINATION_INTERFACE = "ITEM_HIERARCHY_DEFINATION_INTERFACE";
    public static final String ITEM_HIERARCHY_INTERFACE = "ITEM_HIERARCHY_INTERFACE";
    public static final String SALES_MASTER_INTERFACE = "SALES_MASTER_INTERFACE";
    public static final String ACTUAL_MASTER_INTERFACE = "ACTUAL_MASTER_INTERFACE";
    public static final String REBATE_SCHEDULE_INTERFACE = "REBATE_SCHEDULE_INTERFACE";
    public static final String REBATE_PLAN_INTERFACE = "REBATE_PLAN_INTERFACE";
    public static final String PRICE_SCHEDULE_INTERFACE = "PRICE_SCHEDULE_INTERFACE";
    public static final String ITEM_FAMILY_PLAN_INTERFACE = "ITEM_FAMILY_PLAN_INTERFACE";
    public static final String COMPANY_FAMILY_PLAN_INTERFACE = "COMPANY_FAMILY_PLAN_INTERFACE";
    public static final String CONTRACT_HEADER_INTERFACE = "CONTRACT_HEADER_INTERFACE";
    public static final String ITEM_IDENTIFIER_INTERFACE_HISTORY = "ITEM_IDENTIFIER_INTERFACE_HISTORY";
    public static final String ITEM_IDENTIFIER_INTERFACE = "ITEM_IDENTIFIER_INTERFACE";
    public static final String ITEM_PRICING_INTERFACE_HISTORY = "ITEM_PRICING_INTERFACE_HISTORY";
    public static final String ITEM_PRICING_INTERFACE = "ITEM_PRICING_INTERFACE";
    public static final String ITEM_MASTER_INTERFACE_HISTORY = "ITEM_MASTER_INTERFACE_HISTORY";
    public static final String ITEM_MASTER_INTERFACE = "ITEM_MASTER_INTERFACE";
    public static final String COMPANY_IDENTIFIER_INTERFACE_HISTORY = "COMPANY_IDENTIFIER_INTERFACE_HISTORY";
    public static final String COMPANY_IDENTIFIER_INTERFACE = "COMPANY_IDENTIFIER_INTERFACE";
    public static final String COMPANY_TRADE_CLASS_HISTORY_INTERFACE = "COMPANY_TRADE_CLASS_HISTORY_INTERFACE";
    public static final String COMPANY_TRADE_CLASS_INTERFACE = "COMPANY_TRADE_CLASS_INTERFACE";
    public static final String COMPANY_PARENT_INTERFACE = "COMPANY_PARENT_INTERFACE";
    public static final String ITEM_UOM_INTERFACE = "ITEM_UOM_INTERFACE";
	public static Map<String, String> getInterfaceName() {
		if(interfaceNameMap.isEmpty()){
			interfaceNameMap.put(Constants.COMPANY_MASTER, Constants.COMPANY_MASTER);
			interfaceNameMap.put(Constants.COM_MASTER_INTF, Constants.COMPANY_MASTER);
			interfaceNameMap.put(Constants.COMPANY_PARENT_HIS, COMPANY_PARENT_INTERFACE);
			interfaceNameMap.put(COMPANY_PARENT_INTERFACE, COMPANY_PARENT_INTERFACE);
			interfaceNameMap.put(COMPANY_TRADE_CLASS_HISTORY_INTERFACE, COMPANY_TRADE_CLASS_INTERFACE);
			interfaceNameMap.put(COMPANY_TRADE_CLASS_INTERFACE, COMPANY_TRADE_CLASS_INTERFACE);
			interfaceNameMap.put(COMPANY_IDENTIFIER_INTERFACE_HISTORY, COMPANY_IDENTIFIER_INTERFACE);
			interfaceNameMap.put(COMPANY_IDENTIFIER_INTERFACE, COMPANY_IDENTIFIER_INTERFACE);			
			interfaceNameMap.put(ITEM_MASTER_INTERFACE, ITEM_MASTER_INTERFACE);
			interfaceNameMap.put(ITEM_MASTER_INTERFACE_HISTORY, ITEM_MASTER_INTERFACE);
			interfaceNameMap.put(ITEM_PRICING_INTERFACE, ITEM_PRICING_INTERFACE);
			interfaceNameMap.put(ITEM_PRICING_INTERFACE_HISTORY, ITEM_PRICING_INTERFACE);
			interfaceNameMap.put(ITEM_IDENTIFIER_INTERFACE_HISTORY, ITEM_IDENTIFIER_INTERFACE);
			interfaceNameMap.put(ITEM_IDENTIFIER_INTERFACE, ITEM_IDENTIFIER_INTERFACE);	
			interfaceNameMap.put(CONTRACT_HEADER_INTERFACE_HISTORY, CONTRACT_HEADER_INTERFACE);
			interfaceNameMap.put(CONTRACT_HEADER_INTERFACE,CONTRACT_HEADER_INTERFACE);			
			interfaceNameMap.put(COMPANY_FAMILYPLAN_INTERFACE_HISTORY, COMPANY_FAMILY_PLAN_INTERFACE);
			interfaceNameMap.put(COMPANY_FAMILY_PLAN_INTERFACE,COMPANY_FAMILY_PLAN_INTERFACE);			
			interfaceNameMap.put(ITEM_FAMILY_PLAN_INTERFACE_HISTORY, ITEM_FAMILY_PLAN_INTERFACE);
			interfaceNameMap.put(ITEM_FAMILY_PLAN_INTERFACE,ITEM_FAMILY_PLAN_INTERFACE);			
			interfaceNameMap.put(PRICE_SCHEDULE_INTERFACE_HISTORY, PRICE_SCHEDULE_INTERFACE);
			interfaceNameMap.put(PRICE_SCHEDULE_INTERFACE,PRICE_SCHEDULE_INTERFACE);			
			interfaceNameMap.put(REBATE_PLAN_INTERFACE_HISTORY, REBATE_PLAN_INTERFACE);
			interfaceNameMap.put(REBATE_PLAN_INTERFACE,REBATE_PLAN_INTERFACE);			
			interfaceNameMap.put(REBATE_SCHEDULE_INTERFACE_HISTORY, REBATE_SCHEDULE_INTERFACE);
			interfaceNameMap.put(REBATE_SCHEDULE_INTERFACE,REBATE_SCHEDULE_INTERFACE);
			interfaceNameMap.put(ACTUAL_MASTER_INTERFACE_HISTORY, ACTUAL_MASTER_INTERFACE);
			interfaceNameMap.put(ACTUAL_MASTER_INTERFACE,ACTUAL_MASTER_INTERFACE);			
			interfaceNameMap.put(SALES_MASTER_INTERFACE_HISTORY, SALES_MASTER_INTERFACE);
			interfaceNameMap.put(SALES_MASTER_INTERFACE,SALES_MASTER_INTERFACE);			
			interfaceNameMap.put(ITEM_HIERARCHY_INTERFACE_HISTORY, ITEM_HIERARCHY_INTERFACE);
			interfaceNameMap.put(ITEM_HIERARCHY_INTERFACE,ITEM_HIERARCHY_INTERFACE);			
			interfaceNameMap.put(ITEM_HIERARCHY_DEF_INTERFACE_HISTORY, ITEM_HIERARCHY_DEFINATION_INTERFACE);
			interfaceNameMap.put(ITEM_HIERARCHY_DEF_INTERFACE, ITEM_HIERARCHY_DEFINATION_INTERFACE);			
			interfaceNameMap.put(FORECASTING_MASTER_INTERFACE_HISTORY, FORECAST_SALES_INTERFACE);
			interfaceNameMap.put(FORECASTING_MASTER_INTERFACE, FORECAST_SALES_INTERFACE);			
			interfaceNameMap.put(GL_BALANCE_INTERFACE_HISTORY, GL_BALANCE_INTERFACE);
			interfaceNameMap.put(GL_BALANCE_INTERFACE,GL_BALANCE_INTERFACE);			
			interfaceNameMap.put(CPI_INDEX_INTERFACE_HISTORY, CPI_INTERFACE);
			interfaceNameMap.put(CPI_INTERFACE,CPI_INTERFACE);			
			interfaceNameMap.put(FORMULA_DETAILS_INTERFACE_HISTORY, FORMULA_DETAILS_INTERFACE);
			interfaceNameMap.put(FORMULA_DETAILS_INTERFACE,FORMULA_DETAILS_INTERFACE);			
			interfaceNameMap.put(MASTER_DATA_INTERFACE_HISTORY, MASTER_DATA_ATTRIBUTE_INTERFACE);
			interfaceNameMap.put(MASTER_DATA_INTERFACE, MASTER_DATA_ATTRIBUTE_INTERFACE);			
			interfaceNameMap.put(BEST_PRICE_INTERFACE_HISTORY, BEST_PRICE_INTERFACE);
			interfaceNameMap.put(BEST_PRICE_INTERFACE,BEST_PRICE_INTERFACE);			
			interfaceNameMap.put(AVERAGE_SHELF_LIFE_INTERFACE_HISTORY, AVERAGE_SHELF_LIFE_INTERFACE);
			interfaceNameMap.put(AVERAGE_SHELF_LIFE_INTERFACE,AVERAGE_SHELF_LIFE_INTERFACE);			
			interfaceNameMap.put(GL_COST_CENTER_INTERFACE_HISTORY, GL_COST_CENTER_INTERFACE);
			interfaceNameMap.put(GL_COST_CENTER_INTERFACE,GL_COST_CENTER_INTERFACE);			
			interfaceNameMap.put(LOT_MASTER_INTERFACE_HISTORY, LOT_MASTER_INTERFACE);
			interfaceNameMap.put(LOT_MASTER_INTERFACE,LOT_MASTER_INTERFACE);
			interfaceNameMap.put(ACCRUAL_MASTER_INBOUND, ACCRUAL_INBOUND_INTERFACE);
			interfaceNameMap.put(GL_POSTING_INTERFACE, GL_POSTING_INTERFACE);
			interfaceNameMap.put(INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF, INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF);			
			interfaceNameMap.put(INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER, INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER);			
			interfaceNameMap.put(INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT, INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT);			
			interfaceNameMap.put(INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN, INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN);
			interfaceNameMap.put(DEMAND_ACTUAL_INTERFACE, DEMAND_ACTUAL_INTERFACE);			
			interfaceNameMap.put(DEMAND_FORECAST_INTERFACE, DEMAND_FORECAST_INTERFACE);	
			interfaceNameMap.put(RETURNS_INTERFACE, RETURNS_INTERFACE);	
			interfaceNameMap.put(CUSTOMER_GTS_ACTUAL_INTERFACE, CUSTOMER_GTS_ACTUAL_INTERFACE);			
			interfaceNameMap.put(CUSTOMER_GTS_FORECAST_INTERFACE, CUSTOMER_GTS_FORECAST_INTERFACE);	
			interfaceNameMap.put(ADJUSTED_DEMAND_ACTUAL_INTERFACE, ADJUSTED_DEMAND_ACTUAL_INTERFACE);			
			interfaceNameMap.put(ADJUSTED_DEMAND_FORECAST_INTERFACE, ADJUSTED_DEMAND_FORECAST_INTERFACE);
			interfaceNameMap.put(RETURN_RESERVE_INTERFACE, RETURN_RESERVE_INTERFACE);
			interfaceNameMap.put(ITEM_UOM_INTERFACE, ITEM_UOM_INTERFACE);
		}
	return interfaceNameMap;
	}
}
