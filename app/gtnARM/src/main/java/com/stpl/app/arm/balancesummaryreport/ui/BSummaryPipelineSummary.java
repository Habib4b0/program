/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryPipelineLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryPipelineSummary extends AbstractBSummaryReportSummary {

    /**
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    private BSummaryPipelineResults results;
    
    public final Logger pipelineSummary = LoggerFactory.getLogger(BSummaryPipelineSummary.class);

    private String[] defaultVariablesHeader = {"Starting Balance", "Pipeline Accrual", "Demand Accrual", "Pipeline Inventory True-Up",
        "Demand Reforecast", "Total Period Adjustment", "Ending Balance", "Total"};
    private String[] defaultVariablesColumns = {"Starting_Balance", "Pipeline_Accrual", "Demand_Accrual", "Pipeline_Inventory_True-Up",
        "Demand_Reforecast", "Total_Period_Adjustment", "Ending_Balance", "Total"};
    private String[] defaultColumnsHeader = {"Starting Balance", "Pipeline Accrual", "Demand Accrual", "Pipeline Inventory True-Up",
        "Demand Reforecast", "Total Period Adjustment", "Ending Balance"};
    private String[] defaultColumns = {"Starting_Balance", "Pipeline_Accrual", "Demand_Accrual", "Pipeline_Inventory_True-Up",
        "Demand_Reforecast", "Total_Period_Adjustment", "Ending_Balance"};

    public BSummaryPipelineSummary(SummarySelection tselection, BSummaryPipelineLogic logic) {
        super(logic, tselection);
        results = new BSummaryPipelineResults(logic, getSelection());
        init();
    }

    @Override
    public BSummaryPipelineResults getResultsObject() {
        pipelineSummary.debug("inside getResultsObject");
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        pipelineSummary.debug("inside getDefaultColumnsHeader");
        return defaultColumnsHeader.clone();
    }

    @Override
    public String[] getDefaultColumns() {
        pipelineSummary.debug("inside getDefaultColumns");
        return defaultColumns.clone();
    }

    @Override
    public String getProcedureName() {
        pipelineSummary.debug("inside getProcedureName");
        return "PRC_ARM_BSR_PIPELINE";
    }

    @Override
    public String getTempTablesPropertyName() {
        pipelineSummary.debug("inside getTempTablesPropertyName");
        return "ARM_BSR_PIPELINE";
    }

    @Override
    public boolean equals(Object bPipelobj) {
        return super.equals(bPipelobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String[] getAdjustmentTypeHeader() {
        pipelineSummary.debug("inside getAdjustmentTypeHeader");
        return defaultVariablesHeader.clone();
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        pipelineSummary.debug("inside getAdjustmentTypeColumn");
        return defaultVariablesColumns.clone();
    }
}
