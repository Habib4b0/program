/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.utils.ReserveSelection;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveConfigurationDetailsAction implements SaveAction {

    AdjustmentReserveDTO binderDto;
    protected AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    ReserveSelection selection;

    public SaveConfigurationDetailsAction(AdjustmentReserveDTO binderDto, ReserveSelection selection) {
        this.binderDto = binderDto;
        this.selection = selection;

    }

    @Override
    public boolean doSaveAction() throws Exception {
        try {
            if (selection.isIsSaved()) {
                logic.tempToMainUpdateLogic(selection);
                logic.masterUpdateModifiedDate(selection);
            } else {
                if (selection.getReserveMasterSid() == 0) {
                    int id = logic.addLineForMaster(selection, 0);
                    selection.setReserveMasterSid(id);
                } else if (selection.getGtnDetailsMasterSid() == 0) {
                    int id = logic.addLineForMaster(selection, 1);
                    selection.setGtnDetailsMasterSid(id);
                }

                logic.tempToMainSaveLogic(selection, binderDto);
            }
            selection.setIsSaved(true);
            return true;
        } catch (Exception ex) {
            throw new Exception("Exception occured While saving SaveConfigurationDetails : - > " + ex.getMessage());
        }

    }

}
