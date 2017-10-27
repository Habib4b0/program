/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.excecutors.SaveAction;

/**
 *
 * @author Mohamed.Shahul
 */
public class DeleteAdjustmentReserveAction implements SaveAction {

    AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    int reserveSid;

    public DeleteAdjustmentReserveAction(int reserveSid) {
        this.reserveSid = reserveSid;
    }

    @Override
    public boolean doSaveAction() throws Exception {
        logic.deleteReserveMaster(reserveSid);
        logic.deleteAdjustmentSummary(reserveSid);
        logic.deleteBalanceSummary(reserveSid);
        return true;
    }

}
