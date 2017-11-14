package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.periodconfig.constants.GtnWsPeriodConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;

public class GtnFrameworkPeriodConfigSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		savePeriodConfiguration(parameters, componentId);
	}

	private void savePeriodConfiguration(final List<Object> parameters, String componentId)
			throws GtnFrameworkGeneralException {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsPeriodConfigurationRequest periodConfigurationRequest;

		periodConfigurationRequest = (GtnWsPeriodConfigurationRequest) parameters.get(1);

		request.setGtnWsPeriodConfigurationRequest(periodConfigurationRequest);
		wsclient.callGtnWebServiceUrl(
				GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE_COUNT
						+ GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameWorkActionConfig reloadTable = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		reloadTable.addActionParameter("PCView_loadResultTable");
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reloadTable);
	}

	@Override
	public GtnFrameworkPeriodConfigSaveAction createInstance() {
		return new GtnFrameworkPeriodConfigSaveAction();
	}

}
