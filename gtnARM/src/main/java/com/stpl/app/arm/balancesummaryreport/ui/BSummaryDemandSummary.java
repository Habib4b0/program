package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryDemandLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryDemandSummary extends AbstractBSummaryReportSummary {

    BSummaryDemandResults results;
    String[] defaultColumnsHeader = {"Actual Payments", "Period Balance", "Payment Ratio", "Total", "Cumulative Balance"};
    String[] defaultColumns = {"Actual_Payments", "Period_Balance", "Payment_Ratio", "Total", "Cumulative_Balance"};

    public BSummaryDemandSummary(SummarySelection tselection, BSummaryDemandLogic logic) {
        super(logic, tselection);
        results = new BSummaryDemandResults(logic, selection);
        init();
    }

    @Override
    public AbstractBalanceSummaryResutls getResultsObject() {
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
        return "PRC_BALANCE_REPORT_DEMAND";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_DEMAND";
    }

}
