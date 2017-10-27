package com.stpl.app.gtnutilities.util;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Karthik.Raja
 */
public class FileNameUtil {

    private static final Map<String, String> map = new HashMap<String, String>();
    private static final Map<String, String> interfaceNameMap = new HashMap<String, String>();

    public static Map<String, String> getMap() {
        if (map.isEmpty()) {
            map.put(Constants.COMPANY_MASTER_INTERFACE, Constants.COMPANY_MASTER_INTERFACE);
            map.put("COMPANY_MASTER_INTERFACE_HISTORY", Constants.COMPANY_MASTER_INTERFACE);
            map.put("COMPANY_PARENT_HISTORY_INTERFACE", Constants.COMPANY_PARENT_INTERFACE);
            map.put(Constants.COMPANY_PARENT_INTERFACE, Constants.COMPANY_PARENT_INTERFACE);
            map.put("COMPANY_TRADE_CLASS_HISTORY_INTERFACE", Constants.COMPANY_TRADE_CLASS_INTERFACE);
            map.put(Constants.COMPANY_TRADE_CLASS_INTERFACE, Constants.COMPANY_TRADE_CLASS_INTERFACE);
            map.put("COMPANY_IDENTIFIER_INTERFACE_HISTORY", Constants.COMPANY_IDENTIFIER_INTERFACE);
            map.put(Constants.COMPANY_IDENTIFIER_INTERFACE, Constants.COMPANY_IDENTIFIER_INTERFACE);
            map.put(Constants.ITEM_MASTER_INTERFACE, Constants.ITEM_MASTER_INTERFACE);
            map.put("ITEM_MASTER_INTERFACE_HISTORY", Constants.ITEM_MASTER_INTERFACE);
            map.put(Constants.ITEM_PRICING_INTERFACE, Constants.ITEM_PRICING_INTERFACE);
            map.put("ITEM_PRICING_INTERFACE_HISTORY", Constants.ITEM_PRICING_INTERFACE);
            map.put("ITEM_IDENTIFIER_INTERFACE_HISTORY", Constants.ITEM_IDENTIFIER_INTERFACE);
            map.put(Constants.ITEM_IDENTIFIER_INTERFACE, Constants.ITEM_IDENTIFIER_INTERFACE);



            map.put("CONTRACT_HEADER_INTERFACE_HISTORY", Constants.CONTRACT_HEADER_INTERFACE);
            map.put(Constants.CONTRACT_HEADER_INTERFACE, Constants.CONTRACT_HEADER_INTERFACE);
            map.put("COMPANY_FAMILYPLAN_INTERFACE_HISTORY", Constants.COMPANY_FAMILY_PLAN_INTERFACE);
            map.put("COMPANY_FAMILYPLAN_INTERFACE", Constants.COMPANY_FAMILY_PLAN_INTERFACE);
            map.put("ITEM_FAMILY_PLAN_INTERFACE_HISTORY", Constants.ITEM_FAMILY_PLAN_INTERFACE);
            map.put(Constants.ITEM_FAMILY_PLAN_INTERFACE, Constants.ITEM_FAMILY_PLAN_INTERFACE);
            map.put("PRICE_SCHEDULE_INTERFACE_HISTORY", Constants.PRICE_SCHEDULE_INTERFACE);
            map.put(Constants.PRICE_SCHEDULE_INTERFACE, Constants.PRICE_SCHEDULE_INTERFACE);
            map.put("REBATE_PLAN_INTERFACE_HISTORY", Constants.REBATE_PLAN_INTERFACE);
            map.put(Constants.REBATE_PLAN_INTERFACE, Constants.REBATE_PLAN_INTERFACE);
            map.put("REBATE_SCHEDULE_INTERFACE_HISTORY", Constants.REBATE_SCHEDULE_INTERFACE);
            map.put(Constants.REBATE_SCHEDULE_INTERFACE, Constants.REBATE_SCHEDULE_INTERFACE);


            map.put("ACTUAL_MASTER_INTERFACE_HISTORY", Constants.ACTUAL_MASTER_INTERFACE);
            map.put(Constants.ACTUAL_MASTER_INTERFACE, Constants.ACTUAL_MASTER_INTERFACE);
            map.put("SALES_MASTER_INTERFACE_HISTORY", Constants.SALES_MASTER_INTERFACE);
            map.put(Constants.SALES_MASTER_INTERFACE, Constants.SALES_MASTER_INTERFACE);
            map.put("ITEM_HIERARCHY_INTERFACE_HISTORY", Constants.ITEM_HIERARCHY_INTERFACE);
            map.put(Constants.ITEM_HIERARCHY_INTERFACE, Constants.ITEM_HIERARCHY_INTERFACE);
            map.put("ITEM_HIERARCHY_DEF_INTERFACE_HISTORY", Constants.ITEM_HIERARCHY_DEFINATION_INTERFACE);
            map.put("ITEM_HIERARCHY_DEF_INTERFACE", Constants.ITEM_HIERARCHY_DEFINATION_INTERFACE);
            map.put("FORECASTING_MASTER_INTERFACE_HISTORY", Constants.FORECAST_SALES_INTERFACE);
            map.put("FORECASTING_MASTER_INTERFACE", Constants.FORECAST_SALES_INTERFACE);
            map.put("GL_BALANCE_INTERFACE_HISTORY", Constants.GL_BALANCE_INTERFACE);
            map.put(Constants.GL_BALANCE_INTERFACE, Constants.GL_BALANCE_INTERFACE);
            map.put("CPI_INDEX_INTERFACE_HISTORY", Constants.CPI_INTERFACE);
            map.put("CPI_INDEX_INTERFACE", Constants.CPI_INTERFACE);
            map.put("FORMULA_DETAILS_INTERFACE_HISTORY", Constants.FORMULA_DETAILS_INTERFACE);
            map.put(Constants.FORMULA_DETAILS_INTERFACE, Constants.FORMULA_DETAILS_INTERFACE);
            map.put("MASTER_DATA_INTERFACE_HISTORY", Constants.MASTER_DATA_ATTRIBUTE_INTERFACE);
            map.put("MASTER_DATA_INTERFACE", Constants.MASTER_DATA_ATTRIBUTE_INTERFACE);
            map.put("BEST_PRICE_INTERFACE_HISTORY", Constants.BEST_PRICE_INTERFACE);
            map.put(Constants.BEST_PRICE_INTERFACE, Constants.BEST_PRICE_INTERFACE);
            map.put("AVERAGE_SHELF_LIFE_INTERFACE_HISTORY", Constants.AVERAGE_SHELF_LIFE_INTERFACE);
            map.put(Constants.AVERAGE_SHELF_LIFE_INTERFACE, Constants.AVERAGE_SHELF_LIFE_INTERFACE);
            map.put("GL_COST_CENTER_INTERFACE_HISTORY", Constants.GL_COST_CENTER_INTERFACE);
            map.put(Constants.GL_COST_CENTER_INTERFACE, Constants.GL_COST_CENTER_INTERFACE);
            map.put("LOT_MASTER_INTERFACE_HISTORY", Constants.LOT_MASTER_INTERFACE);
            map.put(Constants.LOT_MASTER_INTERFACE, Constants.LOT_MASTER_INTERFACE);

            map.put("ACCRUAL_MASTER_INBOUND", "ACCRUAL_INBOUND_INTERFACE");

            //map.put("ACCRUAL_DETAIL_OUTBOUND", "LOT_MASTER_INTERFACE");

            map.put(Constants.GL_POSTING_INTERFACE, Constants.GL_POSTING_INTERFACE);

            map.put(Constants.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF, Constants.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF);
            map.put(Constants.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER, Constants.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER);
            map.put(Constants.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT, Constants.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT);
            map.put(Constants.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN, Constants.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN);

            map.put(Constants.DEMAND_ACTUAL_INTERFACE, Constants.DEMAND_ACTUAL_INTERFACE);
            map.put(Constants.DEMAND_FORECAST_INTERFACE, Constants.DEMAND_FORECAST_INTERFACE);

            map.put(Constants.RETURNS_INTERFACE, Constants.RETURNS_INTERFACE);

            map.put(Constants.CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.CUSTOMER_GTS_ACTUAL_INTERFACE);
            map.put(Constants.CUSTOMER_GTS_FORECAST_INTERFACE, Constants.CUSTOMER_GTS_FORECAST_INTERFACE);

            map.put(Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE, Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE);
            map.put(Constants.ADJUSTED_DEMAND_FORECAST_INTERFACE, Constants.ADJUSTED_DEMAND_FORECAST_INTERFACE);
            map.put(Constants.RETURN_RESERVE_INTERFACE, Constants.RETURN_RESERVE_INTERFACE);

        }

        return map;
    }

