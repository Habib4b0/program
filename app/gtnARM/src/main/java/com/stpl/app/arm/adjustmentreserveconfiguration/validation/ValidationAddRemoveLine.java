/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.validation;

import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.util.constants.ARMMessages;
import java.util.List;

/**
 *
 * @author Mohamed.Shahul
 */
public class ValidationAddRemoveLine implements Validation {

    ReserveSelection selection;
    boolean addLineOrRemoveLine;

    public ValidationAddRemoveLine(ReserveSelection selection, boolean addLineOrRemoveLine) {
        this.selection = selection;
        this.addLineOrRemoveLine = addLineOrRemoveLine;
    }

    @Override
    public boolean doValidate() {
        List inputList = BalanceSummaryLogic.getInstance().commonInputForSummaryTabs(selection);
        return CommonLogic.getCount(QueryUtils.getItemData(inputList, "isRecordsAreChecked", null)) > 0;
    }

    @Override
    public String validationMessage() {
        return addLineOrRemoveLine ? "Please check mark a row to add a line to." : ARMMessages.getRemoveLineMessageID001();
    }

}
