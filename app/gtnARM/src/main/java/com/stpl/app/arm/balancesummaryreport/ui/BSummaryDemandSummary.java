package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryDemandLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryDemandSummary extends AbstractBSummaryReportSummary {

    private BSummaryDemandResults results;
    private String[] defaultColumns = {"Beginning_Balance", "Demand_Accrual", "Demand_Reforecast", "Payment_True_up", "Actual_Payments", "Period_Balance", "Payment_Ratio", "Total"};
    private String[] defaultColumnsHeader = {"Beginning Balance", "Demand Accrual", "Demand Reforecast", "Payment True-up", "Actual Payments", "Period Balance", "Payment Ratio", "Total"};

    public BSummaryDemandSummary(SummarySelection tselection, BSummaryDemandLogic logic) {
        super(logic, tselection);
        results = new BSummaryDemandResults(logic, getSelection());
        init();
    }

    @Override
    public AbstractBalanceSummaryResutls getResultsObject() {
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getDefaultColumns() {
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }

    @Override
    public String getProcedureName() {
        return "PRC_ARM_BSR_DEMAND";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_DEMAND";
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
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }
}
