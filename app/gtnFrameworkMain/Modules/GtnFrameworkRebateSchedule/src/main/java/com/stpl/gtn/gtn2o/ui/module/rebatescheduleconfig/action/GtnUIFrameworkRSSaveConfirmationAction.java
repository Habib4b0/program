
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkRSSaveConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Save Confirmation ");
		alertParams.add(" Save record "
				+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1").getStringFromField() + "?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		String rsName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1").getStringFromField();

		GtnUIFrameWorkActionConfig custoSavemAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		custoSavemAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRSConstants.RS_SAVE_ACTION }));

		onSucessActionConfig.add(custoSavemAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		List<String> componentIdList = new ArrayList<>();
		componentIdList.add("rsAddSaveButton");
		List<String> captionList = new ArrayList<>();
		captionList.add("UPDATE");

		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(componentIdList);
		actionParameterList.add(captionList);

		changeCaptionActionConfig.setActionParameterList(actionParameterList);
		onSucessActionConfig.add(changeCaptionActionConfig);

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.EDIT }));

		onSucessActionConfig.add(setModeActionConfig);
		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { "priceScheduleAddViewAAddDeleteButton" };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));

		visibleAction.setActionParameterList(visibleParameters);
		onSucessActionConfig.add(visibleAction);
		GtnUIFrameWorkActionConfig notificationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationActionList = new ArrayList<>();
		notificationActionList.add(rsName + " has been successfully saved");
		notificationActionList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		notificationActionList.add(1);
		notificationAction.setActionParameterList(notificationActionList);
		onSucessActionConfig.add(notificationAction);
		alertParams.add(onSucessActionConfig);
		confirmationActionConfig.setActionParameterList(alertParams);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmationActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
