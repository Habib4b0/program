/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.dynamicFiller;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkAdjustmentDetailsSaveViewAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDeductionLevelValueChange;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkTransactionDetailsValueChange;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionRefreshBeanAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionReprocessRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionTableCheckAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnFrameworkTransactionReprocessRemoveValidation;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsDynamicClassFiller implements GtnUIDynamicObjectFiller {

    @Override
    public void addDynamicObject() {
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTransactionDetailsValueChange.class.getName(),
                new GtnFrameworkTransactionDetailsValueChange());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeductionLevelValueChange.class.getName(),
                new GtnFrameworkDeductionLevelValueChange());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAdjustmentDetailsSaveViewAction.class.getName(),
                new GtnFrameworkAdjustmentDetailsSaveViewAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTransactionReprocessRemoveValidation.class.getName(),
                new GtnFrameworkTransactionReprocessRemoveValidation());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionRefreshBeanAction.class.getName(),
                new GtnUIFrameworkTransactionRefreshBeanAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionReprocessRemoveAction.class.getName(),
                new GtnUIFrameworkTransactionReprocessRemoveAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionTableCheckAction.class.getName(),
                new GtnUIFrameworkTransactionTableCheckAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionTableCheckAllAction.class.getName(),
                new GtnUIFrameworkTransactionTableCheckAllAction());
    }

}
