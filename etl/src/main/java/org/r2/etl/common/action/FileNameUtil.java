package org.r2.etl.common.action;

import java.util.HashMap;
import java.util.Map;
import org.r2.etl.common.util.Constants;

public class FileNameUtil {
	private FileNameUtil() {
	}

	private static final Map<String, String> map = new HashMap<>();
	private static final Map<String, String> interfaceNameMap = new HashMap<>();

	public static Map<String, String> getMap() {
		if (map.isEmpty()) {
			map.put(Constants.COMPANY_MASTER, Constants.COMPANY_MASTER);
			map.put(Constants.COM_MASTER_INTF, Constants.COMPANY_MASTER);
			map.put(Constants.COMPANY_PARENT_HIS, Constants.COMPANY_PARENT);
			map.put(Constants.COMPANY_PARENT, Constants.COMPANY_PARENT);
			map.put(Constants.COMPANY_TRADE_HIS, Constants.COMPANY_TRADE_HIS);
			map.put(Constants.COMPANY_TRADE, Constants.COMPANY_TRADE);
			map.put(COMPANY_IDENTIFIER_INTERFACE_HISTORY, Constants.COMPANY_IDENT);
			map.put(Constants.COMPANY_IDENT, Constants.COMPANY_IDENT);
			map.put(ITEM_MASTER_INTERFACE, ITEM_MASTER_INTERFACE);
			map.put(ITEM_MASTER_INTERFACE_HISTORY, ITEM_MASTER_INTERFACE);
			map.put(ITEM_PRICING_INTERFACE, ITEM_PRICING_INTERFACE);
			map.put(ITEM_PRICING_INTERFACE_HISTORY, ITEM_PRICING_INTERFACE);
			map.put(ITEM_IDENTIFIER_INTERFACE_HISTORY, ITEM_IDENTIFIER_INTERFACE);
			map.put(ITEM_IDENTIFIER_INTERFACE, ITEM_IDENTIFIER_INTERFACE);
			map.put(CONTRACT_HEADER_INTERFACE_HISTORY, Constants.CONTRACT_HEADER);
			map.put(Constants.CONTRACT_HEADER, Constants.CONTRACT_HEADER);
			map.put(COMPANY_FAMILYPLAN_INTERFACE_HISTORY, Constants.CFP);
			map.put(Constants.CFP, Constants.CFP);
			map.put(ITEM_FAMILY_PLAN_INTERFACE_HISTORY, Constants.ITEM_FAMILYPLAN);
			map.put(Constants.ITEM_FAMILYPLAN, Constants.ITEM_FAMILYPLAN);
			map.put(PRICE_SCHEDULE_INTERFACE_HISTORY, Constants.PRICE_SCHEDULE);
			map.put(Constants.PRICE_SCHEDULE, Constants.PRICE_SCHEDULE);
			map.put(REBATE_PLAN_INTERFACE_HISTORY, Constants.REBATE_PLAN);
			map.put(Constants.REBATE_PLAN, Constants.REBATE_PLAN);
			map.put(REBATE_SCHEDULE_INTERFACE_HISTORY, Constants.REBATE_SCHEDULE);
			map.put(Constants.REBATE_SCHEDULE, Constants.REBATE_SCHEDULE);
			map.put(ACTUAL_MASTER_INTERFACE_HISTORY, Constants.ACTUAL_MASTER);
			map.put(Constants.ACTUAL_MASTER, Constants.ACTUAL_MASTER);
			map.put(SALES_MASTER_INTERFACE_HISTORY, Constants.SALES_MASTER);
			map.put(Constants.SALES_MASTER, Constants.SALES_MASTER);
			map.put(ITEM_HIERARCHY_INTERFACE_HISTORY, Constants.ITEM_HIERARCHY);
			map.put(Constants.ITEM_HIERARCHY, Constants.ITEM_HIERARCHY);
			map.put(ITEM_HIERARCHY_DEF_INTERFACE_HISTORY, Constants.ITEM_HIE_DEF);
			map.put(Constants.ITEM_HIE_DEF, Constants.ITEM_HIE_DEF);
			map.put(FORECASTING_MASTER_INTERFACE_HISTORY, Constants.FORE_MASTER);
			map.put(Constants.FORE_MASTER, Constants.FORE_MASTER);
			map.put(GL_BALANCE_INTERFACE_HISTORY, Constants.GL_BALANCE);
			map.put(Constants.GL_BALANCE, Constants.GL_BALANCE);
			map.put(CPI_INDEX_INTERFACE_HISTORY, Constants.CPI_INDEX);
			map.put(Constants.CPI_INDEX, Constants.CPI_INDEX);
			map.put(FORMULA_DETAILS_INTERFACE_HISTORY, Constants.FORMULA_DETAILS);
			map.put(Constants.FORMULA_DETAILS, Constants.FORMULA_DETAILS);
			map.put(MASTER_DATA_INTERFACE_HISTORY, MASTER_DATA_ATTRIBUTE_INTERFACE);
			map.put(Constants.MASTER_DATA, Constants.MASTER_DATA);
			map.put(BEST_PRICE_INTERFACE_HISTORY, Constants.BEST_PRICE);
			map.put(Constants.BEST_PRICE, Constants.BEST_PRICE);
			map.put(AVERAGE_SHELF_LIFE_INTERFACE_HISTORY, Constants.AVERAGE_SHELF);
			map.put(Constants.AVERAGE_SHELF, Constants.AVERAGE_SHELF);
			map.put(GL_COST_CENTER_INTERFACE_HISTORY, Constants.GL_COST_CENTER);
			map.put(Constants.GL_COST_CENTER, Constants.GL_COST_CENTER);
			map.put(LOT_MASTER_INTERFACE_HISTORY, Constants.LOT_MASTER);
			map.put(Constants.LOT_MASTER, Constants.LOT_MASTER);
			map.put(Constants.ACCRUAL_MASTER, Constants.ACCRUAL_MASTER);
			map.put(Constants.GL_POSTING, Constants.GL_POSTING);
			map.put(Constants.INVENTORY_ACTUAL_DETAIL, Constants.INVENTORY_ACTUAL_DETAIL);
			map.put(Constants.INVENTORY_ACTUAL_SUMMARY, Constants.INVENTORY_ACTUAL_SUMMARY);
			map.put(Constants.INVENTORY_PROJECTED_DETAIL, Constants.INVENTORY_PROJECTED_DETAIL);
			map.put(Constants.INVENTORY_PROJECTED_SUMMARY, Constants.INVENTORY_PROJECTED_SUMMARY);
			map.put(Constants.DEMAND_ACTUAL, Constants.DEMAND_ACTUAL);
			map.put(Constants.DEMAND_FORECAST, Constants.DEMAND_FORECAST);
			map.put(Constants.RETURNS_INTERFACE, Constants.RETURNS_INTERFACE);
			map.put(Constants.CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.CUSTOMER_GTS_ACTUAL_INTERFACE);
			map.put(Constants.CUSTOMER_GTS_FORECAST_INTERFACE, Constants.CUSTOMER_GTS_FORECAST_INTERFACE);
			map.put(Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE, Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE);
			map.put(Constants.ADJUSTED_DEMAND_FORECASTINTERFACE, Constants.ADJUSTED_DEMAND_FORECASTINTERFACE);
			map.put(Constants.RETURN_RESERVE_INTERFACE, Constants.RETURN_RESERVE_INTERFACE);
			map.put(Constants.ITEM_UOM_INTERFACE, Constants.ITEM_UOM_INTERFACE);
			map.put(Constants.RETURN_RATE_FORECAST_INTERFACE, Constants.RETURN_RATE_FORECAST_INTERFACE);
			map.put(Constants.TESTING_INTERFACE, Constants.TESTING_INTERFACE);
			map.put(Constants.ACTUALS_CHARGEBACK_INTERFACE, Constants.ACTUALS_CHARGEBACK_INTERFACE);
			map.put(Constants.ACTUALS_MEDICAID_INTERFACE, Constants.ACTUALS_MEDICAID_INTERFACE);
			map.put(Constants.ACTUALS_REBATE_INTERFACE, Constants.ACTUALS_REBATE_INTERFACE);
			map.put(Constants.ACTUALS_COUPON_INTERFACE, Constants.ACTUALS_COUPON_INTERFACE);
			map.put(Constants.PRODUCT_PROD_HIERARCHY_INTERFACE, Constants.PRODUCT_PROD_HIERARCHY_INTERFACE);
			map.put(Constants.PRODUCT_PROD_BW_INTERFACE, Constants.PRODUCT_PROD_BW_INTERFACE);
			map.put(Constants.COMPANY_BUNIT_INTERFACE, Constants.COMPANY_BUNIT_INTERFACE);
			map.put(Constants.COMPANY_BUID_INTERFACE, Constants.COMPANY_BUID_INTERFACE);
			map.put(Constants.COMPANY_BUTYPE_INTERFACE, Constants.COMPANY_BUTYPE_INTERFACE);
			map.put(Constants.PRODUCT_PRODID_INTERFACE, Constants.PRODUCT_PRODID_INTERFACE);
			map.put(Constants.PRODUCT_PROD_INTERFACE, Constants.PRODUCT_PROD_INTERFACE);
			map.put(Constants.PRICING_INTERFACE, Constants.PRICING_INTERFACE);
			map.put(Constants.CONTRACT_CONT_INTERFACE, Constants.CONTRACT_CONT_INTERFACE);
			map.put(Constants.CONTRACT_CPGRP_INTERFACE, Constants.CONTRACT_CPGRP_INTERFACE);
			map.put(Constants.CONTRACT_CPPPO_INTERFACE, Constants.CONTRACT_CPPPO_INTERFACE);
			map.put(Constants.CONTRACT_CPPT_INTERFACE, Constants.CONTRACT_CPPT_INTERFACE);
			map.put(Constants.CONTRACT_CPGRP_PGMMKT_INTERFACE, Constants.CONTRACT_CPGRP_PGMMKT_INTERFACE);
			map.put(Constants.CONTRACT_PRGMMKT_INTERFACE, Constants.CONTRACT_PRGMMKT_INTERFACE);
			map.put(Constants.PSTG_SALES_MASTER_INTERFACE, Constants.PSTG_SALES_MASTER_INTERFACE);
			map.put(Constants.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE);
			map.put(Constants.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE, Constants.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE);
			map.put(Constants.PSTG_RETURNS_INTERFACE, Constants.PSTG_RETURNS_INTERFACE);
			map.put(Constants.CONSUMER_PRICE_INDEX_INTERFACE, Constants.CONSUMER_PRICE_INDEX_INTERFACE);
			map.put(Constants.PSTG_GL_COST_CENTER_INTERFACE, Constants.PSTG_GL_COST_CENTER_INTERFACE);
			map.put(Constants.UNIT_OF_MEASURE_INTERFACE, Constants.UNIT_OF_MEASURE_INTERFACE);
			map.put(Constants.ACCRUALS_INTERFACE, Constants.ACCRUALS_INTERFACE);
			map.put(Constants.PSTG_SALES_FORECAST_INTERFACE, Constants.PSTG_SALES_FORECAST_INTERFACE);

			map.put(Constants.CONTRACT_CFP_MBR_INTERFACE, Constants.CONTRACT_CFP_MBR_INTERFACE);
			map.put(Constants.PSTG_FORECAST_SALES_WAC_INTERFACE, Constants.PSTG_FORECAST_SALES_WAC_INTERFACE);
			map.put(Constants.PSTG_GCC_PROD_HIERARCHY_INTERFACE, Constants.PSTG_GCC_PROD_HIERARCHY_INTERFACE);
                        
                        map.put(Constants.DTX_UOM_INTERFACE, Constants.DTX_UOM_INTERFACE);
                        map.put(Constants.DTX_CPI_INTERFACE, Constants.DTX_CPI_INTERFACE);
                        map.put(Constants.DTX_RETURN_RATE_INTERFACE, Constants.DTX_RETURN_RATE_INTERFACE);
                        map.put(Constants.DTX_ITEM_PRICING_INTERFACE, Constants.DTX_ITEM_PRICING_INTERFACE);
                        map.put(Constants.DTX_ACCRUAL_ACTUAL_INTERFACE, Constants.DTX_ACCRUAL_ACTUAL_INTERFACE);
                        map.put(Constants.DTX_ITEM_MASTER_INTERFACE, Constants.DTX_ITEM_MASTER_INTERFACE);
                        map.put(Constants.DTX_ITEM_IDENTIFIER_INTERFACE, Constants.DTX_ITEM_IDENTIFIER_INTERFACE);
                        map.put(Constants.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE);
                        map.put(Constants.DTX_CUSTOMER_GTS_FORECAST_INTERFACE, Constants.DTX_CUSTOMER_GTS_FORECAST_INTERFACE);
                        map.put(Constants.DTX_COMPANY_MASTER_INTERFACE, Constants.DTX_COMPANY_MASTER_INTERFACE);
                        map.put(Constants.DTX_COMPANY_TRADE_CLASS_INTERFACE, Constants.DTX_COMPANY_TRADE_CLASS_INTERFACE);

		}
		return map;
	}

