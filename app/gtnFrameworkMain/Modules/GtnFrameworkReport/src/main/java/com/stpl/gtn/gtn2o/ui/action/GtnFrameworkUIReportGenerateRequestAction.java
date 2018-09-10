package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
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

		String freName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
				.getStringCaptionFromV8ComboBox();

		reportDashBoardBean.setSelectFreqString(freName);

		GtnUIFrameworkBaseComponent perioFromComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);

		GtnUIFrameworkBaseComponent periodToComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);

		GtnUIFrameworkBaseComponent comparisonBasis = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId);
		int id = comparisonBasis.getIntegerFromV8ComboBox();
		String comparisonBasisValue = id > 2 ? "Projection_" + (id - 2)
				: comparisonBasis.getStringCaptionFromV8ComboBox();

		reportDashBoardBean.setComparisonBasis(comparisonBasisValue);
		reportDashBoardBean.setPeriodRangeFromSid(perioFromComponent.getIntegerFromV8ComboBox());

		if (reportDashBoardBean.getPeriodRangeFromSid() != 0) {
			reportDashBoardBean
					.setPeriodStart(perioFromComponent.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		} else {
			reportDashBoardBean.setPeriodRangeFromSid(perioFromComponent.getNthIntegerFromV8ComboBox(1));
			reportDashBoardBean
					.setPeriodStart(perioFromComponent.getNthStringCaptionFromV8ComboBox(1).replaceAll(" - ", " "));
		}

		reportDashBoardBean.setPeriodRangeToSid(periodToComponent.getIntegerFromV8ComboBox());
		if (reportDashBoardBean.getPeriodRangeToSid() != 0) {
			reportDashBoardBean.setPeriodTo(periodToComponent.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		} else {
			reportDashBoardBean.setPeriodRangeToSid(
					periodToComponent.getNthIntegerFromV8ComboBox(periodToComponent.totalItemsInComboBox() - 1));
			reportDashBoardBean.setPeriodTo(
					periodToComponent.getNthStringCaptionFromV8ComboBox(periodToComponent.totalItemsInComboBox() - 1)
							.replaceAll(" - ", " "));
		}

		GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).setCustomPagedTreeTableRequest(serviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
