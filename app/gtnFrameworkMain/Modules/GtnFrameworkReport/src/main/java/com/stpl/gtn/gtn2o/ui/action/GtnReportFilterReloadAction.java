package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

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
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceFilterReloadComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBeanFilterReload = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceFilterReloadComponentId).getComponentData().getSharedPopupData();
		GtnWsReportRequest reportFilterReloadRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalFilterReloadRequest = new GtnWsGeneralRequest();
		reportFilterReloadRequest.setDataSelectionBean(dataSelectionBeanFilterReload);
		generalFilterReloadRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest filterReloadRequest = new GtnUIFrameworkWebserviceRequest();
		filterReloadRequest.setGtnWsGeneralRequest(generalFilterReloadRequest);
		filterReloadRequest.setGtnWsReportRequest(reportFilterReloadRequest);
		GtnWsReportDashboardFilterBean filterReloadBean = new GtnWsReportDashboardFilterBean();
		filterReloadBean.setHierarchyType(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(2)));
		filterReloadBean
				.setCustomerLevelNo(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(3)), componentId)
						.getIntegerFromV8ComboBox());
		filterReloadBean
				.setProductLevelNo(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(4)), componentId)
						.getIntegerFromV8ComboBox());
		filterReloadBean
				.setDeductionLevelNo(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(5)), componentId)
						.getIntegerFromV8ComboBox());
		filterReloadBean
				.setSelectedCustomerList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(6)), componentId)
						.getSelectedListFromV8MultiSelect());
		filterReloadBean
				.setSelectedProductList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(7)), componentId)
						.getSelectedListFromV8MultiSelect());
		filterReloadBean
				.setSelectedDeductionList(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(8)), componentId)
						.getSelectedListFromV8MultiSelect());
		reportFilterReloadRequest.setFilterBean(filterReloadBean);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE + GtnWsReportConstants.GTN_WS_REPORT_FILTER_LOAD_SERVICE,
				"report", filterReloadRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response != null) {
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response
					.getGtnUIFrameworkWebserviceComboBoxResponse();
			if (comboBoxResponse != null) {
				List<Object> itemsToBeSelected = getItemCodeList(filterReloadBean, comboBoxResponse);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)), componentId)
						.addAllItemsToMultiSelect(new ArrayList<>(comboBoxResponse.getItemValueList()),
								new ArrayList<>(comboBoxResponse.getItemCodeList()));
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)), componentId)
						.updateSelection(itemsToBeSelected);
			}
		}

	}

	private List<Object> getItemCodeList(GtnWsReportDashboardFilterBean filterBean,
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse) {
		List<Object> itemsToBeSelected = new ArrayList<>();
		if (filterBean.getHierarchyType().equals("C")) {
			fillItemsToBeSelectedList(comboBoxResponse.getItemCodeList(), filterBean.getSelectedCustomerList(),
					itemsToBeSelected);
		}
		if (filterBean.getHierarchyType().equals("P")) {
			fillItemsToBeSelectedList(comboBoxResponse.getItemCodeList(), filterBean.getSelectedProductList(),
					itemsToBeSelected);
		}
		if (filterBean.getHierarchyType().equals("D")) {
			fillItemsToBeSelectedList(comboBoxResponse.getItemCodeList(), filterBean.getSelectedDeductionList(),
					itemsToBeSelected);
		}
		return itemsToBeSelected;
	}

	private void fillItemsToBeSelectedList(List<String> itemCodeList, List<Object> selectedList,
			List<Object> itemsToBeSelected) {
		for (int i = 0; i < itemCodeList.size(); i++) {
			for (int j = 0; j < selectedList.size(); j++) {
				if (String.valueOf(selectedList.get(j)).equals(String.valueOf(itemCodeList.get(i)))) {
					itemsToBeSelected.add(Integer.valueOf(itemCodeList.get(i)));
				}
			}
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
