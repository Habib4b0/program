
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSetDefaultAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkRPDeleteConfirmationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig confirmationActionConfigRp = new GtnUIFrameWorkActionConfig();
		confirmationActionConfigRp.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsRp = new ArrayList<>();
		alertParamsRp.add(" Delete Confirmation ");
		alertParamsRp.add(" Are you sure you want to delete record  " + GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanName").getStringFromField() + "?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editActionConfigRp = new GtnUIFrameWorkActionConfig();
		editActionConfigRp.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		List<Object> parametersList = new ArrayList<>();
		parametersList.add(GtnFrameworkRPConstants.REBATE_PLAN_DELETE_ACTION);
		parametersList.add("/" + GtnWsCDRContants.RP_SERVICE + GtnWsCDRContants.REMOVE_RP_REC);
		parametersList.add(GtnFrameworkCommonConstants.RP_SEARCH_RESULT_TABLE);
		parametersList.add("");
		parametersList.add(Boolean.TRUE);
		parametersList.add(14);

		editActionConfigRp.setActionParameterList(parametersList);
		onSucessActionConfigList.add(editActionConfigRp);

		GtnUIFrameWorkActionConfig rsNotificationAction = new GtnUIFrameWorkActionConfig();
		rsNotificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		String message = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanName")
				.getStringFromField() + " has been deleted successfully";
		rsNotificationAction.addActionParameter(message);
		rsNotificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(rsNotificationAction);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.RP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList("rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType"));

		onSucessActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig defaultAction = new GtnUIFrameWorkActionConfig();
		defaultAction.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(GtnFrameworkCommonConstants.RP_SEARCH_RESULT_TABLE, "");
		selectResetParams.add(resetSelectMap);
		defaultAction.setActionParameterList(selectResetParams);
		onSucessActionConfigList.add(defaultAction);

		alertParamsRp.add(onSucessActionConfigList);
		confirmationActionConfigRp.setActionParameterList(alertParamsRp);
		GtnUIFrameWorkAction action = confirmationActionConfigRp.getActionType().getGtnUIFrameWorkAction();
		action.configureParams(confirmationActionConfigRp);

		action.doAction(componentId, confirmationActionConfigRp);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setDefaultAction(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkSetDefaultAction defaultAction = new GtnUIFrameWorkSetDefaultAction();
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(GtnFrameworkCommonConstants.RP_SEARCH_RESULT_TABLE, "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		defaultAction.doAction(componentId, resetTableConfig);
	}
}
