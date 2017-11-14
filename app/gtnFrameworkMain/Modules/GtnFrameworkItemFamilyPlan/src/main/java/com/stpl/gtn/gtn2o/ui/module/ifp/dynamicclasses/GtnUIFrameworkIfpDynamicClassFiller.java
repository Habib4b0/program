package com.stpl.gtn.gtn2o.ui.module.ifp.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpAddAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpCompaniesMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpEditAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpInfoDesignationValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemAdditionSearchValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemsDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemsRecordChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemsTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpItemsTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpResetAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpSaveAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpTableFieldFactory;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFramworkItemCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction.GtnFrameworkIfpMoveAllLeftAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction.GtnFrameworkIfpMoveAllRightAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction.GtnFrameworkIfpMoveLeftAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction.GtnFrameworkIfpMoveRightAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.validation.GtnFrameworkIfpCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.validation.GtnFrameworkIfpIdAndNoValidationAction;

public class GtnUIFrameworkIfpDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpMoveAllLeftAction.class.getName(),
				new GtnFrameworkIfpMoveAllLeftAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpMoveAllRightAction.class.getName(),
				new GtnFrameworkIfpMoveAllRightAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpMoveLeftAction.class.getName(),
				new GtnFrameworkIfpMoveLeftAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpMoveRightAction.class.getName(),
				new GtnFrameworkIfpMoveRightAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpCommonValidationAction.class.getName(),
				new GtnFrameworkIfpCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpIdAndNoValidationAction.class.getName(),
				new GtnFrameworkIfpIdAndNoValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpAddAction.class.getName(),
				new GtnFrameworkIfpAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkIfpCompaniesMassFieldValueChangeAction.class.getName(),
				new GtnFrameworkIfpCompaniesMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpDeleteAction.class.getName(),
				new GtnFrameworkIfpDeleteAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpEditAction.class.getName(),
				new GtnFrameworkIfpEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpInfoDesignationValueChangeAction.class.getName(),
				new GtnFrameworkIfpInfoDesignationValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction.class.getName(),
				new GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkIfpItemAdditionSearchValueChangeAction.class.getName(),
				new GtnFrameworkIfpItemAdditionSearchValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpItemsDeleteAction.class.getName(),
				new GtnFrameworkIfpItemsDeleteAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpItemsRecordChangeAction.class.getName(),
				new GtnFrameworkIfpItemsRecordChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpItemsTabPopulateAction.class.getName(),
				new GtnFrameworkIfpItemsTabPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpItemsTabTableCheckAction.class.getName(),
				new GtnFrameworkIfpItemsTabTableCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpResetAction.class.getName(),
				new GtnFrameworkIfpResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpSaveAction.class.getName(),
				new GtnFrameworkIfpSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpTabChangeAction.class.getName(),
				new GtnFrameworkIfpTabChangeAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkIfpTableFieldFactory.class.getName(),
				new GtnFrameworkIfpTableFieldFactory());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFramworkItemCheckAllAction.class.getName(),
				new GtnFramworkItemCheckAllAction());

	}

}
