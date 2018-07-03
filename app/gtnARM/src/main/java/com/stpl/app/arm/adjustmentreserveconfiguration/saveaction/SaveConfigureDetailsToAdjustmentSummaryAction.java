/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.saveaction;

import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.utils.ReserveSelection;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class SaveConfigureDetailsToAdjustmentSummaryAction implements SaveAction {

    public static final Logger LOGGER = LoggerFactory.getLogger(SaveConfigureDetailsToAdjustmentSummaryAction.class);

    private AdjustmentSummaryConfigLogic logic = AdjustmentSummaryConfigLogic.getInstance();

    private ReserveSelection selection;

    public SaveConfigureDetailsToAdjustmentSummaryAction(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean doSaveAction() {
        try {
            List inputList = new ArrayList<>();
            inputList.add(selection.getMethodology());
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            inputList.add(selection.getTempTableName());
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            inputList.add(selection.getTempTableName());
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            logic.mergeAdjSummaryDataFromConfigureTab(inputList);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
