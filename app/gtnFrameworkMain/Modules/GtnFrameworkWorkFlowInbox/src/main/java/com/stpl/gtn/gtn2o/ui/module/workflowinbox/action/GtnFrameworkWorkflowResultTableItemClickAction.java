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
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnFrameworkWorkflowResultTableItemClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkWorkflowResultTableItemClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			if ((GtnWsRecordBean) gtnUIFrameWorkActionConfig.getActionParameterList().get(1) == null) {
				enableDisableButtons(false, false);
				return;
			}
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
			generalWSRequest.setUserId(String.valueOf(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkWorkflowInboxClassConstants.USERID)));
			String combocomponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
					.getCaptionFromComboBox();

			String createdById;
			GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) gtnUIFrameWorkActionConfig.getActionParameterList()
					.get(1);

			String status = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.STATUS));
			if (combocomponent.equals(GtnFrameworkCommonStringConstants.CONTRACT_MANAGEMENT)) {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11));
			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.FORECASTING)) {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(19));

			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ACCRUAL_RATE_PROJECTION)) {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(19));

			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.RETURNS)) {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(20));

			} else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(22));

			} else {

				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11));

			}
			boolean isSubmitter = createdById.equals(userId);

			if (isSubmitter && (GtnFrameworkWorkflowInboxClassConstants.WITHDRAWN.equalsIgnoreCase(status)
					|| GtnFrameworkWorkflowInboxClassConstants.REJECTED.equalsIgnoreCase(status)
					|| GtnFrameworkWorkflowInboxClassConstants.PENDING.equals(status))) {

				enableDisableButtons(false, true);

			} else {

				enableDisableButtons(true, false);
			}

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

	void enableDisableButtons(boolean isView, boolean isOpen) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN)
				.setComponentEnable(isView);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.OPENBTN)
				.setComponentEnable(isOpen);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}

