package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnProductLevelAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnProductLevelAvailableTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			gtnLogger.info("--------change GtnProductLevelAvailableTableLoadAction-----");
			Map<String, String> productDescMap = new HashMap<>();
			List<GtnReportHierarchyLevelBean> selectedCustomerContractList = new ArrayList<>();
			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_producthierarchy").getComponentData().getCustomData();
			Object relationshipBuilderSID = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_relationship").getValueFromComponent();
			int hierarchyBuilderSid = (int) recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1);
			int hierarchyVersionNo = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_productRelationshipVersion").getCaptionFromComboBox());
			int relationVersionNo = (int) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_productRelationshipVersion").getValueFromComponent();
			int selectedLevel = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_level")
					.getValueFromComponent();
			int businessUnit = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_businessUnit")
					.getValueFromComponent();
			int customerHierarchyVersionNoComponent = Integer.parseInt(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_customerRelationshipVersion")
							.getCaptionFromComboBox());
			gtnLogger.info("-----------relationshipBuilderSID" + relationshipBuilderSID);
			gtnLogger.info("-----------hierarchyBuilderSid" + hierarchyBuilderSid);
			gtnLogger.info("-----------hierarchyVersionNo" + hierarchyVersionNo);
			gtnLogger.info("-----------relationVersionNo" + relationVersionNo);
			gtnLogger.info("-----------productDescMap" + productDescMap.isEmpty());
			productDescMap = getLevelValueMap(relationshipBuilderSID, hierarchyBuilderSid, hierarchyVersionNo,
					relationVersionNo);
			gtnLogger.info("-----------productDescMap" + productDescMap.isEmpty());

			List<GtnReportHierarchyLevelBean> productHierarchyLevelDefinitionList = getHierarchyLevelDefinition(
					hierarchyBuilderSid, hierarchyVersionNo);

			gtnLogger.info(
					"-----------productHierarchyLevelDefinitionList" + productHierarchyLevelDefinitionList.size());
			Object businessUnitValue = null;
			GtnReportHierarchyLevelBean selectedHierarchyLevelDto = productHierarchyLevelDefinitionList
					.get(selectedLevel - 1);

			String query = loadAvailableProductlevel(selectedHierarchyLevelDto, (int) relationshipBuilderSID,
					selectedCustomerContractList, false, relationVersionNo, customerHierarchyVersionNoComponent,
					businessUnit, productDescMap);
			List<Object> queryParameters = new ArrayList<>();
			queryParameters.add(query);
			queryParameters.add(productDescMap);
			queryParameters.add(selectedHierarchyLevelDto);
			queryParameters.add(productHierarchyLevelDefinitionList);
			queryParameters.add(relationVersionNo);
			queryParameters.add(hierarchyVersionNo);
			queryParameters.add(selectedLevel);
			queryParameters.add(businessUnit);
			gtnLogger.info("*************selectedHierarchyLevelDto***********" + selectedHierarchyLevelDto.getClass());
			AbstractComponent dualListBoxComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent("reportLandingScreen_productdualListBoxComp");
			gtnLogger.info("*************" + dualListBoxComponent.getClass());
			GtnUIFrameworkComponentData dualListBoxComponentData = (GtnUIFrameworkComponentData) dualListBoxComponent
					.getData();
			gtnLogger.info("--------------" + dualListBoxComponentData.getClass());
			gtnLogger.info("==============" + dualListBoxComponentData.getCustomData().getClass());
			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxComponentData
					.getCustomData();
			dualListBoxBean.setGtnDualListBoxqueryParameters(queryParameters);
			dualListBoxComponentData.setCustomData(dualListBoxBean);
		} catch (Exception e) {
			gtnLogger.error("Error in" + e.getMessage());
		}

	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo, int relationVersionNo) {
		String query = getLevelMapValueMapQuery(relationshipBuilderSID, relationVersionNo, hierarchyBuilderSid,
				hierarchyVersionNo);
		return getLevelValueMap(query);

		/*
		 * GtnUIFrameworkWebServiceClient client = new
		 * GtnUIFrameworkWebServiceClient(); GtnUIFrameworkWebserviceRequest
		 * request = new GtnUIFrameworkWebserviceRequest(); GtnWsReportRequest
		 * gtnWsReportRequest = new GtnWsReportRequest();
		 * gtnWsReportRequest.setQuery(query);
		 * request.setGtnReportRequest(gtnWsReportRequest);
		 * GtnUIFrameworkWebserviceResponse response =
		 * client.callGtnWebServiceUrl( "/report" + "/queryExecutor", request,
		 * GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		 * 
		 * List<Object[]> list =
		 * response.getGtnReportResponse().getResultList(); for (Object[]
		 * LevelDto2 : list) { if (LevelDto2[0] != null && LevelDto2[1] != null)
		 * relationMap.put(LevelDto2[0].toString(), LevelDto2[1].toString()); }
		 * return relationMap;
		 */
	}

	private Map<String, String> getLevelValueMap(String query) {
		Map<String, String> relationMap = new HashMap<>();
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
		relationMap = response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
		return relationMap;
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

	/*
	 * public List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(int
	 * hierarchyDefinitionSid, int hierarchyVersionNo) {
	 * List<GtnReportHierarchyLevelBean> resultDtoList;
	 * 
	 * GtnUIFrameworkWebServiceClient client = new
	 * GtnUIFrameworkWebServiceClient(); GtnUIFrameworkWebserviceRequest request
	 * = new GtnUIFrameworkWebserviceRequest(); GtnWsReportRequest
	 * gtnWsReportRequest = new GtnWsReportRequest();
	 * GtnReportHierarchyLookupBean lookupbean = new
	 * GtnReportHierarchyLookupBean();
	 * lookupbean.setHierarchyDefSid(hierarchyDefinitionSid);
	 * lookupbean.setVersionNo(hierarchyVersionNo);
	 * gtnWsReportRequest.setCustomerHierarchyLookupBean(lookupbean);
	 * request.setGtnReportRequest(gtnWsReportRequest);
	 * 
	 * GtnUIFrameworkWebserviceResponse relationResponse =
	 * client.callGtnWebServiceUrl( "/report" + "/hierarchyDefinition", request,
	 * GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	 * 
	 * List<Object[]> results =
	 * relationResponse.getGtnWsReportResponse().getResultList(); resultDtoList
	 * = cutomizeHierarchyBean(results, hierarchyVersionNo);
	 * //Collections.sort((List<T>) resultDtoList); return resultDtoList; }
	 */
	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(int hierarchyDefSid, int hierarchyVersionNo) {
		List<GtnReportHierarchyLevelBean> levelList = new ArrayList<>();
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
		levelList = response.getGtnWsForecastResponse().getInputBean().getLevelList();
		return levelList;
	}

	public List<GtnReportHierarchyLevelBean> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
		List<GtnReportHierarchyLevelBean> resultDtoList = new ArrayList<>();
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
			List<GtnReportHierarchyLevelBean> selectedCustomerContractList, boolean isNdc, int relationVersionNo,
			int customerRelationVersionNo, int businessUnitValue, Map<String, String> productDescMap)
			throws CloneNotSupportedException {
		// GtnForecastHierarchyInputBean inputBean =
		// createInputBean(selectedHierarchyLevelDto, relationshipSid,
		// relationVersionNo, Boolean.FALSE, businessUnitValue);

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setRelationVersionNo(relationVersionNo);
		inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
		inputBean.setNdc(isNdc);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelDto.getHierarchyDefSid());
		inputBean.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelDto.getHierarchyLevelDefSid()));
		inputBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getHierarchyVersionNo());
		inputBean.setLevelNo(selectedHierarchyLevelDto.getLevelNo());

		// inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
		// inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRelationVersionNo);
		// if (selectedCustomerContractList != null &&
		// !selectedCustomerContractList.isEmpty()) {
		// inputBean.setSelectedCustomerList(
		// LevelDtoToRelationShipBeanConverter.convetToRelationBean(selectedCustomerContractList));
		// inputBean.setSelectedCustomerHierarcySid(selectedCustomerContractList.get(0).getHierarchyId());
		// inputBean
		// .setSelectedCustomerHierarchyVersionNo(selectedCustomerContractList.get(0).getHierarchyVersionNo());
		// }
		// inputBean.setNdc(isNdc);
		gtnLogger.info("-----------inputBean-----------" + inputBean.getHierarchyDefinitionSid()
				+ inputBean.getHierarchyVersionNo() + inputBean.getHierarchyLevelDefinitionSid());
		String query = getLoadProductDataQuery(inputBean);
		gtnLogger.info("-----------query-----------" + query);
		return query;
		// customizeRelation(query, selectedHierarchyLevelDto, productDescMap,
		// relationVersionNo, isNdc);
	}

	private GtnForecastHierarchyInputBean createInputBean(GtnReportHierarchyLevelBean selectedHierarchyLevelDto,
			int relationshipSid, int relationVersionNo, boolean isNdc, int businessUnitValue) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setRelationVersionNo(relationVersionNo);
		inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
		inputBean.setNdc(isNdc);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelDto.getHierarchyDefSid());
		inputBean.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelDto.getHierarchyLevelDefSid()));
		inputBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getHierarchyVersionNo());
		inputBean.setLevelNo(selectedHierarchyLevelDto.getLevelNo());
		return inputBean;
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
		gtnLogger.info("-------outputBean.getHieraryQuery()" + outputBean.getHieraryQuery());
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
