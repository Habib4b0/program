package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnUIFrameWorkResetOnEditModeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = yesButtonClickEvent();

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkConfirmationAction confirmationAction = new GtnUIFrameWorkConfirmationAction();
		confirmationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "Reset Confirmation",
				"Are you sure you want to reset the listview to default/previous values?", finalYesActionConfigList }));
		confirmationAction.doAction(componentId, confirmationActionConfig);

	}

	private List<GtnUIFrameWorkActionConfig> yesButtonClickEvent() {

		GtnUIFrameWorkActionConfig yesActionConfig = new GtnUIFrameWorkActionConfig();
		yesActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yesActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameWorkResetYesButtonAction.class.getName(), "RESET_ALL" }));
		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = new ArrayList<>();
		finalYesActionConfigList.add(yesActionConfig);
		return finalYesActionConfigList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
