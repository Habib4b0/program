/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.utils.ReserveSelection;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveBalanceSummaryInsertAction implements SaveAction {

    BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();
    ReserveSelection selection;

    public SaveBalanceSummaryInsertAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() throws Exception {
        try {
            if (logic.isReportTypeAvailable(selection)) {
                logic.insertBalanceSummaryToTempTable(selection);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
