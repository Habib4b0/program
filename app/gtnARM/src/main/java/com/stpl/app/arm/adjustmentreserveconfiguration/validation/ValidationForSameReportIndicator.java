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
 * @author Mohamed.Shahul
 */
public class ValidationForSameReportIndicator implements Validation {

    ReserveSelection selection;

    public ValidationForSameReportIndicator(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doValidate() {
        return AdjustmentReserveLogic.getInstance().isDuplicateReportIndicator(selection.getTempTableName(), "Duplicate_Report_Indicator");
    }

    @Override
    public String validationMessage() {
        return ARMMessages.getSameReportIndicatorValidation();
    }

}
