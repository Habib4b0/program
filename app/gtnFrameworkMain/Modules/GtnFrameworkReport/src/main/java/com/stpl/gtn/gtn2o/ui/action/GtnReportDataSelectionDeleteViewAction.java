package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnReportDataSelectionDeleteViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		if (recordBean == null) {
			recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(2).toString()).getComponentData().getCustomData();
		}
		Integer viewId = (Integer) recordBean.getPropertyValueByIndex(4);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setViewId(viewId);
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnWsReportRequest(reportRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORRT_DELETEVIEW_SERVICE, "report",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (response.getGtnWsGeneralResponse().isSucess()) {
			GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
			resetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			resetAction.setActionParameterList(Arrays.asList(GtnReportDataSelectionResetAction.class.getName(),
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "company",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnit",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE,
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriod",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "STATUS",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerHierarchy",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerSelectionRelationship",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerSelectionLevel",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerRelationshipVersion",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerSelectionForecastEligibilityDate",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "customerDualListBox",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "producthierarchy",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "relationship",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE + "level",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "productRelationshipVersion",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "productdualListBoxComp",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "displaySelectionTabCustomView",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ "displaySelectionTabVariable",
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG,
					actionParamList.get(3).toString() + GtnFrameworkReportStringConstants.UNDERSCORE
							+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG,
							"reportLandingScreen_reportingDashboardComparisonConfig"));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetAction);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
