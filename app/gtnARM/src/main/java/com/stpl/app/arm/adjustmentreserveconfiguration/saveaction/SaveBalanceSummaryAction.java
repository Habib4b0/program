/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveBalanceSummaryAction implements SaveAction {

    public static final Logger LOGGER = Logger.getLogger(SaveBalanceSummaryAction.class);
    BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();

    ReserveSelection selection;

    public SaveBalanceSummaryAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() throws Exception {
        logic.saveTempToMainBalanceSummaryData(selection);
        return true;
    }

}
