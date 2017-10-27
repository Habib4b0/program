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
public class ReturnReserve  extends AbstractBSummaryReportSummary {

    private final ReturnReserveResults results;
    private String[] defaultColumns = {"Starting_Balance", "Return_Reserve", "Ending_Balance", "Total"};
    private String[] defaultColumnsHeader = {"Starting Balance", "Return Reserve", "Ending Balance", "Total"};

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
        return defaultColumnsHeader.clone();
    }

    @Override
    public String[] getDefaultColumns() {
        return defaultColumns.clone();
    }

    @Override
    public String getProcedureName() {
        return "PRC_ARM_BSR_RETURN_RESERVE";
    }

    @Override
    public String getTempTablesPropertyName() {
        return "ARM_BSR_RETURN_RESERVE";
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
        return defaultColumnsHeader.clone();
    }

    @Override
    public String[] getAdjustmentTypeColumn() {
        return defaultColumns.clone();
    }
}
