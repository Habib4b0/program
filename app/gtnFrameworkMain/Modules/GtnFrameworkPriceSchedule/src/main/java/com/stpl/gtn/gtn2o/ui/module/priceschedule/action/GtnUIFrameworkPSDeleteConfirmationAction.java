/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPSDeleteConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Delete Confirmation ");
		alertParams.add(" Are you sure you want to delete record  "
				+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1").getStringFromField() + "?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.DELETE_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add("/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_DELECTE_SERVICE);
		parameters.add("psSearchResultTable");
		parameters.add("");
		parameters.add(true);
		parameters.add(0);

		editActionConfig.setActionParameterList(parameters);
		onSucessActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("psSearchResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList("priceScheduleType", "priceScheduleStatus",
				"priceScheduleName", "priceScheduleNo", "priceScheduleId", "itemId", "itemNo", "itemName"));
		onSucessActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
		notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		String message = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleName1").getStringFromField()
				+ " has been deleted successfully";
		notificationAction.addActionParameter(message);
		notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(notificationAction);
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
