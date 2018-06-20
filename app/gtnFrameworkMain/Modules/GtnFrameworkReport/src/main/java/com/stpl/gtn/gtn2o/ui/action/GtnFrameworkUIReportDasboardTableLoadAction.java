/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkUIReportDasboardTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(1), componentId);
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnWsReportDashboardBean dashBoardBean = new GtnWsReportDashboardBean();
		dashBoardBean.setSessionId(dataSelectionBean.getSessionId());
		List<Object> salesInclusion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(2).toString(), componentId).getSelectedListFromV8MultiSelect();
		dashBoardBean.setSalesInclusion(
				salesInclusion.size() == 1 ? Integer.parseInt(salesInclusion.get(0).toString()) - 1 : 0);
		List<Object> deductionInclusion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(3).toString(), componentId).getSelectedListFromV8MultiSelect();
		dashBoardBean.setDeductionInclusion(
				salesInclusion.size() == 1 ? Integer.parseInt(deductionInclusion.get(0).toString()) - 1 : 0);
		String annualTotalValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabAnnualTotals", componentId)
				.getCaptionFromV8ComboBox();
		dashBoardBean.setAnnualTotals(annualTotalValue);

		List<Object> displayFormat = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(4).toString(), componentId)
				.getSelectedCaptionListFromV8MultiSelect();
		dashBoardBean.setDisplayFormat(displayFormat.toArray());

		dashBoardBean.setCurrencyConversion(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(5).toString(), componentId).getCaptionFromV8ComboBox());
		grid.getTableConfig().setGtnWsReportDashboardBean(dashBoardBean);
		// GtnUIFrameworkWebServiceClient wsclient = new
		// GtnUIFrameworkWebServiceClient();
		// GtnUIFrameworkWebserviceRequest serviceRequest = new
		// GtnUIFrameworkWebserviceRequest();
		// GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		// GtnWsReportDataSelectionBean dataSelectionBean = new
		// GtnWsReportDataSelectionBean();
		// dataSelectionBean.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		// reportRequest.setDataSelectionBean(dataSelectionBean);
		// serviceRequest.setGtnWsReportRequest(reportRequest);
		// reportRequest.setGtnWsReportDashboardBean(grid.getTableConfig().getGtnWsReportDashboardBean());
		// wsclient.callGtnWebServiceUrl(GtnWsReportConstants.GTN_REPORT_DASHBOARD_GENERATE_REPORT_CALCULATION_INSERT,
		// GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, serviceRequest,
		// GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		componentData.getCurrentGtnComponent().reloadComponent(null, componentId, (String) params.get(1), null);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
