package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.gtn.gtn2o.registry.util.GtnFrameworkAlertUtil;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.vaadin.ui.AbstractComponent;

public class GtnCustomerAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//no need to implement

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		Date forecastEligibleDate = null;
		String hierarchyComponentId = String.valueOf(actionParamList.get(1));
		String relationshipComponentId = String.valueOf(actionParamList.get(2));
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId, componentId).getComponentData().getCustomData();

		String relationshipVersionNo = String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(3)), componentId)
						.getCaptionFromV8ComboBox());
		String hierarchyVersionNo = recordBean.getPropertyValueByIndex(6).toString();

		String relationshipBuilderSid = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(relationshipComponentId, componentId).getCaptionFromV8ComboBox());
		Integer hierarchyDefSid = (Integer) recordBean.getPropertyValueByIndex(7);

		Integer selectedLevelNo = Integer.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(4)), componentId)
						.getCaptionFromV8ComboBox());
		LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParamList.get(5)), componentId).getFieldValue();
		if (date != null) {
			forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		if (selectedLevelNo != 0) {
			String query = getLevelValueMapQuery(relationshipBuilderSid, hierarchyDefSid, hierarchyVersionNo,
					relationshipVersionNo);
			Map<String, String> levelValueMap = getLevelValueMap(query);
			List<GtnReportHierarchyLevelBean> hierarchyLevels = getHierarchyLevelDefinition(hierarchyDefSid,
					hierarchyVersionNo);
			GtnReportHierarchyLevelBean selectedHierarchyLevelBean = hierarchyLevels.get(selectedLevelNo - 1);
			String customerLevelQuery = loadAvailableCustomerLevelQuery(selectedHierarchyLevelBean,
					Integer.parseInt(relationshipBuilderSid), relationshipVersionNo, forecastEligibleDate);
			List<Object> queryParameters = new ArrayList<>();
			queryParameters.add(customerLevelQuery);
			queryParameters.add(levelValueMap);
			queryParameters.add(selectedHierarchyLevelBean);
			queryParameters.add(hierarchyLevels);
			queryParameters.add(relationshipVersionNo);
			queryParameters.add(hierarchyVersionNo);
			queryParameters.add(selectedLevelNo);
			queryParameters.add(forecastEligibleDate);
			queryParameters.add(Boolean.FALSE);

			AbstractComponent dualListBoxComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent(String.valueOf(actionParamList.get(6)), componentId);
			GtnUIFrameworkComponentData dualListBoxComponentData = (GtnUIFrameworkComponentData) dualListBoxComponent
					.getData();
			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxComponentData
					.getCustomData();
			dualListBoxBean.setGtnDualListBoxqueryParameters(queryParameters);
			dualListBoxComponentData.setCustomData(dualListBoxBean);
		}
	}

	private String getLevelValueMapQuery(String relationshipBuilderSid, Integer hierarchyDefSid,
			String hierarchyVersionNo, String relationshipVersionNo) {

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(Integer.parseInt(relationshipBuilderSid));
		inputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = buildServiceRequest(
				GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP);
		request.setGtnWsForecastRequest(forecastRequest);
		request.setGtnServiceRegistryWsRequest(serviceRegistryWsRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = callGtnService(request);
		GtnWsForecastResponse foreCastResponse = new GtnWsForecastResponse();
		if (relationResponse != null) {
			 foreCastResponse = relationResponse.getGtnWsForecastResponse();
		}else {
			GtnFrameworkAlertUtil alertAction = new GtnFrameworkAlertUtil();
			alertAction.throwAlertUtil("", GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP);
		}
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();

	}

	private Map<String, String> getLevelValueMap(String query) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHieraryQuery(query);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = buildServiceRequest("/loadLevelValueMapResults");
		request.setGtnServiceRegistryWsRequest(serviceRegistryWsRequest);
		GtnUIFrameworkWebserviceResponse response = callGtnService(request);
		Map<String, String> returnMap = new HashMap<>();
		if (response != null) {
			returnMap= response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
		}else {
			GtnFrameworkAlertUtil alertAction = new GtnFrameworkAlertUtil();
			alertAction.throwAlertUtil("", "/loadLevelValueMapResults");
		}
		return returnMap;
	}

	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(Integer hierarchyDefSid,
			String hierarchyVersionNo) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = buildServiceRequest("/getHierarchyLevelValues");
		request.setGtnServiceRegistryWsRequest(serviceRegistryWsRequest);
		GtnUIFrameworkWebserviceResponse response = callGtnService(request);
		List<GtnReportHierarchyLevelBean> returnList = new ArrayList<>();
		if (response != null) {
			returnList = response.getGtnWsForecastResponse().getInputBean().getLevelList();
		}else {
			GtnFrameworkAlertUtil alertAction = new GtnFrameworkAlertUtil();
			alertAction.throwAlertUtil("", "/getHierarchyLevelValues");
		}
		return returnList;
	}

	private String loadAvailableCustomerLevelQuery(GtnReportHierarchyLevelBean selectedHierarchyLevelBean,
			int relationshipSid, String relationshipVersionNo, Date forecastEligibleDate) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		inputBean.setForecastEligibleDate(forecastEligibleDate);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelBean.getHierarchyDefSid());
		inputBean
				.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelBean.getHierarchyLevelDefSid()));
		inputBean.setHierarchyVersionNo(selectedHierarchyLevelBean.getHierarchyVersionNo());
		inputBean.setLevelNo(selectedHierarchyLevelBean.getLevelNo());
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = buildServiceRequest(
				GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL);
		request.setGtnServiceRegistryWsRequest(serviceRegistryWsRequest);
		GtnUIFrameworkWebserviceResponse response = callGtnService(request);
		String returnString = "";
		if (response != null) {
			returnString = response.getGtnWsForecastResponse().getInputBean().getHieraryQuery();
		}else {
			GtnFrameworkAlertUtil alertAction = new GtnFrameworkAlertUtil();
			alertAction.throwAlertUtil("", GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL);
		}
		return returnString;
	}

	private GtnServiceRegistryWsRequest buildServiceRequest(String webserviceUrl) {
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();

		serviceRegistryBean.setRegisteredWebContext("/GtnHierarchyAndRelaionshipWebService");
		serviceRegistryBean.setUrl(webserviceUrl);
		serviceRegistryBean.setModuleName("hierarchyRelationship");
		serviceRegistryWsRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
		return serviceRegistryWsRequest;
	}

	private GtnUIFrameworkWebserviceResponse callGtnService(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry",
				gtnUIFrameworkWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
