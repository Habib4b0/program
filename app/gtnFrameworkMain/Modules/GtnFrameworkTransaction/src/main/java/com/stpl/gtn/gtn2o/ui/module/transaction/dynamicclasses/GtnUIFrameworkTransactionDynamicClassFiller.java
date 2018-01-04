package com.stpl.gtn.gtn2o.ui.module.transaction.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactionTableColumnFormatAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactioneRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionAlertAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionCustomResultViewAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionInvalidIntegrationLoadAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionInvalidViewLoadAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionReprocessRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionResetBeanAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionViewAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionGeneratedCoumnAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionRefreshBeanAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.validation.GtnFrameworkTransactionReprocessRemoveValidation;

public class GtnUIFrameworkTransactionDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionGeneratedCoumnAction.class.getName(),
				new GtnUIFrameworkTransactionGeneratedCoumnAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionRefreshBeanAction.class.getName(),
				new GtnUIFrameworkTransactionRefreshBeanAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionTableCheckAction.class.getName(),
				new GtnUIFrameworkTransactionTableCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionTableCheckAllAction.class.getName(),
				new GtnUIFrameworkTransactionTableCheckAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkTransactionReprocessRemoveValidation.class.getName(),
				new GtnFrameworkTransactionReprocessRemoveValidation());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionAlertAction.class.getName(),
				new GtnUIFrameworkTransactionAlertAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionCustomResultViewAction.class.getName(),
				new GtnUIFrameworkTransactionCustomResultViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionEnableDisableAction.class.getName(),
				new GtnUIFrameworkTransactionEnableDisableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTransactioneRecordTypeAction.class.getName(),
				new GtnUIFrameWorkTransactioneRecordTypeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameworkTransactionInvalidIntegrationLoadAction.class.getName(),
				new GtnUIFrameworkTransactionInvalidIntegrationLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionInvalidViewLoadAction.class.getName(),
				new GtnUIFrameworkTransactionInvalidViewLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionReprocessRemoveAction.class.getName(),
				new GtnUIFrameworkTransactionReprocessRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionResetBeanAction.class.getName(),
				new GtnUIFrameworkTransactionResetBeanAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTransactionViewAction.class.getName(),
				new GtnUIFrameworkTransactionViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTransactionTableColumnFormatAction.class.getName(),
				new GtnUIFrameWorkTransactionTableColumnFormatAction());

	}

}
