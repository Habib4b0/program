/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAliasAddAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAliasAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			List<String> parameters = (List<String>) mainParameters.get(1);
			GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
			validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
			validationActionConfig
					.setFieldValues(Arrays.asList(parameters.get(2), parameters.get(3), parameters.get(4)));

			GtnUIFrameWorkActionConfig failActionConfig = new GtnUIFrameWorkActionConfig();
			failActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			failActionConfig.addActionParameter(GtnFrameworkErrorLabelAction.class.getName());
			failActionConfig.addActionParameter(mainParameters.get(5));
			failActionConfig.addActionParameter(Arrays.asList(parameters.get(2), parameters.get(3), parameters.get(4)));
			failActionConfig.addActionParameter(
					Arrays.asList(mainParameters.get(2), mainParameters.get(3), mainParameters.get(4)));

			GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
			addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			addActionConfig.addActionParameter(GtnFrameworkAliasConfirmAddAction.class.getName());
			addActionConfig.setFieldValues(parameters);
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(Arrays.asList(parameters.get(0), parameters.get(1), parameters.get(2),
					parameters.get(3), parameters.get(4), parameters.get(5)));
			resetActionConfig.addActionParameter(Arrays.asList("", "", "", null, null, null));

			validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
			validationActionConfig.addActionParameter(Arrays.asList(failActionConfig));
			validationActionConfig
					.addActionParameter(Arrays.asList(failActionConfig, addActionConfig, resetActionConfig));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, validationActionConfig);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkAliasAddAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
