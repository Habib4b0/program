package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkReturnProjectionTabResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkReturnProjectionTabResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GtnFrameworkReturnProjectionTabResetAction");

		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = yesButtonClickEvent(gtnUIFrameWorkActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkConfirmationAction confirmationAction = new GtnUIFrameWorkConfirmationAction();
		confirmationActionConfig
				.setActionParameterList(Arrays.asList(GtnFrameworkForecastAlertMsgConstants.RESET_MESSAGE_HEADER,
						GtnFrameworkForecastAlertMsgConstants.RESET_MESSAGE_HEADER_MSG, finalYesActionConfigList));
		confirmationAction.doAction(componentId, confirmationActionConfig);
	}

	private List<GtnUIFrameWorkActionConfig> yesButtonClickEvent(
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {

		GtnUIFrameWorkActionConfig yesActionConfig = new GtnUIFrameWorkActionConfig();
		yesActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yesActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_RESET_BUTTON_CUSTOM_ACTION,
						gtnUIFrameWorkActionConfig));
		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = new ArrayList<>();
		finalYesActionConfigList.add(yesActionConfig);

		return finalYesActionConfigList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
