package com.stpl.gtn.gtn2o.ui.module.workflowinbox.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworPopulatefromTableAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkAddJSListenerAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureApprovedByAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureBusinesLookUpAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureBusinessProcessDdlbAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureHistoryButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureOpenButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkDownloadattachmentAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkFetchHistorytoAttachmentAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkSelectBtnRecordClickTableAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkUpdateTableJSListenerAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkFlowPopupTableLoadAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowInboxARMDeductionLevelValueAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowInboxDeductionLevelValueAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowPopulateFieldsAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowResultTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnUIFrameworkConfirmedLookupDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnUIFrameworkUrlDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud.GtnFrameworkConfigureSaveProfilelookupAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud.GtnFrameworkWorkflowPublicPrivateViewSaveAction;

public class GtnUIFrameworkWorkflowInboxDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureSaveProfilelookupAction.class.getName(),
				new GtnFrameworkConfigureSaveProfilelookupAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkWorkflowPublicPrivateViewSaveAction.class.getName(),
				new GtnFrameworkWorkflowPublicPrivateViewSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureApprovedByAction.class.getName(),
				new GtnFrameworkConfigureApprovedByAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureBusinesLookUpAction.class.getName(),
				new GtnFrameworkConfigureBusinesLookUpAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureBusinessProcessDdlbAction.class.getName(),
				new GtnFrameworkConfigureBusinessProcessDdlbAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkWorkFlowPopupTableLoadAction.class.getName(),
				new GtnFrameworkWorkFlowPopupTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureHistoryButtonAction.class.getName(),
				new GtnFrameworkConfigureHistoryButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfigureOpenButtonAction.class.getName(),
				new GtnFrameworkConfigureOpenButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDownloadattachmentAction.class.getName(),
				new GtnFrameworkDownloadattachmentAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFetchHistorytoAttachmentAction.class.getName(),
				new GtnFrameworkFetchHistorytoAttachmentAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSelectBtnRecordClickTableAction.class.getName(),
				new GtnFrameworkSelectBtnRecordClickTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkWorkflowInboxARMDeductionLevelValueAction.class.getName(),
				new GtnFrameworkWorkflowInboxARMDeductionLevelValueAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkWorkflowInboxDeductionLevelValueAction.class.getName(),
				new GtnFrameworkWorkflowInboxDeductionLevelValueAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkWorkflowResultTableItemClickAction.class.getName(),
				new GtnFrameworkWorkflowResultTableItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkWorkflowPopulateFieldsAction.class.getName(),
				new GtnFrameworkWorkflowPopulateFieldsAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworPopulatefromTableAction.class.getName(),
				new GtnFrameworPopulatefromTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkConfirmedLookupDeleteButtonAction.class.getName(),
				new GtnUIFrameworkConfirmedLookupDeleteButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkUrlDeleteButtonAction.class.getName(),
				new GtnUIFrameworkUrlDeleteButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddJSListenerAction.class.getName(),
				new GtnFrameworkAddJSListenerAction());
                GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUpdateTableJSListenerAction.class.getName(),
				new GtnFrameworkUpdateTableJSListenerAction());

	}

}
