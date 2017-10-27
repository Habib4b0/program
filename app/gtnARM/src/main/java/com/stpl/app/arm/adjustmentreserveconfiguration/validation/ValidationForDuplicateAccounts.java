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
 * * This class is the validator to check for duplicate accounts for a single
 * adjustment type
 */
public class ValidationForDuplicateAccounts implements Validation {

    ReserveSelection selection;

    public ValidationForDuplicateAccounts(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
        return logic.isDuplicateAccount(selection.getTempTableName(), "Duplicate_Account_For_Same_AdjustmentType");
    }

    @Override
    public String validationMessage() {
        return ARMMessages.getSameAccountNameValidation();
    }

}
