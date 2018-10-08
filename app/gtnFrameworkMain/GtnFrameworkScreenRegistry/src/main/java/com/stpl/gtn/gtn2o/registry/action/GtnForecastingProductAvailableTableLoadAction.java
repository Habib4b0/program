package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import java.util.ArrayList;
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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnForecastingProductAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnForecastingProductAvailableTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(1).toString(), componentId).getComponentData()
					.getCustomData();
			int relationshipBuilderSID = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(2).toString(), componentId).getCaptionFromV8ComboBox());
			int hierarchyBuilderSid =(int) recordBean.getPropertyValueByIndex(7);
			int hierarchyVersionNo = (int) recordBean.getPropertyValueByIndex(6);
			int relationVersionNo = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(3).toString(), componentId).getCaptionFromV8ComboBox());
			int selectedLevel = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(4).toString(), componentId).getCaptionFromV8ComboBox());
			int businessUnit = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(5).toString(), componentId).getCaptionFromV8ComboBox());
			Map<String, String> productDescMap = getLevelValueMap(relationshipBuilderSID, hierarchyBuilderSid,
					hierarchyVersionNo, relationVersionNo);

			List<GtnReportHierarchyLevelBean> productHierarchyLevelDefinitionList = getHierarchyLevelDefinition(
					hierarchyBuilderSid, hierarchyVersionNo);

			if (selectedLevel != 0) {
				GtnReportHierarchyLevelBean selectedHierarchyLevelDto = productHierarchyLevelDefinitionList
						.get(selectedLevel - 1);

				String query = loadAvailableProductlevel(selectedHierarchyLevelDto, relationshipBuilderSID, false,
						relationVersionNo, businessUnit);
				List<Object> queryParameters = new ArrayList<>();
				queryParameters.add(query);
				queryParameters.add(productDescMap);
				queryParameters.add(selectedHierarchyLevelDto);
				queryParameters.add(productHierarchyLevelDefinitionList);
				queryParameters.add(relationVersionNo);
				queryParameters.add(hierarchyVersionNo);
				queryParameters.add(selectedLevel);
				queryParameters.add(businessUnit);
				queryParameters.add(Boolean.TRUE);
				AbstractComponent dualListBoxComponent = GtnUIFrameworkGlobalUI
						.getVaadinComponent(actionParamList.get(7).toString(), componentId);
				GtnUIFrameworkComponentData dualListBoxComponentData = (GtnUIFrameworkComponentData) dualListBoxComponent
						.getData();
				GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxComponentData
						.getCustomData();
				dualListBoxBean.setGtnDualListBoxqueryParameters(queryParameters);
				dualListBoxComponentData.setCustomData(dualListBoxBean);
			}
		} catch (Exception e) {
			gtnLogger.error("Error in" + e.getMessage(), e);
		}

	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo, int relationVersionNo) {
		String query = getLevelMapValueMapQuery(relationshipBuilderSID, relationVersionNo, hierarchyBuilderSid,
				hierarchyVersionNo);
		return getLevelValueMap(query);

	}

	private Map<String, String> getLevelValueMap(String query) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHieraryQuery(query);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = callGtnWebService(request);
		return response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
	}

	public GtnUIFrameworkWebserviceResponse callGtnWebService(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/loadLevelValueMapResults", GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private String getLevelMapValueMapQuery(Object relationshipBuilderSID, int relationVersionNo,
			int hierarchyBuilderSid, int hierarchyVersionNo) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(Integer.parseInt(relationshipBuilderSID.toString()));
		inputBean.setRelationVersionNo(relationVersionNo);
		inputBean.setHierarchyDefinitionSid(hierarchyBuilderSid);
		inputBean.setHierarchyVersionNo(hierarchyVersionNo);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = callWebService(client, request);
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	public GtnUIFrameworkWebserviceResponse callWebService(GtnUIFrameworkWebServiceClient client,
			GtnUIFrameworkWebserviceRequest request) {
		return client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP, GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(int hierarchyDefSid, int hierarchyVersionNo) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(hierarchyVersionNo);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = callHierarchyWebService(request);
		return response.getGtnWsForecastResponse().getInputBean().getLevelList();
	}

	public GtnUIFrameworkWebserviceResponse callHierarchyWebService(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/getHierarchyLevelValues", GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public List<GtnReportHierarchyLevelBean> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
		List<GtnReportHierarchyLevelBean> resultDtoList = new ArrayList<>(results.size());
		for (Object[] objects : results) {
			GtnReportHierarchyLevelBean gtnReportHierarchyLevelBean = new GtnReportHierarchyLevelBean();
			gtnReportHierarchyLevelBean.setLevelNo(getIntegerValue(objects, 0));
			gtnReportHierarchyLevelBean.setLevelValueReference(getStringValue(objects, 1));
			gtnReportHierarchyLevelBean.setTableName(objects[2] == null ? "" : objects[2].toString());
			gtnReportHierarchyLevelBean.setFieldName(objects[3] == null ? "" : objects[3].toString());
			gtnReportHierarchyLevelBean.setLevel(objects[4] == null ? "" : objects[4].toString());
			gtnReportHierarchyLevelBean.setHierarchyLevelDefSid(objects[5] == null ? "0" : objects[5].toString());
			gtnReportHierarchyLevelBean
					.setHierarchyDefSid((Integer.valueOf(objects[6] == null ? "0" : objects[6].toString())));
			gtnReportHierarchyLevelBean.setHierarchyType(objects[7] == null ? "" : objects[7].toString());
			gtnReportHierarchyLevelBean.setHierarchyVersionNo(hierarchyVersionNo);
			resultDtoList.add(gtnReportHierarchyLevelBean);
		}
		return resultDtoList;
	}

	public String loadAvailableProductlevel(GtnReportHierarchyLevelBean selectedHierarchyLevelDto, int relationshipSid,
			boolean isNdc, int relationVersionNo, int businessUnitValue) throws CloneNotSupportedException {

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setRelationVersionNo(relationVersionNo);
		inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
		inputBean.setNdc(isNdc);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelDto.getHierarchyDefSid());
		inputBean.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelDto.getHierarchyLevelDefSid()));
		inputBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getHierarchyVersionNo());
		inputBean.setLevelNo(selectedHierarchyLevelDto.getLevelNo());

		return getLoadProductDataQuery(inputBean);

	}

	private String getLoadProductDataQuery(GtnForecastHierarchyInputBean inputBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = callProductHierarchyWebService(client, request);
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	public GtnUIFrameworkWebserviceResponse callProductHierarchyWebService(GtnUIFrameworkWebServiceClient client,
			GtnUIFrameworkWebserviceRequest request) {
		return client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_PRODUCT_LEVEL, GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private String getStringValue(Object[] objects, int index) {
		return objects[index] == null ? "" : objects[index].toString();
	}

	private Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
