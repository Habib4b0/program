/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.singleliablity.ui;

import com.stpl.app.arm.balancesummaryreport.singleliablity.logic.SingleLiablityLogic;
import com.stpl.app.arm.balancesummaryreport.ui.AbstractBSummaryReportSummary;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.utils.VariableConstants;

/**
 *
 * @author Sathya
 */
public class SingleLiablity extends AbstractBSummaryReportSummary {

    /**
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    private SingleLiablityResults results;

    private String[] defaultVariablesHeader = {"Beginning Balance", "Fees Accrual", "Inflation Adjustment", "Credit Card Fees", "Other Fixed Dollar Fees",
        "Inventory Valuation", "Payment True-up", VariableConstants.PAYMENTS, "Period Balance", "Total"};
    private String[] defaultVariablesColumns = {"Beginning_Balance", "Fees_Accrual", "Inflation_Adjustment", "Credit_Card_Fees", "Other_Fixed_Dollar_Fees",
        "Inventory_Valuation", "Payment_True-up", VariableConstants.PAYMENTS, "Period_Balance", "Total"};
    private String[] defaultColumnsHeader = {"Fees Accrual", "Inflation Adjustment", "Credit Card Fees", "Other Fixed Dollar Fees",
        "Inventory Valuation", "Payment True-up", VariableConstants.PAYMENTS, "Period Balance"};
    private String[] defaultColumns = {"Fees_Accrual", "Inflation_Adjustment", "Credit_Card_Fees", "Other_Fixed_Dollar_Fees",
        "Inventory_Valuation", "Payment_True-up", VariableConstants.PAYMENTS, "Period_Balance"};

    public SingleLiablity(SummarySelection tselection, SingleLiablityLogic logic) {
        super(logic, tselection);
        results = new SingleLiablityResults(logic, getSelection());
        init();
    }

    @Override
    public SingleLiablityResults getResultsObject() {
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        return defaultColumnsHeader.clone();
    }

    @Override
    public String[] getDefaultColumns() {
        return defaultColumns.clone();
    }

    @Override
    public String getProcedureName() {
        return "PRC_ARM_BSR_SINGLE_LIABILITY";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_SINGLE_LIABLITY";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String[] getAdjustmentTypeHeader() {
        return defaultVariablesHeader.clone();
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        return defaultVariablesColumns.clone();
    }
}
