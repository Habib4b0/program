/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.singleliablity.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryPipelineLogic;
import com.stpl.app.arm.balancesummaryreport.ui.AbstractBSummaryReportSummary;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;

/**
 *
 * @author Sathya.Seelan
 */
public class SingleLiablity extends AbstractBSummaryReportSummary {

    /**
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    private SingleLiablityResults results;
    
    private String[] defaultVariablesHeader = {"Starting Balance", "Pipeline Accrual", "Demand Accrual", "Pipeline Inventory True-Up",
        "Demand Reforecast", "Total Period Adjustment", "Ending Balance", "Total"};
    private String[] defaultVariablesColumns = {"Starting_Balance","Pipeline_Accrual","Demand_Accrual","Pipeline_Inventory_True-Up",
        "Demand_Reforecast","Total_Period_Adjustment","Ending_Balance", "Total"};
    private String[] defaultColumnsHeader = {"Starting Balance", "Pipeline Accrual", "Demand Accrual", "Pipeline Inventory True-Up",
        "Demand Reforecast", "Total Period Adjustment", "Ending Balance"};
    private String[] defaultColumns = {"Starting_Balance","Pipeline_Accrual","Demand_Accrual","Pipeline_Inventory_True-Up",
        "Demand_Reforecast","Total_Period_Adjustment","Ending_Balance"};

    public SingleLiablity(SummarySelection tselection, BSummaryPipelineLogic logic) {
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
        return "PRC_ARM_BSR_PIPELINE";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_PIPELINE";
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
