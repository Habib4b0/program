package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnForecastEligibleDateLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest reportForecastEligibleDateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse reportForecastEligibleDateResponse = new GtnUIFrameworkWebServiceClient()
				.callGtnWebServiceUrl(
						GtnWsReportConstants.GTN_REPORT_SERVICE
								+ GtnWsReportConstants.GTN_REPORT_LOADELIGIBLEDATE_SERVICE,
						"report", reportForecastEligibleDateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		Date reportForecastEligibleDateDate = reportForecastEligibleDateResponse
				.getGtnUIFrameworkWebserviceDateResponse().getResultValue();
		Optional.ofNullable(reportForecastEligibleDateDate).ifPresent(d -> {
			LocalDateTime localDate = reportForecastEligibleDateDate.toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			LocalDate reportForecastEligibleDateLocalDate = localDate.toLocalDate();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())
					.loadV8DateValue(reportForecastEligibleDateLocalDate);
		});
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
