/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkAddAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkBackAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCRValueChangeAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCVDeleteAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCVSaveValidationAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkConfirmSaveAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCustomerAddAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkOptionGroupChangeAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkRemoveAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnUIFrameworkCVDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVDynamicFiller implements GtnUIDynamicObjectFiller {

    @Override
    public void addDynamicObject() {
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCRValueChangeAction.class.getName(),
				new GtnFrameworkCRValueChangeAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkOptionGroupChangeAction.class.getName(),
				new GtnFrameworkOptionGroupChangeAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCustomerAddAction.class.getName(),
				new GtnFrameworkCustomerAddAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRemoveAction.class.getName(),
				new GtnFrameworkRemoveAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCVSaveValidationAction.class.getName(),
				new GtnFrameworkCVSaveValidationAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfirmSaveAction.class.getName(),
				new GtnFrameworkConfirmSaveAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCustomViewEditAction.class.getName(),
				new GtnFrameworkCustomViewEditAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCVDeleteAction.class.getName(),
				new GtnFrameworkCVDeleteAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCVDeleteConfirmationAction.class.getName(),
				new GtnUIFrameworkCVDeleteConfirmationAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddAction.class.getName(),
				new GtnFrameworkAddAction());
        GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkBackAction.class.getName(),
				new GtnFrameworkBackAction());
    }
    
}
