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
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		serviceRequest.setGtnWsReportRequest(reportRequest);
		reportRequest.setDataSelectionBean(dataSelectionBean);
		GtnUIFrameworkPagedTreeTableConfig tableConfig = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId)
				.getCurrentComponentConfig().getGtnPagedTreeTableConfig();
		dataSelectionBean.setSessionId(tableConfig.getGtnWsReportDashboardBean().getSessionId());
		reportRequest.setGtnWsReportDashboardBean(tableConfig.getGtnWsReportDashboardBean());

		serviceRequest.setGtnWsReportRequest(new GtnWsReportRequest());

		GtnWsReportDashboardBean reportDashBoardBean = new GtnWsReportDashboardBean();

		serviceRequest.getGtnWsReportRequest().setGtnWsReportDashboardBean(reportDashBoardBean);

		String freName = ((GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getComponentData().getSharedPopupData()).getFrequencyName();
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
		}

		reportDashBoardBean.setPeriodRangeToSid(periodToComponent.getIntegerFromV8ComboBox());
		if (reportDashBoardBean.getPeriodRangeToSid() != 0) {
			reportDashBoardBean.setPeriodTo(periodToComponent.getStringCaptionFromV8ComboBox());
		}

		GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).setCustomPagedTreeTableRequest(serviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
