package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
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

			GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
			Map<String, String> captionMap = new HashMap<>();
			captionMap.put(componentId, "UPDATE");
			changeCaptionActionConfig.setActionParameterList(Arrays.asList(new Object[] { captionMap }));
			onSucessActionConfig.add(changeCaptionActionConfig);

			GtnUIFrameWorkActionConfig notificationActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			List<Object> notificationParams = new ArrayList<>();
			notificationParams
					.add(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleName").getStringFromField()
							+ " has been saved sucessfully ");
			notificationParams.add("");

			notificationActionConfig.setActionParameterList(notificationParams);
			onSucessActionConfig.add(notificationActionConfig);

			alertParams.add(onSucessActionConfig);

			confirmationActionConfig.setActionParameterList(alertParams);
			GtnUIFrameWorkAction action = confirmationActionConfig.getActionType().getGtnUIFrameWorkAction();
			action.configureParams(confirmationActionConfig);
			action.doAction("", confirmationActionConfig);

		} catch (GtnFrameworkGeneralException ex) {
			throw ex;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRSaveConfirmationAction();
	}

}