	public static final String COMPANY_IDENTIFIER_INTERFACE_HISTORY = "COMPANY_IDENTIFIER_INTERFACE_HISTORY";
	public static final String COMPANY_FAMILYPLAN_INTERFACE_HISTORY = "COMPANY_FAMILYPLAN_INTERFACE_HISTORY";

	public static final String FORECASTING_MASTER_INTERFACE = "FORECASTING_MASTER_INTERFACE";
	public static final String LOT_MASTER_INTERFACE_HISTORY = "LOT_MASTER_INTERFACE_HISTORY";
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
	public static final String CONTRACT_HEADER_INTERFACE_HISTORY = "CONTRACT_HEADER_INTERFACE_HISTORY";
	public static final String MASTER_DATA_ATTRIBUTE_INTERFACE = "MASTER_DATA_ATTRIBUTE_INTERFACE";
	public static final String FORECAST_SALES_INTERFACE = "FORECAST_SALES_INTERFACE";
	public static final String ITEM_IDENTIFIER_INTERFACE_HISTORY = "ITEM_IDENTIFIER_INTERFACE_HISTORY";
	public static final String ITEM_IDENTIFIER_INTERFACE = "ITEM_IDENTIFIER_INTERFACE";
	public static final String ITEM_PRICING_INTERFACE_HISTORY = "ITEM_PRICING_INTERFACE_HISTORY";
	public static final String ITEM_PRICING_INTERFACE = "ITEM_PRICING_INTERFACE";
	public static final String ITEM_MASTER_INTERFACE_HISTORY = "ITEM_MASTER_INTERFACE_HISTORY";
	public static final String ITEM_MASTER_INTERFACE = "ITEM_MASTER_INTERFACE";
	public static final String COMPANY_TRADE_CLASS_HISTORY_INTERFACE = "COMPANY_TRADE_CLASS_HISTORY_INTERFACE";

