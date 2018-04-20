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
 * This class is the validator to check The GTN accounts are matched with
 * Reserve accounts
 */
public class ValidationForNotMatchedAccountsOfGTNReserve implements Validation {

    private ReserveSelection selection;

    public ValidationForNotMatchedAccountsOfGTNReserve(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        return AdjustmentReserveLogic.getInstance().isGTNRecordsAreAvailableInReserve(selection.getTempTableName(), "Account_GtnIsNotAvailableInReserve");
    }

    @Override
    public String validationMessage() {
        return ARMMessages.getNotMatchedAccountsOfGTNAndReserveValidation();
    }

}
