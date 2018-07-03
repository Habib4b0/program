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
 *  * This class is the validator to check ReserveAndGTN are selected
 *
 */
public class MandatoryValidationForReserveAndGTN implements Validation {

    private ReserveSelection selection;

    public MandatoryValidationForReserveAndGTN(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
        return logic.madatoryCheckReserveANDDetails(selection, "Mandatory check for Reserve", "Mandatory check for GTNDetails");
    }

    @Override
    public String validationMessage() {
        return "Please ensure that all mandatory fields are populated. ";
    }

}
