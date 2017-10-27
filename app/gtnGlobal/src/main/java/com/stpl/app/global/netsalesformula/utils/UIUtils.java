/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.utils;

import com.stpl.app.util.ConstantsUtils;
import com.vaadin.ui.ComboBox;

/**
 *
 * @author karthikraja.k
 */
public class UIUtils {

   
    public static final Object[] AVAILABLE_CONTR_COL = new Object[]{ConstantsUtils.CONTRACT_NO , ConstantsUtils.CONTRACT_NAME, ConstantsUtils.CONTRACT_HOLDER , ConstantsUtils.MARKET_TYPE, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO,  ConstantsUtils.ITEM_FAMILY_PLAN_NAME, ConstantsUtils.PS_NO, ConstantsUtils.PS_NAME, ConstantsUtils.DEDUCTION_NO, ConstantsUtils.DEDUCTION_NAME};
   
    public static final String[] AVAILABLE_CONTR_HEADER = new String[]{ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, ConstantsUtils.CONTRACT_HOLDER_LABEL,ConstantsUtils.MARKET_TYPE_LABEL, ConstantsUtils.COMPANY_FAMILY_PLAN_NO,
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO_LABEL, ConstantsUtils.ITEM_FAMILY_PLAN_NAME_LABEL, ConstantsUtils.PRICE_SCHEDULE_NO, ConstantsUtils.PRICE_SCHEDULE_NAME, ConstantsUtils.DEDUCTION_NO_LABEL, ConstantsUtils.DEDUCTION_NAME_LABEL};
    
    public static final Object[] AVAILABLE_CUSTOMER_COL = new Object[]{ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.CONTRACT_NO, ConstantsUtils.CONTRACT_NAME, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME};
    
    public static final String[] AVAILABLE_CUSTOMER_HEADER = new String[]{ConstantsUtils.CUSTOMER_NO_LABEL, ConstantsUtils.CUSTOMER_NAME_LABEL, ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, ConstantsUtils.COMPANY_FAMILY_PLAN_NO,
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME};
    
    public static final Object[] SELECTED_CUSTOMER_COL = new Object[]{ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.CONTRACT_NO, ConstantsUtils.CONTRACT_NAME, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME, ConstantsUtils.NET_SALES_RULENO, ConstantsUtils.NET_SALES_RULE_NAME};
    
    public static final String[] SELECTED_CUSTOMER_HEADER = new String[]{ConstantsUtils.CUSTOMER_NO_LABEL, ConstantsUtils.CUSTOMER_NAME_LABEL, ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, ConstantsUtils.COMPANY_FAMILY_PLAN_NO,
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME, ConstantsUtils.NET_SALES_RULE_NO, ConstantsUtils.NET_SALES_RULE_NAME_LABEL};
    
    public static final Object[] AVAILABLE_DED_COL = new Object[]{ConstantsUtils.DEDUCTION_TYPE, ConstantsUtils.DEDUCTION_SUB_TYPE, ConstantsUtils.DEDUCTION_CATEGORY};
    
    public static final String[] AVAILABLE_DED_HEADER = new String[]{ConstantsUtils.DEDUCTION_TYPE_LABEL, ConstantsUtils.DEDUCTION_SUB_TYPE_LABEL, ConstantsUtils.DEDUCTION_CATEGORY_LABEL};
    
    public static final Object[] SELECTED_DED_COL = new Object[]{"selectedCheck", ConstantsUtils.DEDUCTION_TYPE,  ConstantsUtils.DEDUCTION_SUB_TYPE, ConstantsUtils.DEDUCTION_CATEGORY, "indicator", ConstantsUtils.NET_SALES_RULENO,ConstantsUtils.NET_SALES_RULE_NAME};
    
    public static final String[] SELECTED_DED_HEADER = new String[]{"", ConstantsUtils.DEDUCTION_TYPE_LABEL, ConstantsUtils.DEDUCTION_SUB_TYPE_LABEL, ConstantsUtils.DEDUCTION_CATEGORY_LABEL, "+/- Indicator", ConstantsUtils.NET_SALES_RULE_NO,ConstantsUtils.NET_SALES_RULE_NAME_LABEL};
    
    public static final Object[] AVAILABLE_DED_COL1 = new Object[]{ConstantsUtils.CONTRACT_NO, ConstantsUtils.CONTRACT_NAME, ConstantsUtils.DEDUCTION_NO, ConstantsUtils.DEDUCTION_NAME, ConstantsUtils.DEDUCTION_TYPE,  ConstantsUtils.DEDUCTION_SUB_TYPE, ConstantsUtils.DEDUCTION_CATEGORY, ConstantsUtils.MARKET_TYPE, "startDate", "endDate", ConstantsUtils.CONTRACT_HOLDER, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO,ConstantsUtils.ITEM_FAMILY_PLAN_NAME, ConstantsUtils.PS_NO, ConstantsUtils.PS_NAME};
    
