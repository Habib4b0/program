/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.dynamicFiller;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDeductionLevelValueChange;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkSearchConfig;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkTransactionDetailsValueChange;
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
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSearchConfig.class.getName(),
                new GtnFrameworkSearchConfig());
    }

}
