package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.balancesummaryreport.logic.BSummaryDemandLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class BSummaryDemandSummary extends AbstractBSummaryReportSummary {

    private BSummaryDemandResults results;
    public final Logger demandLogger = LoggerFactory.getLogger(BSummaryDemandSummary.class);
    private String[] defaultColumns = {"Beginning_Balance", "Demand_Accrual", "Demand_Reforecast", "Payment_True_up", "Actual_Payments", "Period_Balance", "Payment_Ratio", "Total"};
    private String[] defaultColumnsHeader = {"Beginning Balance", "Demand Accrual", "Demand Reforecast", "Payment True-up", "Actual Payments", "Period Balance", "Payment Ratio", "Total"};

    public BSummaryDemandSummary(SummarySelection tselection, BSummaryDemandLogic logic) {
        super(logic, tselection);
        results = new BSummaryDemandResults(logic, getSelection());
        init();
    }

    @Override
    public AbstractBalanceSummaryResutls getResultsObject() {
        demandLogger.debug("Inside getResultsObject");
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        demandLogger.debug("Inside getDefaultColumnsHeader");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getDefaultColumns() {
        demandLogger.debug("Inside getDefaultColumns");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }

    @Override
    public String getProcedureName() {
        demandLogger.debug("Inside getProcedureName");
        return "PRC_ARM_BSR_DEMAND";
    }

    @Override
    public String getTempTablesPropertyName() {
        demandLogger.debug("Inside getTempTablesPropertyName");
        return "ARM_BSR_DEMAND";
    }

    @Override
    public boolean equals(Object demadnobj) {
        return super.equals(demadnobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String[] getAdjustmentTypeHeader() {
        demandLogger.debug("Inside getAdjustmentTypeHeader");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        demandLogger.debug("Inside getAdjustmentTypeColumn");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }
}
