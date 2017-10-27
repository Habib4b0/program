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
import org.jboss.logging.Logger;

/**
 *
 * This class is the validator to check whether if Row is Reserve , then two
 * rows and if GTN , one row has to be there for every adjustment type
 */
public class ValidationForLimitedRowInDetails implements Validation {

    ReserveSelection selection;
    boolean isConfigureType;
    private static final Logger LOGGER = Logger.getLogger(ValidationForLimitedRowInDetails.class);

    public ValidationForLimitedRowInDetails(ReserveSelection selection, boolean reserveOrGTN) {
        this.selection = selection;
        this.isConfigureType = reserveOrGTN;
    }

    @Override
    public boolean doValidate() {
        if (!isConfigureType) {
            LOGGER.info("Inside 4 ::");
        } else {
            LOGGER.info("Inside 7 ::");
        }
        AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
        boolean flag = false;
        flag = logic.isOnlyValidNoOfRecordsAreAvailableForAdjustmentType(selection.getTempTableName(), !isConfigureType ? "Reserve_Two_Records_For_Same_Adj_Type" : "one_row_for_adjustment_type_on_GTN");
        return flag;
    }

    @Override
    public String validationMessage() {
        return !isConfigureType ? ARMMessages.getTwoRowsForReserveAdjustmentTypes() : ARMMessages.getOneRowForGTNAdjustmentTypes();
    }

}
