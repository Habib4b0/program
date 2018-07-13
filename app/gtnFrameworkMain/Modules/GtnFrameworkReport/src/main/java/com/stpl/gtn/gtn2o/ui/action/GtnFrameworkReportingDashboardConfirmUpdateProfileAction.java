package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkReportingDashboardConfirmUpdateProfileAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass{

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportingDashboardConfirmUpdateProfileAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameWorkActionConfig reportSaveProfileConfirmUpdateAction = new GtnUIFrameWorkActionConfig();
		reportSaveProfileConfirmUpdateAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		reportSaveProfileConfirmUpdateAction.addActionParameter("Update Confirmation");
		reportSaveProfileConfirmUpdateAction.addActionParameter("Are you sure you want to update the current Report View?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportSaveProfileUpdateViewAction = new GtnUIFrameWorkActionConfig();
		reportSaveProfileUpdateViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportSaveProfileUpdateViewAction.addActionParameter(GtnReportingDashboardUpdateProfileAddAction.class.getName());
		reportSaveProfileUpdateViewAction.addActionParameter(parameters.get(1).toString());
		reportSaveProfileUpdateViewAction.addActionParameter(parameters.get(2).toString());
		reportSaveProfileUpdateViewAction.addActionParameter(parameters.get(3).toString());
		successActionConfigList.add(reportSaveProfileUpdateViewAction);
		reportSaveProfileConfirmUpdateAction.addActionParameter(successActionConfigList);	
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportSaveProfileConfirmUpdateAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
