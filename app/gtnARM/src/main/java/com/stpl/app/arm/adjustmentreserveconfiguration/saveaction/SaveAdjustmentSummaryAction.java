/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveAdjustmentSummaryAction implements SaveAction {

    public static final Logger LOGGER = Logger.getLogger(SaveAdjustmentSummaryAction.class);

    AdjustmentSummaryConfigLogic logic = AdjustmentSummaryConfigLogic.getInstance();
    ReserveSelection selection;

    public SaveAdjustmentSummaryAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() throws Exception {
        try {
            if (ARMUtils.COPY.equals(selection.getSession().getMode())) {
                logic.updateReserveMasterSid(selection);
            }
            logic.saveTempToMainAdjSummaryData(selection);
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return false;
        }
    }

}
