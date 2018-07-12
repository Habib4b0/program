package com.stpl.gtn.gtn2o.ui.action;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkReportFilterGenerateLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnWsReportDashboardBean dashboardBean = new GtnWsReportDashboardBean();

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(5), componentId);
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();

		grid.getTableConfig().setGtnWsReportDashboardBean(dashboardBean);

		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnWsReportRequest(reportRequest);
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();
		filterBean
				.setDeductionLevelNo(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)), componentId)
						.getIntegerFromV8ComboBox());
		filterBean
				.setSelectedCustomerList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(2)), componentId)
						.getSelectedListFromV8MultiSelect());
		filterBean
				.setSelectedProductList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(3)), componentId)
						.getSelectedListFromV8MultiSelect());
		filterBean
				.setSelectedDeductionList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(4)), componentId)
						.getSelectedListFromV8MultiSelect());
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE + GtnWsReportConstants.GTN_WS_FILTERCCP_GENERATE_SERVICE,
				"report", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		Set<Object> ccpDetailsSidList = null;
		Set<Object> rsContractSidList = null;
		Set<String> filteredHierarchy = null;
		if (response != null) {
			for (GtnUIFrameworkDataRow result : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				if (filterBean.getSelectedDeductionList().isEmpty() && !result.getColList().isEmpty()) {
					ccpDetailsSidList = Optional.ofNullable(ccpDetailsSidList).orElseGet(HashSet::new);
					filteredHierarchy = Optional.ofNullable(filteredHierarchy).orElseGet(HashSet::new);
					ccpDetailsSidList.add(result.getColumnVAlue(0));
					filteredHierarchy.add(result.getColumnVAlue(1).toString());
				} else if (!result.getColList().isEmpty()) {
					ccpDetailsSidList = Optional.ofNullable(ccpDetailsSidList).orElseGet(HashSet::new);
					rsContractSidList = Optional.ofNullable(rsContractSidList).orElseGet(HashSet::new);
					filteredHierarchy = Optional.ofNullable(filteredHierarchy).orElseGet(HashSet::new);
					ccpDetailsSidList.add(result.getColumnVAlue(0));
					filteredHierarchy.add(result.getColumnVAlue(1).toString());
					rsContractSidList.add(result.getColumnVAlue(2));
				}
			}
			dashboardBean.setCcpDetailsSidList(ccpDetailsSidList);
			dashboardBean.setRsContractSidList(rsContractSidList);
			dashboardBean.setFilteredHierarchy(filteredHierarchy);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
