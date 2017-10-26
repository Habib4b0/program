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

   
    public static final Object[] AVAILABLE_CONTR_COL = new Object[]{"contractNo", "contractName", "contractHolder", "marketType", "companyFamilyPlanNo",
        "companyFamilyPlanName", "itemFamilyPlanNo", "itemFamilyPlanName", "priceScheduleNo", "priceScheduleName", "deductionNo", "deductionName"};
   
    public static final String[] AVAILABLE_CONTR_HEADER = new String[]{"Contract No", "Contract Name", "Contract Holder", "Market Type", "Company Family Plan No",
        "Company Family Plan Name", "Item Family Plan No", "Item Family Plan Name", "Price Schedule No", "Price Schedule Name", "Deduction No", "Deduction Name"};
    
    public static final Object[] AVAILABLE_CUSTOMER_COL = new Object[]{"companyNo", "companyName", "contractNo", "contractName", "companyFamilyPlanNo",
        "companyFamilyPlanName"};
    
    public static final String[] AVAILABLE_CUSTOMER_HEADER = new String[]{"Customer No", "Customer Name", "Contract No", "Contract Name", "Company Family Plan No",
        "Company Family Plan Name"};
    
    public static final Object[] SELECTED_CUSTOMER_COL = new Object[]{"companyNo", "companyName", "contractNo", "contractName", "companyFamilyPlanNo",
        "companyFamilyPlanName", "netSalesRuleNo", "netSalesRuleName"};
    
    public static final String[] SELECTED_CUSTOMER_HEADER = new String[]{"Customer No", "Customer Name", "Contract No", "Contract Name", "Company Family Plan No",
        "Company Family Plan Name", "Net Sales Rule No", "Net Sales Rule Name"};
    
    public static final Object[] AVAILABLE_DED_COL = new Object[]{"deductionType", "deductionSubType", "deductionCategory"};
    
    public static final String[] AVAILABLE_DED_HEADER = new String[]{"Deduction Type", "Deduction SubType", "Deduction Category"};
    
    public static final Object[] SELECTED_DED_COL = new Object[]{"selectedCheck", "deductionType", "deductionSubType", "deductionCategory", "indicator", "netSalesRuleNo","netSalesRuleName"};
    
    public static final String[] SELECTED_DED_HEADER = new String[]{"", "Deduction Type", "Deduction SubType", "Deduction Category", "+/- Indicator", "Net Sales Rule No","Net Sales Rule Name"};
    
    public static final Object[] AVAILABLE_DED_COL1 = new Object[]{"contractNo", "contractName", "deductionNo", "deductionName", "deductionType", "deductionSubType", "deductionCategory", "marketType", "startDate", "endDate", "contractHolder", "companyFamilyPlanNo",
        "companyFamilyPlanName", "itemFamilyPlanNo", "itemFamilyPlanName", "priceScheduleNo", "priceScheduleName"};
    
    public static final String[] AVAILABLE_DED_HEADER1 = new String[]{"Contract No", "Contract Name", "Deduction No", "Deduction Name", "Deduction Type", "Deduction Sub Type", "Deduction Category", "Market Type", "Start Date", "End Date", "Contract Holder", "Company Family Plan No",
        "Company Family Plan Name", "Item Family Plan No", "Item Family Plan Name", "Price Schedule No", "Price Schedule Name"};
    
    public static final Object[] SELECTED_DED_COL1 = new Object[]{"selectedCheck", "contractNo", "contractName", "deductionNo", "deductionName", "deductionType", "deductionSubType", "deductionCategory", "marketType", "startDate", "endDate", "contractHolder", "companyFamilyPlanNo",
        "companyFamilyPlanName", "itemFamilyPlanNo", "itemFamilyPlanName", "priceScheduleNo", "priceScheduleName" ,"indicator", "netSalesRuleNo","netSalesRuleName"};
    
    public static final String[] SELECTED_DED_HEADER1 = new String[]{"", "Contract No", "Contract Name", "Deduction No", "Deduction Name", "Deduction Type", "Deduction SubType", "Deduction Category", "Market Type", "Start Date", "End Date", "Contract Holder", "Company Family Plan No",
        "Company Family Plan Name", "Item Family Plan No", "Item Family Plan Name", "Price Schedule No", "Price Schedule Name", "+/- Indicator", "Net Sales Rule No","Net Sales Rule Name"};
    
    public static final Object[] NSR_COLUMN = new Object[]{"ruleType", "ruleNo", "ruleName", "ruleCategoryString"};
    
    public static final String[] NSR_HEADER = new String[]{"Rule Type", "Rule No", "Rule Name", "Rule Category"};
    
    public static final Object[] NSR_DETAILS_COLUMN = new Object[]{"lineType", "association", "keyword", "operator", "value", "comparison", "logicalOperator"};
    
    public static final String[] NSR_DETAILS_HEADER = new String[]{"Line Type", "Item/Group/Association", "Keyword", "Operator", "Value", "Comparison", "Operator"};
    
    public static final Object[] SELECTED_CUSTOMER_COL1 = new Object[]{"availableCheck", "companyNo", "companyName", "contractNo", "contractName", "companyFamilyPlanNo",
        "companyFamilyPlanName", "netSalesRuleNo", "netSalesRuleName"};
    
    public static final String[] SELECTED_CUSTOMER_HEADER1 = new String[]{"", "Customer No", "Customer Name", "Contract No", "Contract Name", "Company Family Plan No",
        "Company Family Plan Name", "Net Sales Rule No", "Net Sales Rule Name"};

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
