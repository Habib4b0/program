package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnCustomerAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		Date forecastEligibleDate = null;
		String hierarchyComponentId = String.valueOf(actionParameterList.get(1));
		String relationshipComponentId = String.valueOf(actionParameterList.get(2));
		GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId, componentId).getComponentData().getCustomData();

		String relationshipVersionNo = String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParameterList.get(3)), componentId)
						.getCaptionFromV8ComboBox());
		String hierarchyVersionNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParameterList.get(3)), componentId)
				.getStringCaptionFromV8ComboBox();

		String relationshipBuilderSid = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(relationshipComponentId, componentId).getCaptionFromV8ComboBox());
		Integer hierarchyDefSid = Integer.valueOf(
				String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(gtnWsRecordBean.getProperties().size() - 1)));

		Integer selectedLevelNo = Integer.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParameterList.get(4)), componentId)
						.getCaptionFromV8ComboBox());
		LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParameterList.get(5)), componentId).getFieldValue();
		if (date != null) {
			forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}

		if (selectedLevelNo != 0) {
			String sqlQuery = getReportLevelValueMapQuery(relationshipBuilderSid, hierarchyDefSid, hierarchyVersionNo,
					relationshipVersionNo);
			Map<String, String> reportLevelValueMap = getLevelValueMap(sqlQuery);
			List<GtnReportHierarchyLevelBean> reportHierarchyLevels = getHierarchyLevelDefinition(hierarchyDefSid,
					hierarchyVersionNo);
			GtnReportHierarchyLevelBean reportSelectedHierarchyLevelBean = reportHierarchyLevels
					.get(selectedLevelNo - 1);
			String reportCustomerLevelQuery = loadAvailableCustomerLevelQuery(reportSelectedHierarchyLevelBean,
					Integer.parseInt(relationshipBuilderSid), relationshipVersionNo, forecastEligibleDate);
			List<Object> reportQueryParameters = new ArrayList<>();
			reportQueryParameters.add(reportCustomerLevelQuery);
			reportQueryParameters.add(reportLevelValueMap);
			reportQueryParameters.add(reportSelectedHierarchyLevelBean);
			reportQueryParameters.add(reportHierarchyLevels);
			reportQueryParameters.add(relationshipVersionNo);
			reportQueryParameters.add(hierarchyVersionNo);
			reportQueryParameters.add(selectedLevelNo);
			reportQueryParameters.add(forecastEligibleDate);
			reportQueryParameters.add(Boolean.FALSE);

			AbstractComponent reportDualListBoxComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent(String.valueOf(actionParameterList.get(6)), componentId);
			GtnUIFrameworkComponentData reportDualListBoxComponentData = (GtnUIFrameworkComponentData) reportDualListBoxComponent
					.getData();
			GtnFrameworkV8DualListBoxBean reportDualListBoxBean = (GtnFrameworkV8DualListBoxBean) reportDualListBoxComponentData
					.getCustomData();
			reportDualListBoxBean.setGtnDualListBoxqueryParameters(reportQueryParameters);
			reportDualListBoxComponentData.setCustomData(reportDualListBoxBean);
		}
	}

	private String getReportLevelValueMapQuery(String relationshipBuilderSid, Integer hierarchyDefSid,
			String hierarchyVersionNo, String relationshipVersionNo) {
		GtnForecastHierarchyInputBean reportInputBean = new GtnForecastHierarchyInputBean();
		reportInputBean.setRelationShipBuilderSid(Integer.parseInt(relationshipBuilderSid));
		reportInputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		reportInputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		reportInputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(reportInputBean);
		GtnUIFrameworkWebServiceClient reportLevelMapClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest reportLevelMapRequest = new GtnUIFrameworkWebserviceRequest();
		reportLevelMapRequest.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = reportLevelMapClient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP,
				reportLevelMapRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean reportOutputBean = foreCastResponse.getInputBean();
		return reportOutputBean.getHieraryQuery();

	}

	private Map<String, String> getLevelValueMap(String sqlQuery) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHieraryQuery(sqlQuery);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTDATASELECTION_LOAD_LEVELVALUE_MAP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
	}

	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(Integer hierarchyDefSid,
			String hierarchyVersionNo) {
		GtnForecastHierarchyInputBean hierarchyLevelDefinitionInputBean = new GtnForecastHierarchyInputBean();
		hierarchyLevelDefinitionInputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		hierarchyLevelDefinitionInputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest reportHierarchyLevelDefinitionRequest = new GtnWsForecastRequest();
		reportHierarchyLevelDefinitionRequest.setInputBean(hierarchyLevelDefinitionInputBean);
		GtnUIFrameworkWebserviceRequest hierarchyLevelDefinitionRequest = new GtnUIFrameworkWebserviceRequest();
		hierarchyLevelDefinitionRequest.setGtnWsForecastRequest(reportHierarchyLevelDefinitionRequest);
		GtnUIFrameworkWebserviceResponse hierarchyLevelDefinitionResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTCUSTOMER_HIERARCHYLEVEL_VALUES,
				hierarchyLevelDefinitionRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return hierarchyLevelDefinitionResponse.getGtnWsForecastResponse().getInputBean().getLevelList();
	}

	private String loadAvailableCustomerLevelQuery(GtnReportHierarchyLevelBean selectedHierarchyLevelBean,
			int relationshipSid, String relationshipVersionNo, Date forecastEligibleDate) {
		GtnForecastHierarchyInputBean reportHierarchyInputBean = new GtnForecastHierarchyInputBean();
		reportHierarchyInputBean.setRelationShipBuilderSid(relationshipSid);
		reportHierarchyInputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		reportHierarchyInputBean.setForecastEligibleDate(forecastEligibleDate);
		reportHierarchyInputBean.setHierarchyDefinitionSid(selectedHierarchyLevelBean.getHierarchyDefSid());
		reportHierarchyInputBean
				.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelBean.getHierarchyLevelDefSid()));
		reportHierarchyInputBean.setHierarchyVersionNo(selectedHierarchyLevelBean.getHierarchyVersionNo());
		reportHierarchyInputBean.setLevelNo(selectedHierarchyLevelBean.getLevelNo());
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(reportHierarchyInputBean);
		GtnUIFrameworkWebserviceRequest reportAvailableCustomerLevelQueryRequest = new GtnUIFrameworkWebserviceRequest();
		reportAvailableCustomerLevelQueryRequest.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse reportAvailableCustomerLevelQueryResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL,
				reportAvailableCustomerLevelQueryRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return reportAvailableCustomerLevelQueryResponse.getGtnWsForecastResponse().getInputBean().getHieraryQuery();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
