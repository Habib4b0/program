/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.validation;

import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.util.constants.ARMMessages;

/**
 *
 * This class is the Validator to check Credit and Debit indicator of Adjustment
 * type are same
 */
public class ValidationForSameCreditDebitOfAdjustmentType implements Validation {

    ReserveSelection selection;

    public ValidationForSameCreditDebitOfAdjustmentType(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        return AdjustmentReserveLogic.getInstance().isCreditDebitAreOppositeForSameAdjustmentType(selection.getReserveMasterSid(),selection.getTempTableName(), "isCreditDebitAreOpposite");
    }

    @Override
    public String validationMessage() {
        return ARMMessages.getSameDebitCreditIndicatorValidation();
    }

}
