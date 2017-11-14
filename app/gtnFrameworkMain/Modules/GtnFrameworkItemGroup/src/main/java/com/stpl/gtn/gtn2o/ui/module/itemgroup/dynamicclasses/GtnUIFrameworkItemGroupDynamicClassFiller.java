package com.stpl.gtn.gtn2o.ui.module.itemgroup.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameWorkItemGrpLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpAddAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpAddAllAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpCopyAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpEditAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpRemoveAllAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpResetAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpSaveAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpTempTableClearAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation.GtnFrameworkItemGroupTableEmptyValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation.GtnFrameworkItemGrpCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation.GtnFrameworkItemGrpDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation.GtnFrameworkItemGrpNameValidationAction;

public class GtnUIFrameworkItemGroupDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGroupTableEmptyValidationAction.class.getName(),
				new GtnFrameworkItemGroupTableEmptyValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpCommonValidationAction.class.getName(),
				new GtnFrameworkItemGrpCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpNameValidationAction.class.getName(),
				new GtnFrameworkItemGrpNameValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpAddAction.class.getName(),
				new GtnFrameworkItemGrpAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpAddAllAction.class.getName(),
				new GtnFrameworkItemGrpAddAllAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpAddButtonAction.class.getName(),
				new GtnFrameworkItemGrpAddButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpCopyAction.class.getName(),
				new GtnFrameworkItemGrpCopyAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpDeleteAction.class.getName(),
				new GtnFrameworkItemGrpDeleteAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpEditAction.class.getName(),
				new GtnFrameworkItemGrpEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameWorkItemGrpLoadDataTableAction.class.getName(),
				new GtnFrameWorkItemGrpLoadDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpRemoveAction.class.getName(),
				new GtnFrameworkItemGrpRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpRemoveAllAction.class.getName(),
				new GtnFrameworkItemGrpRemoveAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpResetAction.class.getName(),
				new GtnFrameworkItemGrpResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpSaveAction.class.getName(),
				new GtnFrameworkItemGrpSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpDeleteValidationAction.class.getName(),
				new GtnFrameworkItemGrpDeleteValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemGrpTempTableClearAction.class.getName(),
				new GtnFrameworkItemGrpTempTableClearAction());

	}

}
