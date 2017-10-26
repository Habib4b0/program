/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryPipelineLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryPipelineSummary extends AbstractBSummaryReportSummary {

    /**
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    BSummaryPipelineResults results;

    String[] defaultColumnsHeader = {"Starting Balance", "Actual Payments", "Total Period Adjustment", "Ending Balance", "Cumulative Balance"};
    String[] defaultColumns = {"Starting_Balance", "Actual_Payments", "Total_Period_Adjustment", "Ending_Balance", "Cumulative_Balance "};

    public BSummaryPipelineSummary(SummarySelection tselection, BSummaryPipelineLogic logic) {
        super(logic, tselection);
        results = new BSummaryPipelineResults(logic, selection);
        init();
    }

    @Override
    public BSummaryPipelineResults getResultsObject() {
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        return defaultColumnsHeader;
    }

    @Override
    public String[] getDefaultColumns() {
        return defaultColumns;
    }

    @Override
    public String getProcedureName() {
        return "PRC_BALANCE_REPORT_PIPELINE";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_PIPELINE";
    }

}
