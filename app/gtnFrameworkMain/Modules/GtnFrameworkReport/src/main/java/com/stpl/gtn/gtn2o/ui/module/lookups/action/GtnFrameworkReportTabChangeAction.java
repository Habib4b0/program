package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.TabSheet;

public class GtnFrameworkReportTabChangeAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportTabChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Tab Change Action Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceViewId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		TabSheet tabsheet = (TabSheet) GtnUIFrameworkGlobalUI
				.getVaadinComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1), sourceViewId);
		tabsheet.setSelectedTab(
				Integer.parseInt(gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString()));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
