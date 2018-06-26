package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnReportForecastEligibleDateReloadInReportingDashboardAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportForecastEligibleDateReloadInReportingDashboardAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params " );
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();	
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		Date forecastEligibleDate = dataSelectionBean.getForecastEligibleDate();
		LocalDateTime local = forecastEligibleDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDate localDate = local.toLocalDate();
		GtnUIFrameworkBaseComponent forecastEligiblityDateBaseComponent =  GtnUIFrameworkGlobalUI
		.getVaadinBaseComponent("dataSelectionTab_customerSelectionForecastEligibilityDate",componentId);
		forecastEligiblityDateBaseComponent
		.loadV8DateValue(localDate);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
