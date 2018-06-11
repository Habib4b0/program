package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnReportFilterReloadAction
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
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnWsReportRequest(reportRequest);
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();
		filterBean.setHierarchyType(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(2)));
		filterBean.setCustomerLevelNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(3)))
				.getIntegerFromV8ComboBox());
		filterBean.setProductLevelNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(4)))
				.getIntegerFromV8ComboBox());
		filterBean.setDeductionLevelNo(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(5)))
				.getIntegerFromV8ComboBox());
		filterBean.setSelectedCustomerList(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(6)))
				.getIntegerListFromV8MultiSelect());
		filterBean.setSelectedProductList(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(7)))
				.getIntegerListFromV8MultiSelect());
		filterBean.setSelectedDeductionList(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(8)))
				.getIntegerListFromV8MultiSelect());
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE + GtnWsReportConstants.GTN_WS_REPORT_FILTER_LOAD_SERVICE,
				"report", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response
				.getGtnUIFrameworkWebserviceComboBoxResponse();

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.addAllItemsToMultiSelect(comboBoxResponse.getItemValueList(), comboBoxResponse.getItemCodeList());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
