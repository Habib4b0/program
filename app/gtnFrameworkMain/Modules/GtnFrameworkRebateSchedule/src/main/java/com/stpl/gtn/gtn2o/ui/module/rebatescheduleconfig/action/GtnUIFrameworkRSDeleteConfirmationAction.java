
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkRSDeleteConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

		final List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.DELETE_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add("/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_DELECTE_SERVICE);
		parameters.add("rsSearchResultTable");
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(30);

		editActionConfig.setActionParameterList(parameters);
		onSucessActionConfigList.add(editActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
		notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		String message = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1").getStringFromField()
				+ " has been deleted successfully";
		notificationAction.addActionParameter(message);
		notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(notificationAction);
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Delete Confirmation ");
		alertParams.add(" Are you sure you want to delete record "
				+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1").getStringFromField() + "?");
		alertParams.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParams);
		GtnUIFrameWorkAction action = confirmationActionConfig.getActionType().getGtnUIFrameWorkAction();
		action.configureParams(confirmationActionConfig);

		action.doAction(componentId, confirmationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
