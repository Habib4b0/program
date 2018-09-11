package com.stpl.gtn.gtn2o.ui.action;

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

public class GtnProductLevelAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnProductLevelAvailableTableLoadAction.class);

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
			int hierarchyBuilderSid = Integer.parseInt(
					String.valueOf(recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1)));
			int hierarchyVersionNo = Integer.parseInt(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(3).toString(), componentId)
							.getStringCaptionFromV8ComboBox());
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
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTDATASELECTION_LOAD_LEVELVALUE_MAP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
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
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(int hierarchyDefSid, int hierarchyVersionNo) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(hierarchyVersionNo);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTCUSTOMER_HIERARCHYLEVEL_VALUES,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getLevelList();
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
			boolean isNdc, int relationVersionNo, int businessUnitValue) {

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
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_PRODUCT_LEVEL,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
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