    public static Map<String, String> getInterfaceName() {
        if (interfaceNameMap.isEmpty()) {
            interfaceNameMap.put(Constants.COMPANY_MASTER_INTERFACE, Constants.COMPANY_MASTER_INTERFACE);
            interfaceNameMap.put("COMPANY_MASTER_INTERFACE_HISTORY", Constants.COMPANY_MASTER_INTERFACE);
            interfaceNameMap.put("COMPANY_PARENT_HISTORY_INTERFACE", Constants.COMPANY_PARENT_INTERFACE);
            interfaceNameMap.put(Constants.COMPANY_PARENT_INTERFACE, Constants.COMPANY_PARENT_INTERFACE);
            interfaceNameMap.put("COMPANY_TRADE_CLASS_HISTORY_INTERFACE", Constants.COMPANY_TRADE_CLASS_INTERFACE);
            interfaceNameMap.put(Constants.COMPANY_TRADE_CLASS_INTERFACE, Constants.COMPANY_TRADE_CLASS_INTERFACE);
            interfaceNameMap.put("COMPANY_IDENTIFIER_INTERFACE_HISTORY", Constants.COMPANY_IDENTIFIER_INTERFACE);
            interfaceNameMap.put(Constants.COMPANY_IDENTIFIER_INTERFACE, Constants.COMPANY_IDENTIFIER_INTERFACE);
            interfaceNameMap.put(Constants.ITEM_MASTER_INTERFACE, Constants.ITEM_MASTER_INTERFACE);
            interfaceNameMap.put("ITEM_MASTER_INTERFACE_HISTORY", Constants.ITEM_MASTER_INTERFACE);
            interfaceNameMap.put(Constants.ITEM_PRICING_INTERFACE, Constants.ITEM_PRICING_INTERFACE);
            interfaceNameMap.put("ITEM_PRICING_INTERFACE_HISTORY", Constants.ITEM_PRICING_INTERFACE);
            interfaceNameMap.put("ITEM_IDENTIFIER_INTERFACE_HISTORY", Constants.ITEM_IDENTIFIER_INTERFACE);
            interfaceNameMap.put(Constants.ITEM_IDENTIFIER_INTERFACE, Constants.ITEM_IDENTIFIER_INTERFACE);



            interfaceNameMap.put("CONTRACT_HEADER_INTERFACE_HISTORY", Constants.CONTRACT_HEADER_INTERFACE);
            interfaceNameMap.put(Constants.CONTRACT_HEADER_INTERFACE, Constants.CONTRACT_HEADER_INTERFACE);
            interfaceNameMap.put("COMPANY_FAMILYPLAN_INTERFACE_HISTORY", Constants.COMPANY_FAMILY_PLAN_INTERFACE);
            interfaceNameMap.put("COMPANY_FAMILYPLAN_INTERFACE", Constants.COMPANY_FAMILY_PLAN_INTERFACE);
            interfaceNameMap.put("ITEM_FAMILY_PLAN_INTERFACE_HISTORY", Constants.ITEM_FAMILY_PLAN_INTERFACE);
            interfaceNameMap.put(Constants.ITEM_FAMILY_PLAN_INTERFACE, Constants.ITEM_FAMILY_PLAN_INTERFACE);
            interfaceNameMap.put("PRICE_SCHEDULE_INTERFACE_HISTORY", Constants.PRICE_SCHEDULE_INTERFACE);
            interfaceNameMap.put(Constants.PRICE_SCHEDULE_INTERFACE, Constants.PRICE_SCHEDULE_INTERFACE);
            interfaceNameMap.put("REBATE_PLAN_INTERFACE_HISTORY", Constants.REBATE_PLAN_INTERFACE);
            interfaceNameMap.put(Constants.REBATE_PLAN_INTERFACE, Constants.REBATE_PLAN_INTERFACE);
            interfaceNameMap.put("REBATE_SCHEDULE_INTERFACE_HISTORY", Constants.REBATE_SCHEDULE_INTERFACE);
            interfaceNameMap.put(Constants.REBATE_SCHEDULE_INTERFACE, Constants.REBATE_SCHEDULE_INTERFACE);


            interfaceNameMap.put("ACTUAL_MASTER_INTERFACE_HISTORY", Constants.ACTUAL_MASTER_INTERFACE);
            interfaceNameMap.put(Constants.ACTUAL_MASTER_INTERFACE, Constants.ACTUAL_MASTER_INTERFACE);
            interfaceNameMap.put("SALES_MASTER_INTERFACE_HISTORY", Constants.SALES_MASTER_INTERFACE);
            interfaceNameMap.put(Constants.SALES_MASTER_INTERFACE, Constants.SALES_MASTER_INTERFACE);
            interfaceNameMap.put("ITEM_HIERARCHY_INTERFACE_HISTORY", Constants.ITEM_HIERARCHY_INTERFACE);
            interfaceNameMap.put(Constants.ITEM_HIERARCHY_INTERFACE, Constants.ITEM_HIERARCHY_INTERFACE);
            interfaceNameMap.put("ITEM_HIERARCHY_DEF_INTERFACE_HISTORY", Constants.ITEM_HIERARCHY_DEFINATION_INTERFACE);
            interfaceNameMap.put("ITEM_HIERARCHY_DEF_INTERFACE", Constants.ITEM_HIERARCHY_DEFINATION_INTERFACE);
            interfaceNameMap.put("FORECASTING_MASTER_INTERFACE_HISTORY", Constants.FORECAST_SALES_INTERFACE);
            interfaceNameMap.put("FORECASTING_MASTER_INTERFACE", Constants.FORECAST_SALES_INTERFACE);
            interfaceNameMap.put("GL_BALANCE_INTERFACE_HISTORY", Constants.GL_BALANCE_INTERFACE);
            interfaceNameMap.put(Constants.GL_BALANCE_INTERFACE, Constants.GL_BALANCE_INTERFACE);
            interfaceNameMap.put("CPI_INDEX_INTERFACE_HISTORY", Constants.CPI_INTERFACE);
            interfaceNameMap.put("CPI_INDEX_INTERFACE", Constants.CPI_INTERFACE);
            interfaceNameMap.put("FORMULA_DETAILS_INTERFACE_HISTORY", Constants.FORMULA_DETAILS_INTERFACE);
            interfaceNameMap.put(Constants.FORMULA_DETAILS_INTERFACE, Constants.FORMULA_DETAILS_INTERFACE);
            interfaceNameMap.put("MASTER_DATA_INTERFACE_HISTORY", Constants.MASTER_DATA_ATTRIBUTE_INTERFACE);
            interfaceNameMap.put("MASTER_DATA_INTERFACE", Constants.MASTER_DATA_ATTRIBUTE_INTERFACE);
            interfaceNameMap.put("BEST_PRICE_INTERFACE_HISTORY", Constants.BEST_PRICE_INTERFACE);
            interfaceNameMap.put(Constants.BEST_PRICE_INTERFACE, Constants.BEST_PRICE_INTERFACE);
            interfaceNameMap.put("AVERAGE_SHELF_LIFE_INTERFACE_HISTORY", Constants.AVERAGE_SHELF_LIFE_INTERFACE);
            interfaceNameMap.put(Constants.AVERAGE_SHELF_LIFE_INTERFACE, Constants.AVERAGE_SHELF_LIFE_INTERFACE);
            interfaceNameMap.put("GL_COST_CENTER_INTERFACE_HISTORY", Constants.GL_COST_CENTER_INTERFACE);
            interfaceNameMap.put(Constants.GL_COST_CENTER_INTERFACE, Constants.GL_COST_CENTER_INTERFACE);
            interfaceNameMap.put("LOT_MASTER_INTERFACE_HISTORY", Constants.LOT_MASTER_INTERFACE);
            interfaceNameMap.put(Constants.LOT_MASTER_INTERFACE, Constants.LOT_MASTER_INTERFACE);

            interfaceNameMap.put("ACCRUAL_MASTER_INBOUND", "ACCRUAL_INBOUND_INTERFACE");

            //interfaceNameMap.put("ACCRUAL_DETAIL_OUTBOUND", "LOT_MASTER_INTERFACE");

            interfaceNameMap.put(Constants.GL_POSTING_INTERFACE, Constants.GL_POSTING_INTERFACE);

            interfaceNameMap.put(Constants.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF, Constants.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERF);
            interfaceNameMap.put(Constants.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER, Constants.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTER);
            interfaceNameMap.put(Constants.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT, Constants.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INT);
            interfaceNameMap.put(Constants.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN, Constants.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_IN);

            interfaceNameMap.put(Constants.DEMAND_ACTUAL_INTERFACE, Constants.DEMAND_ACTUAL_INTERFACE);
            interfaceNameMap.put(Constants.DEMAND_FORECAST_INTERFACE, Constants.DEMAND_FORECAST_INTERFACE);

            interfaceNameMap.put(Constants.RETURNS_INTERFACE, Constants.RETURNS_INTERFACE);

            interfaceNameMap.put(Constants.CUSTOMER_GTS_ACTUAL_INTERFACE, Constants.CUSTOMER_GTS_ACTUAL_INTERFACE);
            interfaceNameMap.put(Constants.CUSTOMER_GTS_FORECAST_INTERFACE, Constants.CUSTOMER_GTS_FORECAST_INTERFACE);

            interfaceNameMap.put(Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE, Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE);
            interfaceNameMap.put(Constants.ADJUSTED_DEMAND_FORECAST_INTERFACE, Constants.ADJUSTED_DEMAND_FORECAST_INTERFACE);
            interfaceNameMap.put(Constants.RETURN_RESERVE_INTERFACE, Constants.RETURN_RESERVE_INTERFACE);
        }

        return interfaceNameMap;
    }
}

