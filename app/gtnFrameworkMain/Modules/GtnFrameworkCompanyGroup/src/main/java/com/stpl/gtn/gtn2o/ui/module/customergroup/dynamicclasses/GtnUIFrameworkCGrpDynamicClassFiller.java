package com.stpl.gtn.gtn2o.ui.module.customergroup.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameWorkCGrpLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpAddAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpAddAllAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpBackBtnAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpCopyAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpEditAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpRemoveAllAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpResetAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpSaveAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.GtnFrameworkCGrpTempTableClearAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation.GtnFrameworkCGrpCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation.GtnFrameworkCGrpDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation.GtnFrameworkCGrpTableEmptyValidationAction;

public class GtnUIFrameworkCGrpDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpCommonValidationAction.class.getName(),
				new GtnFrameworkCGrpCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpDeleteValidationAction.class.getName(),
				new GtnFrameworkCGrpDeleteValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpTableEmptyValidationAction.class.getName(),
				new GtnFrameworkCGrpTableEmptyValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpAddAction.class.getName(),
				new GtnFrameworkCGrpAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpAddAllAction.class.getName(),
				new GtnFrameworkCGrpAddAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpAddButtonAction.class.getName(),
				new GtnFrameworkCGrpAddButtonAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpCopyAction.class.getName(),
				new GtnFrameworkCGrpCopyAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpDeleteAction.class.getName(),
				new GtnFrameworkCGrpDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpEditAction.class.getName(),
				new GtnFrameworkCGrpEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameWorkCGrpLoadDataTableAction.class.getName(),
				new GtnFrameWorkCGrpLoadDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpRemoveAction.class.getName(),
				new GtnFrameworkCGrpRemoveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpRemoveAllAction.class.getName(),
				new GtnFrameworkCGrpRemoveAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpResetAction.class.getName(),
				new GtnFrameworkCGrpResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpSaveAction.class.getName(),
				new GtnFrameworkCGrpSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpTempTableClearAction.class.getName(),
				new GtnFrameworkCGrpTempTableClearAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCGrpBackBtnAction.class.getName(),
				new GtnFrameworkCGrpBackBtnAction());

	}

}
