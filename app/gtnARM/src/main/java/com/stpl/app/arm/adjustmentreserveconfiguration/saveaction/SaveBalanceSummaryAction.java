/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveBalanceSummaryAction implements SaveAction {

    public static final Logger LOGGER = LoggerFactory.getLogger(SaveBalanceSummaryAction.class);
    private BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();

    private ReserveSelection selection;

    public SaveBalanceSummaryAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() {
        logic.saveTempToMainBalanceSummaryData(selection);
        return true;
    }

}
