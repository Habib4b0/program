package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkCDRSaveConfirmationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

			GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
			confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<Object> alertParams = new ArrayList<>();
			alertParams.add(" Save Confirmation ");
			alertParams.add(" Save record "
					+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleName").getStringFromField()
					+ "?");

			List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

			GtnUIFrameWorkActionConfig custoSavemAction = new GtnUIFrameWorkActionConfig();
			custoSavemAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

			custoSavemAction.setActionParameterList(Arrays.asList(new Object[] {
					"com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.customaction.GtnUIFrameworkComplianceAndDeductionRulesSaveAction" }));

			onSucessActionConfig.add(custoSavemAction);


			GtnUIFrameWorkActionConfig notificationActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			List<Object> notificationParams = new ArrayList<>();
			notificationParams
					.add(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleName").getStringFromField()
							+ " has been saved sucessfully ");
			notificationParams.add("");

			notificationActionConfig.setActionParameterList(notificationParams);
			onSucessActionConfig.add(notificationActionConfig);

			GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
			List<Object> paramsList = new ArrayList<>();
			paramsList.add(Arrays.asList("cDRAddSaveButton"));
			paramsList.add(Arrays.asList("UPDATE"));
			changeCaptionActionConfig.setActionParameterList(paramsList);
			onSucessActionConfig.add(changeCaptionActionConfig);


			alertParams.add(onSucessActionConfig);

			confirmationActionConfig.setActionParameterList(alertParams);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmationActionConfig);

		} catch (GtnFrameworkGeneralException ex) {
			throw ex;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRSaveConfirmationAction();
	}

}
