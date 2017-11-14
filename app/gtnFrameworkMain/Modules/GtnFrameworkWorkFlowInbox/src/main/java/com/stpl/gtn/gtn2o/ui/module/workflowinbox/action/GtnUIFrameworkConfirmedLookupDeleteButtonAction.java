package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;

public class GtnUIFrameworkConfirmedLookupDeleteButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsWorkflowInboxBean projMasterBean = new GtnWsWorkflowInboxBean();
		String viewType = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE)
				.getValueFromPagedDataTable();

		String workflowinboxSid;

		workflowinboxSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11));

		projMasterBean.setWorkflowinboxSid(Integer.valueOf(workflowinboxSid));

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
