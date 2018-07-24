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

public class GtnFrameworkReportConfirmedCloseAction  implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass{
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportConfirmedCloseAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params..");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmCloseAction = new GtnUIFrameWorkActionConfig();
		confirmCloseAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmCloseAction.addActionParameter("Close Confirmation");
		confirmCloseAction.addActionParameter("Are you sure you want to close the popup?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		successActionConfigList.add(closeAction);
		confirmCloseAction.addActionParameter(successActionConfigList);	
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmCloseAction);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}


}
