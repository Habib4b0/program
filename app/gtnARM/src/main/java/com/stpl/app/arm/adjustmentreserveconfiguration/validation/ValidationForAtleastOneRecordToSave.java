/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.validation;

import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.util.constants.ARMMessages;

/**
 *
 * * This class is the validator to check Atleast one record is in the list
 * view
 */
public class ValidationForAtleastOneRecordToSave implements Validation {

    ReserveSelection selection;

    public ValidationForAtleastOneRecordToSave(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
        return (selection.getReserveMasterSid() != 0 || selection.getGtnDetailsMasterSid() != 0) && logic.madatoryForAtleastOneRecordtosave(selection, "ValidationForatleastOneRecordForSave");
    }

    @Override
    public String validationMessage() {
        return ARMMessages.getSaveMessageID003();
    }

}
