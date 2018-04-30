package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.LevelDto;
import com.stpl.gtn.gtn2o.ws.report.constants.NumericConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

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
		try{
		gtnLogger.info("-----inside ---GtnProductLevelAvailableTableLoadAction-----");
		Map<String, String> productDescMap = new HashMap<>();
		List<LevelDto> productHierarchyLevelDefinitionList = Collections.emptyList();
		List<LevelDto> resultedLevelsList = new ArrayList<>();
		List<LevelDto> selectedCustomerContractList = new ArrayList<>();
		List<String> groupFilteredItems = new ArrayList<>() ;
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
		int customerHierarchyVersionNoComponent = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerRelationshipVersion").getCaptionFromComboBox());
		gtnLogger.info("-----------relationshipBuilderSID" + relationshipBuilderSID);
		gtnLogger.info("-----------hierarchyBuilderSid" + hierarchyBuilderSid);
		gtnLogger.info("-----------hierarchyVersionNo" + hierarchyVersionNo);
		gtnLogger.info("-----------relationVersionNo" + relationVersionNo);
		gtnLogger.info("-----------productDescMap" + productDescMap.isEmpty());
		productDescMap = getLevelValueMap(relationshipBuilderSID, hierarchyBuilderSid, hierarchyVersionNo,
				relationVersionNo);
		gtnLogger.info("-----------productDescMap" + productDescMap.isEmpty());

		gtnLogger.info("-----------productHierarchyLevelDefinitionList" + productHierarchyLevelDefinitionList.size());

		productHierarchyLevelDefinitionList = getHierarchyLevelDefinition(hierarchyBuilderSid, hierarchyVersionNo);

		gtnLogger.info("-----------productHierarchyLevelDefinitionList" + productHierarchyLevelDefinitionList.size());
		Object businessUnitValue = null;
		LevelDto selectedHierarchyLevelDto = productHierarchyLevelDefinitionList.get(selectedLevel - 1);
		List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String> emptyList()
				: groupFilteredItems;
		gtnLogger.info("-----------resultedLevelsList" + resultedLevelsList.size());

		
		resultedLevelsList = loadAvailableProductlevel(selectedHierarchyLevelDto, (int)relationshipBuilderSID,
				tempGroupFileter, selectedCustomerContractList,false,"","",relationVersionNo,customerHierarchyVersionNoComponent,businessUnitValue,productDescMap);
		
		gtnLogger.info("-----------resultedLevelsList" + resultedLevelsList.size());

		}
		catch(Exception e){
			gtnLogger.error("Error in"+e.getMessage());
		}

	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo, int relationVersionNo) {
		Map<String, String> relationMap = new HashMap<>();
		String query = getLevelMapValueMapQuery(relationshipBuilderSID, relationVersionNo, hierarchyBuilderSid,
				hierarchyVersionNo);

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		gtnWsReportRequest.setQuery(query);
		request.setGtnReportRequest(gtnWsReportRequest);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				"/report" + "/queryExecutor", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		List<Object[]> list = response.getGtnWsReportResponse().getResultList();
		for (Object[] LevelDto2 : list) {
			if (LevelDto2[0] != null && LevelDto2[1] != null)
				relationMap.put(LevelDto2[0].toString(), LevelDto2[1].toString());
		}
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

	public List<LevelDto> getHierarchyLevelDefinition(int hierarchyDefinitionSid, int hierarchyVersionNo) {
		List<LevelDto> resultDtoList;

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		GtnReportHierarchyLookupBean lookupbean = new GtnReportHierarchyLookupBean();
		lookupbean.setHierarchyDefSid(hierarchyDefinitionSid);
		lookupbean.setVersionNo(hierarchyVersionNo);
		gtnWsReportRequest.setCustomerHierarchyLookupBean(lookupbean);
		request.setGtnReportRequest(gtnWsReportRequest);

		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				"/report" + "/hierarchyDefinition", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		List<Object[]> results = relationResponse.getGtnWsReportResponse().getResultList();
		resultDtoList = cutomizeHierarchyBean(results, hierarchyVersionNo);
		Collections.sort(resultDtoList);
		return resultDtoList;
	}

	public List<LevelDto> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
		List<LevelDto> resultDtoList = new ArrayList<>();
		for (Object[] objects : results) {
			LevelDto levelDto = new LevelDto();
			levelDto.setLevelNo(getIntegerValue(objects, 0));
			levelDto.setLevelValueReference(getStringValue(objects, 1));
			levelDto.setTableName(objects[2] == null ? "" : objects[2].toString());
			levelDto.setFieldName(objects[3] == null ? "" : objects[3].toString());
			levelDto.setLevel(objects[4] == null ? "" : objects[4].toString());
			levelDto.setHierarchyLevelDefnId(objects[5] == null ? "0" : objects[5].toString());
			levelDto.setHierarchyId((Integer.valueOf(objects[6] == null ? "0" : objects[6].toString())));
			levelDto.setHierarchyType(objects[7] == null ? "" : objects[7].toString());
			levelDto.setHierarchyVersionNo(hierarchyVersionNo);
			resultDtoList.add(levelDto);
		}
		return resultDtoList;
	}

	public List<LevelDto> loadAvailableProductlevel(LevelDto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<LevelDto> selectedCustomerContractList, boolean isNdc,
			String dedLevel, String dedValue, int relationVersionNo, int customerRelationVersionNo,
			Object businessUnitValue, Map<String, String> productDescMap) throws CloneNotSupportedException {
		GtnForecastHierarchyInputBean inputBean = createInputBean(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredItems, dedLevel, dedValue, relationVersionNo, null, Boolean.FALSE);

//		inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
//		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRelationVersionNo);
//		 if (selectedCustomerContractList != null &&
//		 !selectedCustomerContractList.isEmpty()) {
//		 inputBean.setSelectedCustomerList(
//		 LevelDtoToRelationShipBeanConverter.convetToRelationBean(selectedCustomerContractList));
//		 inputBean.setSelectedCustomerHierarcySid(selectedCustomerContractList.get(0).getHierarchyId());
//		 inputBean
//		 .setSelectedCustomerHierarchyVersionNo(selectedCustomerContractList.get(0).getHierarchyVersionNo());
//		 }
//		inputBean.setNdc(isNdc);
		gtnLogger.info("-----------inputBean-----------" + inputBean.getHierarchyDefinitionSid()+inputBean.getHierarchyVersionNo()+inputBean.getHierarchyLevelDefinitionSid());
		String query = getLoadProductDataQuery(inputBean);
		gtnLogger.info("-----------query-----------" + query);
		return customizeRelation(query, selectedHierarchyLevelDto, productDescMap, relationVersionNo, isNdc);
	}

	private GtnForecastHierarchyInputBean createInputBean(LevelDto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, String dedLevel, String dedValue, int relationVersionNo,
			Date forecastEligibleDate, boolean isNdc) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setGroupFilterCompenies(groupFilteredCompanies);
		inputBean.setDeductionLevel(dedLevel);
		inputBean.setDeductionValue(dedValue);
		inputBean.setRelationVersionNo(relationVersionNo);
		inputBean.setForecastEligibleDate(forecastEligibleDate);
		inputBean.setNdc(isNdc);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelDto.getHierarchyId());
		inputBean.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelDto.getHierarchyLevelDefnId()));
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
		gtnLogger.info("-------outputBean.getHieraryQuery()"+outputBean.getHieraryQuery());
		return outputBean.getHieraryQuery();
	}

	private List<LevelDto> customizeRelation(String query, LevelDto selectedHierarchyLevelDto,
			Map<String, String> customerDescMap, int relationVersionNo, boolean isNdc)
			throws CloneNotSupportedException {
		List<LevelDto> resultList = new ArrayList<>();

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		gtnWsReportRequest.setQuery(query);
		request.setGtnReportRequest(gtnWsReportRequest);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				"/report" + "/queryExecutor", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		List<Object[]> resultsDataList = response.getGtnWsReportResponse().getResultList();
		if (resultsDataList != null && !resultsDataList.isEmpty()) {
			for (int i = 0; i < resultsDataList.size(); i++) {
				LevelDto dto = (LevelDto) selectedHierarchyLevelDto.clone();
				Object[] obj = resultsDataList.get(i);
				dto.setDisplayValue(customerDescMap.get(obj[NumericConstants.FOUR]));
				dto.setLevel(customerDescMap.get(obj[NumericConstants.FOUR]));
				dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.ZERO]));
				dto.setLevelNo(Integer.parseInt(String.valueOf(obj[NumericConstants.ONE])));
				dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
				dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
				dto.setHierarchyNo(String.valueOf(obj[NumericConstants.FOUR]));
				dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.FIVE]));
				if (isNdc) {
					dto.setStrength(String.valueOf(obj[NumericConstants.SIX]));
					dto.setForm(String.valueOf(obj[NumericConstants.SEVEN]));
				}
				dto.setRelationShipVersionNo(relationVersionNo);
				resultList.add(dto);
			}
		}
		return resultList;
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
