package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

public class GtnFrameworkUIReportGenerateRequestAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData()
				.getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = ((GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData());
		serviceRequest.setGtnWsReportRequest(reportRequest);
		reportRequest.setDataSelectionBean(dataSelectionBean);
		GtnUIFrameworkPagedTreeTableConfig tableConfig = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId)
				.getCurrentComponentConfig().getGtnPagedTreeTableConfig();
		dataSelectionBean.setSessionId(tableConfig.getGtnWsReportDashboardBean().getSessionId());
		reportRequest.setGtnWsReportDashboardBean(tableConfig.getGtnWsReportDashboardBean());

		GtnWsReportDashboardBean reportDashBoardBean = tableConfig.getGtnWsReportDashboardBean();

		serviceRequest.getGtnWsReportRequest().setGtnWsReportDashboardBean(reportDashBoardBean);

		String freName = dataSelectionBean.getFrequencyName();

		reportDashBoardBean.setSelectFreqString(freName);

		GtnUIFrameworkBaseComponent perioFromComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);

		GtnUIFrameworkBaseComponent periodToComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);

		List<GtnReportComparisonProjectionBean> selectedProj = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
				.getComponentData().getCustomData();

		reportDashBoardBean.setPeriodRangeFromSid(perioFromComponent.getIntegerFromV8ComboBox());

		if (reportDashBoardBean.getPeriodRangeFromSid() != 0) {
			reportDashBoardBean.setPeriodStart(perioFromComponent.getStringCaptionFromV8ComboBox());
		} else {
			GtnUIFrameworkBaseComponent dataslectionFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId);
			reportDashBoardBean.setPeriodRangeFromSid(dataslectionFrom.getIntegerFromV8ComboBox());
			reportDashBoardBean.setPeriodStart(dataslectionFrom.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		}

		reportDashBoardBean.setPeriodRangeToSid(periodToComponent.getIntegerFromV8ComboBox());
		if (reportDashBoardBean.getPeriodRangeToSid() != 0) {
			reportDashBoardBean.setPeriodTo(periodToComponent.getStringCaptionFromV8ComboBox());
		} else {
			GtnUIFrameworkBaseComponent dataslectionTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId);
			reportDashBoardBean.setPeriodRangeToSid(dataslectionTo.getIntegerFromV8ComboBox());
			reportDashBoardBean.setPeriodTo(dataslectionTo.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		}

		GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).setCustomPagedTreeTableRequest(serviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
