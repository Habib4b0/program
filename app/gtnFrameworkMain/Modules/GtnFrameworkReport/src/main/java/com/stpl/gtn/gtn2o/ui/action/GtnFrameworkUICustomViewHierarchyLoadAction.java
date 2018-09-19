package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkUICustomViewHierarchyLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkUICustomViewHierarchyLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsHierarchyType hierarchyType = (GtnWsHierarchyType) parameterList.get(1);
		GtnUIFrameworkDataTable dataTable = null;
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		if (hierarchyType == GtnWsHierarchyType.CUSTOMER || hierarchyType == GtnWsHierarchyType.PRODUCT) {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
					.withCustomViewBean().withDataSelectionBean().build();
			request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setHierarchyType(hierarchyType);
			GTNLOGGER.info("component Id = = = " + componentId);
			GTNLOGGER.info("sourceComponentId = = =" + sourceComponentId);
			GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
			int relationSid = 0;
			int customerLevelNo = 0;
			int productLevelNo = 0;

			GtnUIFrameworkBaseComponent baseComponent1 = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(getHierarchyComponentId(parameterList.get(2).toString(), sourceComponentId)
							+ "_customerSelectionRelationship", componentId);
			if (baseComponent1.getComponent() != null) {
				relationSid = baseComponent1.getIntegerFromV8ComboBox();
			}

			GtnUIFrameworkBaseComponent baseComponent2 = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(getHierarchyComponentId(parameterList.get(2).toString(), sourceComponentId)
							+ "_customerSelectionLevel", componentId);
			if (baseComponent2.getComponent() != null) {
				customerLevelNo = baseComponent2.getIntegerFromV8ComboBox();
			}
			GtnUIFrameworkBaseComponent baseComponent3 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					getHierarchyComponentId(parameterList.get(2).toString(), sourceComponentId) + "_level",
					componentId);

			if (baseComponent3.getComponent() != null) {
				productLevelNo = baseComponent3.getIntegerFromV8ComboBox();
			}
			dataSelectionBean.setCustomerRelationshipBuilderSid(relationSid);
			dataSelectionBean.setCustomerHierarchySid(relationSid);
			dataSelectionBean.setCustomerHierarchyForecastLevel(customerLevelNo);
			dataSelectionBean.setProductHierarchyForecastLevel(productLevelNo);

			GtnUIFrameworkBaseComponent baseComponent4 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					getHierarchyComponentId(parameterList.get(2).toString(), sourceComponentId) + "_relationship",
					componentId);
			if (baseComponent4.getComponent() != null) {
				relationSid = baseComponent4.getIntegerFromV8ComboBox();
			}

			dataSelectionBean.setProductRelationshipBuilderSid(relationSid);
			dataSelectionBean.setProductHierarchySid(relationSid);
			request.getGtnWsReportRequest().getReportBean().setDataSelectionBean(dataSelectionBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsReportEndPointUrlConstants.LOAD_HIERARCHY,
					GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			dataTable = response.getGtnWsReportResponse().getReportBean().getCustomViewBean().getGridData();
		} else {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
					.withCustomViewBean().withDataSelectionBean().build();
			request.getGtnWsReportRequest().getReportBean().getCustomViewBean()
					.setHierarchyType(GtnWsHierarchyType.DEDUCTION);
			GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
			int relationSid = 0;
			GtnUIFrameworkBaseComponent baseComponent5 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					getHierarchyComponentId(parameterList.get(2).toString(), sourceComponentId) + "_relationship",
					componentId);
			if (baseComponent5.getComponent() != null) {
				relationSid = baseComponent5.getIntegerFromV8ComboBox();
			}

			dataSelectionBean.setProductRelationshipBuilderSid(relationSid);
			request.getGtnWsReportRequest().getReportBean().setDataSelectionBean(dataSelectionBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsReportEndPointUrlConstants.LOAD_DEDUCTION_HIERARCHY,
					GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			dataTable = response.getGtnWsReportResponse().getReportBean().getCustomViewBean().getGridData();
		}
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.GRID_STATIC_LOAD_ACTION);
		actionConfig.addActionParameter(componentId);
		actionConfig.addActionParameter(dataTable);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
	}

	private String getHierarchyComponentId(String input, String sourceComponentId) {
		if (input.equals("reportCustomViewLookup")) {
			return "reportLandingScreen";
		}
		return sourceComponentId + "_" + "dataSelectionTab";
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
