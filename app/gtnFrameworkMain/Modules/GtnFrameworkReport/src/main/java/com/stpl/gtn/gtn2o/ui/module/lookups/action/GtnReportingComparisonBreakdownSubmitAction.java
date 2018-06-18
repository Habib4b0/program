package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnReportingComparisonBreakdownSubmitAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(
				"comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent", componentId);

		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();

		List<Object[]> gtnReportComparisonBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();

		List<String> inputList = new ArrayList<String>();
		for (int i = 0; i < gtnReportComparisonBreakdownLookupBeanList.size(); i++) {
			inputList.add(i, gtnReportComparisonBreakdownLookupBeanList.get(i)[1].toString());
		}

		Set<String> inputSet = new LinkedHashSet<>(inputList);

		List<String> inputWsList = new ArrayList<>(inputSet);
		GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
		gtnWsReportDataSelectionBean.setVariableBreakdownHeaderLoadList(inputWsList);
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		gtnWsReportRequest.setDataSelectionBean(gtnWsReportDataSelectionBean);
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(gtnWsReportRequest);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_PERIODS_SERVICE,
				GtnFrameworkReportStringConstants.REPORT, gtnUIFrameworkWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		List<Object[]> resultList = response.getGtnReportResponse().getComparisonBreakdownLookupBean().getResultList();

		Map<String, Object[]> periodSidAndYearMap = new HashMap();
		for (int k = 0; k < inputWsList.size(); k++) {
			periodSidAndYearMap.put(inputWsList.get(k), resultList.get(k));
		}

		List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownLookupBeanSaveList = new ArrayList<>();
		for (int i = 0; i < gtnReportComparisonBreakdownLookupBeanList.size(); i++) {
			GtnReportComparisonBreakdownLookupBean comparisonBreakdownLookupBean = new GtnReportComparisonBreakdownLookupBean();
			comparisonBreakdownLookupBean.setMasterSid((int) gtnReportComparisonBreakdownLookupBeanList.get(i)[2]);
			comparisonBreakdownLookupBean
					.setPeriod((int) periodSidAndYearMap.get(gtnReportComparisonBreakdownLookupBeanList.get(i)[1])[1]);
			comparisonBreakdownLookupBean
					.setYear((int) periodSidAndYearMap.get(gtnReportComparisonBreakdownLookupBeanList.get(i)[1])[2]);
			comparisonBreakdownLookupBean.setSelectedVariable(
					Integer.valueOf((String) gtnReportComparisonBreakdownLookupBeanList.get(i)[0]));
			comparisonBreakdownLookupBeanSaveList.add(comparisonBreakdownLookupBean);
		}
		String sourceParentComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId)
				.getParentViewId();
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(sourceParentComponentId)
				.getViewId();
		GtnWsReportDataSelectionBean gtnWsReportDataSelectionBeanSave = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		// GtnWsReportDataSelectionBean gtnWsReportDataSelectionBeanSave =
		// (GtnWsReportDataSelectionBean)
		// GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW,
		// componentId).getComponentData().getSharedPopupData();
		gtnWsReportDataSelectionBeanSave.setComparisonBreakdownSaveList(comparisonBreakdownLookupBeanSaveList);
		GtnWsReportRequest gtnWsReportRequestSave = new GtnWsReportRequest();
		gtnWsReportRequestSave.setDataSelectionBean(gtnWsReportDataSelectionBeanSave);
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequestSave = new GtnUIFrameworkWebserviceRequest();
		gtnUIFrameworkWebserviceRequestSave.setGtnWsReportRequest(gtnWsReportRequestSave);
		GtnUIFrameworkWebServiceClient clientSave = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse saveResponse = clientSave.callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_SAVE_SERVICE,
				GtnFrameworkReportStringConstants.REPORT, gtnUIFrameworkWebserviceRequestSave,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
