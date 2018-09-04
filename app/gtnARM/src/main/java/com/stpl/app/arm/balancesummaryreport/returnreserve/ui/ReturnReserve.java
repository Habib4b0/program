/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.returnreserve.ui;

import com.stpl.app.arm.balancesummaryreport.returnreserve.logic.ReturnReserveLogic;
import com.stpl.app.arm.balancesummaryreport.ui.AbstractBSummaryReportSummary;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;

/**
 *
 * @author
 */
public class ReturnReserve extends AbstractBSummaryReportSummary {

    private final ReturnReserveResults results;
    private String[] returnReservedefaultColumns = {"Starting_Balance", "Return_Reserve", "Ending_Balance", "Total"};
    private String[] returnReserveDefaultColumnsHeader = {"Starting Balance", "Return Reserve", "Ending Balance", "Total"};
    private static final String PRC_ARM_BSR_RETURN_RESERVE = "PRC_ARM_BSR_RETURN_RESERVE";
    private static final String ARM_BSR_RETURN_RESERVE = "ARM_BSR_RETURN_RESERVE";

    public ReturnReserve(SummarySelection tselection, ReturnReserveLogic logic) {
        super(logic, tselection);
        results = new ReturnReserveResults(logic, getSelection());
        init();
    }

    @Override
    public ReturnReserveResults getResultsObject() {
        return results;
    }

    @Override
    public String[] getDefaultColumnsHeader() {
        return returnReserveDefaultColumnsHeader.clone();
    }

    @Override
    public String[] getDefaultColumns() {
        return returnReservedefaultColumns.clone();
    }

    @Override
    public String getProcedureName() {
        return PRC_ARM_BSR_RETURN_RESERVE;
    }

    @Override
    public String getTempTablesPropertyName() {
        return ARM_BSR_RETURN_RESERVE;
    }

    @Override
    public boolean equals(Object retresObj) {
        return super.equals(retresObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String[] getAdjustmentTypeHeader() {
        return returnReserveDefaultColumnsHeader.clone();
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        return returnReservedefaultColumns.clone();
    }
}
