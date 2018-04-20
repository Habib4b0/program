/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.utils.ReserveSelection;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveMainToTempAction implements SaveAction {

    private ReserveSelection selection;

    public SaveMainToTempAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() {
        AdjustmentReserveLogic.getInstance().insertToTempTable(selection);
        AdjustmentSummaryConfigLogic.getInstance().insertAdjSummaryToTempTableFromMainTable(selection);
        BalanceSummaryLogic.getInstance().insertBalanceSummaryToTempTableFromMainTable(selection);
        return true;
    }

}
