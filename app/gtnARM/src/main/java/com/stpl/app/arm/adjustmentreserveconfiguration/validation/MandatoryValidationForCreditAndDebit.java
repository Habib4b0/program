/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.validation;

import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.utils.ReserveSelection;

/**
 *
 * This class is the validator to check CreditAndDebit are selected
 */
public class MandatoryValidationForCreditAndDebit implements Validation {

    ReserveSelection selection;

    public MandatoryValidationForCreditAndDebit(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
        return logic.madatoryCheckCreditDebit(selection, "CrediDebit_Validation");
    }

    @Override
    public String validationMessage() {
        return "The Credit and Debit Indicators must be populated with a value";
    }

}
