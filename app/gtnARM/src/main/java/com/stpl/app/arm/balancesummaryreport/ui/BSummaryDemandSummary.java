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
    public static final Logger DEMAND_LOGGER = LoggerFactory.getLogger(BSummaryDemandSummary.class);
    private String[] defaultColumns = {"Beginning_Balance", "Demand_Accrual", "Demand_Reforecast", "Payment_True_up", "Actual_Payments", "Period_Balance", "Payment_Ratio", "Total"};
    private String[] defaultColumnsHeader = {"Beginning Balance", "Demand Accrual", "Demand Reforecast", "Payment True-up", "Actual Payments", "Period Balance", "Payment Ratio", "Total"};

    public BSummaryDemandSummary(SummarySelection tselection, BSummaryDemandLogic logic) {
        super(logic, tselection);
        results = new BSummaryDemandResults(logic, getSelection());
        init();
    }

    @Override
    public AbstractBalanceSummaryResutls getResultsObject() {
        DEMAND_LOGGER.debug("Inside getResultsObject");
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        DEMAND_LOGGER.debug("Inside getDefaultColumnsHeader");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getDefaultColumns() {
        DEMAND_LOGGER.debug("Inside getDefaultColumns");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }

    @Override
    public String getProcedureName() {
        DEMAND_LOGGER.debug("Inside getProcedureName");
        return "PRC_ARM_BSR_DEMAND";
    }

    @Override
    public String getTempTablesPropertyName() {
        DEMAND_LOGGER.debug("Inside getTempTablesPropertyName");
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
        DEMAND_LOGGER.debug("Inside getAdjustmentTypeHeader");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumnsHeader);
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        DEMAND_LOGGER.debug("Inside getAdjustmentTypeColumn");
        return CommonLogic.getInstance().getStringArrayCloned(defaultColumns);
    }
}