	public static Map<String, String> getInterfaceName() {
		if (interfaceNameMap.isEmpty()) {
			interfaceNameMap.put(Constants.COMPANY_MASTER, Constants.COMPANY_MASTER);
			interfaceNameMap.put(Constants.COM_MASTER_INTF, Constants.COMPANY_MASTER);
			interfaceNameMap.put(Constants.COMPANY_PARENT_HIS, Constants.COMPANY_PARENT);
			interfaceNameMap.put(Constants.COMPANY_PARENT, Constants.COMPANY_PARENT);
			interfaceNameMap.put(Constants.COMPANY_TRADE_HIS, Constants.COMPANY_TRADE_HIS);
			interfaceNameMap.put(Constants.COMPANY_TRADE, Constants.COMPANY_TRADE);
			interfaceNameMap.put(COMPANY_IDENTIFIER_INTERFACE_HISTORY, Constants.COMPANY_IDENT);
			interfaceNameMap.put(Constants.COMPANY_IDENT, Constants.COMPANY_IDENT);
			interfaceNameMap.put(ITEM_MASTER_INTERFACE, ITEM_MASTER_INTERFACE);
			interfaceNameMap.put(ITEM_MASTER_INTERFACE_HISTORY, ITEM_MASTER_INTERFACE);
			interfaceNameMap.put(ITEM_PRICING_INTERFACE, ITEM_PRICING_INTERFACE);
			interfaceNameMap.put(ITEM_PRICING_INTERFACE_HISTORY, ITEM_PRICING_INTERFACE);
			interfaceNameMap.put(ITEM_IDENTIFIER_INTERFACE_HISTORY, ITEM_IDENTIFIER_INTERFACE);
			interfaceNameMap.put(ITEM_IDENTIFIER_INTERFACE, ITEM_IDENTIFIER_INTERFACE);
			interfaceNameMap.put(CONTRACT_HEADER_INTERFACE_HISTORY, Constants.CONTRACT_HEADER);
			interfaceNameMap.put(Constants.CONTRACT_HEADER, Constants.CONTRACT_HEADER);
			interfaceNameMap.put(COMPANY_FAMILYPLAN_INTERFACE_HISTORY, Constants.CFP);
			interfaceNameMap.put(Constants.CFP, Constants.CFP);
			interfaceNameMap.put(ITEM_FAMILY_PLAN_INTERFACE_HISTORY, Constants.ITEM_FAMILYPLAN);
			interfaceNameMap.put(Constants.ITEM_FAMILYPLAN, Constants.ITEM_FAMILYPLAN);
			interfaceNameMap.put(PRICE_SCHEDULE_INTERFACE_HISTORY, Constants.PRICE_SCHEDULE);
			interfaceNameMap.put(Constants.PRICE_SCHEDULE, Constants.PRICE_SCHEDULE);
			interfaceNameMap.put(REBATE_PLAN_INTERFACE_HISTORY, Constants.REBATE_PLAN);
			interfaceNameMap.put(Constants.REBATE_PLAN, Constants.REBATE_PLAN);
			interfaceNameMap.put(REBATE_SCHEDULE_INTERFACE_HISTORY, Constants.REBATE_SCHEDULE);
			interfaceNameMap.put(Constants.REBATE_SCHEDULE, Constants.REBATE_SCHEDULE);
			interfaceNameMap.put(ACTUAL_MASTER_INTERFACE_HISTORY, Constants.ACTUAL_MASTER);
			interfaceNameMap.put(Constants.ACTUAL_MASTER, Constants.ACTUAL_MASTER);
			interfaceNameMap.put(SALES_MASTER_INTERFACE_HISTORY, Constants.SALES_MASTER);
			interfaceNameMap.put(Constants.SALES_MASTER, Constants.SALES_MASTER);
			interfaceNameMap.put(ITEM_HIERARCHY_INTERFACE_HISTORY, Constants.ITEM_HIERARCHY);
			interfaceNameMap.put(Constants.ITEM_HIERARCHY, Constants.ITEM_HIERARCHY);
			interfaceNameMap.put(ITEM_HIERARCHY_DEF_INTERFACE_HISTORY, Constants.ITEM_HIE_DEF);
			interfaceNameMap.put(Constants.ITEM_HIE_DEF, Constants.ITEM_HIE_DEF);
			interfaceNameMap.put(FORECASTING_MASTER_INTERFACE_HISTORY, FORECAST_SALES_INTERFACE);
			interfaceNameMap.put(Constants.FORE_MASTER, Constants.FORE_MASTER);
			interfaceNameMap.put(GL_BALANCE_INTERFACE_HISTORY, Constants.GL_BALANCE);
			interfaceNameMap.put(Constants.GL_BALANCE, Constants.GL_BALANCE);
			interfaceNameMap.put(CPI_INDEX_INTERFACE_HISTORY, Constants.CPI_INDEX);
			interfaceNameMap.put(Constants.CPI_INDEX, Constants.CPI_INDEX);
			interfaceNameMap.put(FORMULA_DETAILS_INTERFACE_HISTORY, Constants.FORMULA_DET_INTF);
			interfaceNameMap.put(Constants.FORMULA_DET_INTF, Constants.FORMULA_DET_INTF);
			interfaceNameMap.put(MASTER_DATA_INTERFACE_HISTORY, MASTER_DATA_ATTRIBUTE_INTERFACE);
			interfaceNameMap.put(Constants.MASTER_DATA, Constants.MASTER_DATA);
			interfaceNameMap.put(BEST_PRICE_INTERFACE_HISTORY, Constants.BEST_PRICE);
			interfaceNameMap.put(Constants.BEST_PRICE, Constants.BEST_PRICE);
			interfaceNameMap.put(AVERAGE_SHELF_LIFE_INTERFACE_HISTORY, Constants.AVERAGE_SHELF);
			interfaceNameMap.put(Constants.AVERAGE_SHELF, Constants.AVERAGE_SHELF);
			interfaceNameMap.put(GL_COST_CENTER_INTERFACE_HISTORY, Constants.GL_COST_CENTER);
			interfaceNameMap.put(Constants.GL_COST_CENTER, Constants.GL_COST_CENTER);
			interfaceNameMap.put(LOT_MASTER_INTERFACE_HISTORY, Constants.LOT_MASTER);
			interfaceNameMap.put(Constants.LOT_MASTER, Constants.LOT_MASTER);
			interfaceNameMap.put(Constants.ACCRUAL_MASTER, Constants.ACCRUAL_MASTER);
			interfaceNameMap.put(Constants.GL_POSTING, Constants.GL_POSTING);
			interfaceNameMap.put(Constants.INVENTORY_ACTUAL_DETAIL, Constants.INVENTORY_ACTUAL_DETAIL);
			interfaceNameMap.put(Constants.INVENTORY_ACTUAL_SUMMARY, Constants.INVENTORY_ACTUAL_SUMMARY);
			interfaceNameMap.put(Constants.INVENTORY_PROJECTED_DETAIL, Constants.INVENTORY_PROJECTED_DETAIL);
			interfaceNameMap.put(Constants.INVENTORY_PROJECTED_SUMMARY, Constants.INVENTORY_PROJECTED_SUMMARY);
			interfaceNameMap.put(Constants.DEMAND_ACTUAL, Constants.DEMAND_ACTUAL);
			interfaceNameMap.put(Constants.DEMAND_FORECAST, Constants.DEMAND_FORECAST);
			interfaceNameMap.put(Constants.RETURNS_INTERFACE, Constants.RETURNS_INTERFACE);
			interfaceNameMap.put(Constants.CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.CUSTOMER_GTS_ACTUAL_INTERFACE);
			interfaceNameMap.put(Constants.CUSTOMER_GTS_FORECAST_INTERFACE, Constants.CUSTOMER_GTS_FORECAST_INTERFACE);
			interfaceNameMap.put(Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE,
					Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE);
			interfaceNameMap.put(Constants.ADJUSTED_DEMAND_FORECASTINTERFACE,
					Constants.ADJUSTED_DEMAND_FORECASTINTERFACE);
			interfaceNameMap.put(Constants.RETURN_RESERVE_INTERFACE, Constants.RETURN_RESERVE_INTERFACE);
			interfaceNameMap.put(Constants.ITEM_UOM_INTERFACE, Constants.ITEM_UOM_INTERFACE);
			interfaceNameMap.put(Constants.RETURN_RATE_FORECAST_INTERFACE, Constants.RETURN_RATE_FORECAST_INTERFACE);
			interfaceNameMap.put(Constants.TESTING_INTERFACE, Constants.TESTING_INTERFACE);

			interfaceNameMap.put(Constants.ACTUALS_CHARGEBACK_INTERFACE, Constants.ACTUALS_CHARGEBACK_INTERFACE);
			interfaceNameMap.put(Constants.ACTUALS_MEDICAID_INTERFACE, Constants.ACTUALS_MEDICAID_INTERFACE);
			interfaceNameMap.put(Constants.ACTUALS_REBATE_INTERFACE, Constants.ACTUALS_REBATE_INTERFACE);
			interfaceNameMap.put(Constants.ACTUALS_COUPON_INTERFACE, Constants.ACTUALS_COUPON_INTERFACE);
			interfaceNameMap.put(Constants.PRODUCT_PROD_HIERARCHY_INTERFACE,
					Constants.PRODUCT_PROD_HIERARCHY_INTERFACE);
			interfaceNameMap.put(Constants.PRODUCT_PROD_BW_INTERFACE, Constants.PRODUCT_PROD_BW_INTERFACE);

			interfaceNameMap.put(Constants.COMPANY_BUNIT_INTERFACE, Constants.COMPANY_BUNIT_INTERFACE);
			interfaceNameMap.put(Constants.COMPANY_BUID_INTERFACE, Constants.COMPANY_BUID_INTERFACE);
			interfaceNameMap.put(Constants.COMPANY_BUTYPE_INTERFACE, Constants.COMPANY_BUTYPE_INTERFACE);
			interfaceNameMap.put(Constants.PRODUCT_PRODID_INTERFACE, Constants.PRODUCT_PRODID_INTERFACE);
			interfaceNameMap.put(Constants.PRODUCT_PROD_INTERFACE, Constants.PRODUCT_PROD_INTERFACE);
			interfaceNameMap.put(Constants.PRICING_INTERFACE, Constants.PRICING_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CONT_INTERFACE, Constants.CONTRACT_CONT_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CPGRP_INTERFACE, Constants.CONTRACT_CPGRP_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CPPPO_INTERFACE, Constants.CONTRACT_CPPPO_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CPPT_INTERFACE, Constants.CONTRACT_CPPT_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CPGRP_PGMMKT_INTERFACE, Constants.CONTRACT_CPGRP_PGMMKT_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_PRGMMKT_INTERFACE, Constants.CONTRACT_PRGMMKT_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_SALES_MASTER_INTERFACE, Constants.PSTG_SALES_MASTER_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE,
					Constants.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE,
					Constants.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_RETURNS_INTERFACE, Constants.PSTG_RETURNS_INTERFACE);
			interfaceNameMap.put(Constants.CONSUMER_PRICE_INDEX_INTERFACE, Constants.CONSUMER_PRICE_INDEX_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_GL_COST_CENTER_INTERFACE, Constants.PSTG_GL_COST_CENTER_INTERFACE);
			interfaceNameMap.put(Constants.UNIT_OF_MEASURE_INTERFACE, Constants.UNIT_OF_MEASURE_INTERFACE);
			interfaceNameMap.put(Constants.ACCRUALS_INTERFACE, Constants.ACCRUALS_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_SALES_FORECAST_INTERFACE, Constants.PSTG_SALES_FORECAST_INTERFACE);
			interfaceNameMap.put(Constants.CONTRACT_CFP_MBR_INTERFACE, Constants.CONTRACT_CFP_MBR_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_FORECAST_SALES_WAC_INTERFACE,
					Constants.PSTG_FORECAST_SALES_WAC_INTERFACE);
			interfaceNameMap.put(Constants.PSTG_GCC_PROD_HIERARCHY_INTERFACE,
					Constants.PSTG_GCC_PROD_HIERARCHY_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_UOM_INTERFACE,
					Constants.DTX_UOM_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_CPI_INTERFACE,
					Constants.DTX_CPI_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_RETURN_RATE_INTERFACE,
					Constants.DTX_RETURN_RATE_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_ITEM_PRICING_INTERFACE,
					Constants.DTX_ITEM_PRICING_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_ACCRUAL_ACTUAL_INTERFACE,
					Constants.DTX_ACCRUAL_ACTUAL_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_ITEM_MASTER_INTERFACE,
            		Constants.DTX_ITEM_MASTER_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_ITEM_IDENTIFIER_INTERFACE,
                    Constants.DTX_ITEM_IDENTIFIER_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE,
                    Constants.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_CUSTOMER_GTS_FORECAST_INTERFACE,
                    Constants.DTX_CUSTOMER_GTS_FORECAST_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_COMPANY_MASTER_INTERFACE,
                    Constants.DTX_COMPANY_MASTER_INTERFACE);
                        interfaceNameMap.put(Constants.DTX_COMPANY_TRADE_CLASS_INTERFACE,
                    Constants.DTX_COMPANY_TRADE_CLASS_INTERFACE);
		}
		return interfaceNameMap;
	}
}
