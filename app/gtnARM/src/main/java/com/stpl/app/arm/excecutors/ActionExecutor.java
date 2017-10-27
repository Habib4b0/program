/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.excecutors;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed.Shahul
 */
public class ActionExecutor {

    public void callingActionExecution(SaveAction... inputs) throws Exception {
        List<SaveAction> actionList = new ArrayList<>();
        for (SaveAction saveAction : inputs) {
            actionList.add(saveAction);
        }
        excecuteSaveAction(actionList);

    }

    /**
     * this is the method to excecute all the actions.
     *
     * @param actionList
     * @param selection
     * @throws Exception
     */
    public void excecuteSaveAction(List<SaveAction> actionList) throws Exception {
        for (SaveAction saveAction : actionList) {
            saveAction.doSaveAction();
        }
    }

    /**
     * Method to execute validation list
     *
     * @param validationList
     */
     public boolean executeValidation(List<Validation> validationList) {
        // Validation Message throwing logic
        for (Validation validation : validationList) {
            if (!validation.doValidate()) {
                AbstractNotificationUtils.getErrorNotification(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ERROR.getConstant(), validation.validationMessage());
                return false;
            }
        }
        return true;
    }

}
