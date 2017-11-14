package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkSelectBtnRecordClickTableAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkSelectBtnRecordClickTableAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkSelectBtnRecordClickTableAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE)
				.getValueFromPagedDataTable();
		String combocomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
				.getCaptionFromComboBox();
		String workflowSid = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		try {

			if (combocomponent.equals(GtnFrameworkCommonStringConstants.CONTRACT_MANAGEMENT)) {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(8));
			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.FORECASTING)) {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ACCRUAL_RATE_PROJECTION)) {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.RETURNS)) {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
			} else {
				workflowSid = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(8));
			}

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID, componentId)
					.loadFieldValue(workflowSid);

		} catch (

		Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