    public static final String[] AVAILABLE_DED_HEADER1 = new String[]{ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, ConstantsUtils.DEDUCTION_NO_LABEL, ConstantsUtils.DEDUCTION_NAME_LABEL, ConstantsUtils.DEDUCTION_TYPE_LABEL, "Deduction Sub Type", ConstantsUtils.DEDUCTION_CATEGORY_LABEL, ConstantsUtils.MARKET_TYPE_LABEL, "Start Date", "End Date", ConstantsUtils.CONTRACT_HOLDER_LABEL, ConstantsUtils.COMPANY_FAMILY_PLAN_NO,
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO_LABEL, ConstantsUtils.ITEM_FAMILY_PLAN_NAME_LABEL,  ConstantsUtils.PRICE_SCHEDULE_NO, ConstantsUtils.PRICE_SCHEDULE_NAME};
    
    public static final Object[] SELECTED_DED_COL1 = new Object[]{"selectedCheck", ConstantsUtils.CONTRACT_NO, ConstantsUtils.CONTRACT_NAME, ConstantsUtils.DEDUCTION_NO,ConstantsUtils.DEDUCTION_NAME, ConstantsUtils.DEDUCTION_TYPE,  ConstantsUtils.DEDUCTION_SUB_TYPE, ConstantsUtils.DEDUCTION_CATEGORY, ConstantsUtils.MARKET_TYPE, "startDate", "endDate", ConstantsUtils.CONTRACT_HOLDER, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO, ConstantsUtils.ITEM_FAMILY_PLAN_NAME, ConstantsUtils.PS_NO, ConstantsUtils.PS_NAME ,"indicator", ConstantsUtils.NET_SALES_RULENO,ConstantsUtils.NET_SALES_RULE_NAME};
    
    public static final String[] SELECTED_DED_HEADER1 = new String[]{"", ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, ConstantsUtils.DEDUCTION_NO_LABEL, ConstantsUtils.DEDUCTION_NAME_LABEL, ConstantsUtils.DEDUCTION_TYPE_LABEL, ConstantsUtils.DEDUCTION_SUB_TYPE_LABEL, ConstantsUtils.DEDUCTION_CATEGORY_LABEL, ConstantsUtils.MARKET_TYPE_LABEL, "Start Date", "End Date", ConstantsUtils.CONTRACT_HOLDER_LABEL, ConstantsUtils.COMPANY_FAMILY_PLAN_NO,
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME, ConstantsUtils.ITEM_FAMILY_PLAN_NO_LABEL, ConstantsUtils.ITEM_FAMILY_PLAN_NAME_LABEL,  ConstantsUtils.PRICE_SCHEDULE_NO, ConstantsUtils.PRICE_SCHEDULE_NAME, "+/- Indicator", ConstantsUtils.NET_SALES_RULE_NO,ConstantsUtils.NET_SALES_RULE_NAME_LABEL};
    
    public static final Object[] NSR_COLUMN = new Object[]{"ruleType", "ruleNo", "ruleName", "ruleCategoryString"};
    
    public static final String[] NSR_HEADER = new String[]{"Rule Type", "Rule No", "Rule Name", "Rule Category"};
    
    public static final Object[] NSR_DETAILS_COLUMN = new Object[]{"lineType", "association", "keyword", "operator", "value", "comparison", "logicalOperator"};
    
    public static final String[] NSR_DETAILS_HEADER = new String[]{"Line Type", "Item/Group/Association", "Keyword", "Operator", "Value", "Comparison", "Operator"};
    
    public static final Object[] SELECTED_CUSTOMER_COL1 = new Object[]{"availableCheck", ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.CONTRACT_NO, ConstantsUtils.CONTRACT_NAME, ConstantsUtils.CFP_NO,
        ConstantsUtils.CFP_NAME, ConstantsUtils.NET_SALES_RULENO, ConstantsUtils.NET_SALES_RULE_NAME};
    
    public static final String[] SELECTED_CUSTOMER_HEADER1 = new String[]{"", ConstantsUtils.CUSTOMER_NO_LABEL, ConstantsUtils.CUSTOMER_NAME_LABEL, ConstantsUtils.CONTRACT_NO1, ConstantsUtils.CONTRACT_NAME1, "Company Family Plan No",
        ConstantsUtils.COMPANY_FAMILY_PLAN_NAME, ConstantsUtils.NET_SALES_RULE_NO, ConstantsUtils.NET_SALES_RULE_NAME_LABEL};

    public ComboBox loadIndicator(ComboBox massCombo,boolean isFilter) {
        String defaultValue=isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
        massCombo.removeAllItems();
        massCombo.addItem(defaultValue);
        massCombo.setNullSelectionAllowed(true);
        massCombo.setImmediate(true);
        massCombo.addItem("Add");
        massCombo.addItem("Subtract");
        massCombo.setNullSelectionItemId(defaultValue);
        return massCombo;
    }
}
