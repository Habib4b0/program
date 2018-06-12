package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkReportDashBoardRightHeaderRequestAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	{

	}
	private final GtnWSLogger gtnWsLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnWsLogger.info(" inside GtnFrameworkConfigureRightTableHeaderForPTTCompoAction");

		// GtnForecastBean gtnForecastBean = new GtnForecastBean();
		// GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new
		// GtnUIFrameworkWebserviceRequest();
		//
		// gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(new
		// GtnWsReportRequest());
		//
		// GtnWsReportDashboardBean reportDashBoardBean = new
		// GtnWsReportDashboardBean();
		//
		// gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().setGtnWsReportDashboardBean(reportDashBoardBean);
		//
		// String[] selectedVariable = GtnUIFrameworkGlobalUI
		// .getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable",
		// componentId)
		// .getStringFromMultiselectComboBox();
		//
		// reportDashBoardBean.setSelectedVariableType(selectedVariable);
		//
		// String[] selectedVariableCategory = GtnUIFrameworkGlobalUI
		// .getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariableCategory",
		// componentId)
		// .getStringFromMultiselectComboBox();
		//
		// reportDashBoardBean.setSelectedVariableCategoryType(selectedVariableCategory);
		//
		// GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		// gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		// gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(gtnWsForecastRequest);
		//
		// GtnUIFrameworkComponentData resultTableComponentData =
		// GtnUIFrameworkGlobalUI.getVaadinComponentData(
		// gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(),
		// componentId);
		// resultTableComponentData.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}