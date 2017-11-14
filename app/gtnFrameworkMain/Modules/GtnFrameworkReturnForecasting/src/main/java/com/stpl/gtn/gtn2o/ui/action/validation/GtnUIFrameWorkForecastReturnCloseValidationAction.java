package com.stpl.gtn.gtn2o.ui.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

/**
 *
 * @author Stpl
 */
public class GtnUIFrameWorkForecastReturnCloseValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String saveConfirmationMsgHeader = GtnFrameworkForecastAlertMsgConstants.RETURNS_SAVE_CONFRMATION_MSG_HEADER;
		String saveConfirmationMsg = GtnFrameworkForecastAlertMsgConstants.RETURNS_SAVE_CONFRMATION_MSG;

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
		GtnForecastBean projMasterBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		if (projMasterBean.isViewModeFlag() || projMasterBean.isWorkflowFlag()) {
			saveConfirmationMsgHeader = "Close Confirmation";
			saveConfirmationMsg = "Are you sure you want to close this Projection?";
		}
		GtnUIFrameWorkActionConfig closeConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		List<GtnUIFrameWorkActionConfig> saveActionList = new ArrayList<>();
		if (!projMasterBean.isMultipleSaveFalg() && !projMasterBean.isEditMode() && !projMasterBean.isViewModeFlag()
				&& !projMasterBean.isWorkflowFlag()) {
			List<Object> closeConfirmationParams = new ArrayList<>();
			closeConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			closeConfirmationParams.add(GtnFrameworkForecastAlertMsgConstants.RETURNS_CLOSE_CONFRMATION_MSG_HEADER);
			closeConfirmationParams
					.add(GtnFrameworkForecastAlertMsgConstants.RETURNS_CLOSE_AND_CONTIUE_CONFRMATION_MSG);
			closeConfirmationParams.add(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
			closeConfirmationActionConfig.setActionParameterList(closeConfirmationParams);
			saveActionList.add(closeConfirmationActionConfig);
		} else if (projMasterBean.isWorkflowFlag()) {
			saveActionList = ((List<GtnUIFrameWorkActionConfig>) gtnUIFrameWorkActionConfig.getActionParameterList()
					.get(1)).subList(4, 5);
		} else {
			saveActionList = ((List<GtnUIFrameWorkActionConfig>) gtnUIFrameWorkActionConfig.getActionParameterList()
					.get(1)).subList(0, 4);
		}

		GtnUIFrameWorkActionConfig saveConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkConfirmationAction saveValidationAction = new GtnUIFrameWorkConfirmationAction();
		saveConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> saveConfirmationParams = new ArrayList<>();
		saveConfirmationParams.add(saveConfirmationMsgHeader);
		saveConfirmationParams.add(saveConfirmationMsg);
		saveConfirmationParams.add(saveActionList);
		saveConfirmationActionConfig.setActionParameterList(saveConfirmationParams);
		saveValidationAction.doAction(componentId, saveConfirmationActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
