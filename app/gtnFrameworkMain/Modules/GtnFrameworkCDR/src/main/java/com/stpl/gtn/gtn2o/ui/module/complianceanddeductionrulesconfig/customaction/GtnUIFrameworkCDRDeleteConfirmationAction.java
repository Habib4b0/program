
package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.customaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkCDRDeleteConfirmationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCDRDeleteConfirmationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

			GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
			confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<Object> alertParams = new ArrayList<>();
			alertParams.add(" Delete Confirmation ");
			alertParams.add(" Are you sure you want to Delete the selected record?");

			List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

			GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.DELETE_ACTION);
			List<Object> parameters = new ArrayList<>();
			parameters.add("/" + GtnWsCDRContants.CDR_SERVICE + GtnWsCDRContants.REMOVE_RULE_SERVICE);
			parameters.add("cDRsearchResultTable");
			parameters.add("");
			parameters.add(true);
			parameters.add(8);

			editActionConfig.setActionParameterList(parameters);
			onSucessActionConfigList.add(editActionConfig);

			GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
					.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

			List<Object> actionParams = new ArrayList<>();
			actionParams.add("cDRsearchResultTable");

			loadDataTableActionConfig.setActionParameterList(actionParams);
			loadDataTableActionConfig
					.setFieldValues(Arrays.asList(new String[] { "ruleType", "ruleNo", "ruleName", "ruleCategory" }));

			onSucessActionConfigList.add(loadDataTableActionConfig);

			alertParams.add(onSucessActionConfigList);
			confirmationActionConfig.setActionParameterList(alertParams);
			GtnUIFrameWorkAction action = confirmationActionConfig.getActionType().getGtnUIFrameWorkAction();
			action.configureParams(confirmationActionConfig);

			action.doAction(componentId, confirmationActionConfig);

		} catch (Exception ex) {
			logger.error("Delete Confirmation failed", ex);
			throw new GtnFrameworkGeneralException(ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRDeleteConfirmationAction();
	}

}
