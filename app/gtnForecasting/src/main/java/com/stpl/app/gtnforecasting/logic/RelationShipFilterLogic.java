package com.stpl.app.gtnforecasting.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.converters.LevelDtoToRelationShipBeanConverter;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.ui.util.dto.Pairs;
import com.vaadin.server.VaadinSession;

public class RelationShipFilterLogic {

	private static final RelationShipFilterLogic instance = new RelationShipFilterLogic();
	private final CommonDAO daoImpl = new CommonDAOImpl();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

	private RelationShipFilterLogic() {
		// Singleton constructor
	}

	public static RelationShipFilterLogic getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getHierarchyLevelDefinition(int hierarchyDefinitionSid, int hierarchyVersionNo) {
		List<String> input = new ArrayList<>();
		List<Leveldto> resultDtoList;
		input.add(String.valueOf(hierarchyDefinitionSid));
		input.add(String.valueOf(hierarchyVersionNo));
		List<Object[]> results = QueryUtils.getAppData(input, "getHierarchyLevelVlaues", null);
		resultDtoList = cutomizeHierarchyBean(results, hierarchyVersionNo);
		Collections.sort(resultDtoList);
		return resultDtoList;
	}

	public List<Leveldto> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
		List<Leveldto> resultDtoList = new ArrayList<>();
		for (Object[] objects : results) {
			Leveldto levelDto = new Leveldto();
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

	public List<Leveldto> loadAvailableCustomerlevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, String dedLevel,
			String dedValue, int relationVersionNo, Date forecastEligibleDate, Map<String, String> customerDescMap)
			throws CloneNotSupportedException {
		GtnForecastHierarchyInputBean inputBean = createInputBean(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredCompanies, dedLevel, dedValue, relationVersionNo,
				forecastEligibleDate, Boolean.FALSE);
		String query = getLoadDataQuery(inputBean);
		return customizeRelation(query, selectedHierarchyLevelDto, customerDescMap, relationVersionNo, false);
	}

	private List<Leveldto> customizeRelation(String query, Leveldto selectedHierarchyLevelDto,
			Map<String, String> customerDescMap, int relationVersionNo, boolean isNdc)
			throws CloneNotSupportedException {
		List<Leveldto> resultList = new ArrayList<>();
		List<Object[]> resultsDataList = (List<Object[]>) daoImpl.executeSelectQuery(query, null, null);
		if (resultsDataList != null && !resultsDataList.isEmpty()) {
			for (int i = 0; i < resultsDataList.size(); i++) {
				Leveldto dto = (Leveldto) selectedHierarchyLevelDto.clone();
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

	private GtnForecastHierarchyInputBean createInputBean(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, String dedLevel,
			String dedValue, int relationVersionNo, Date forecastEligibleDate, boolean isNdc) {
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

	private String getLoadDataQuery(GtnForecastHierarchyInputBean inputBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL,
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
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
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	@SuppressWarnings("unchecked")
	public List<String> getSelectedCustomerLevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList, String dedLevel,
			String dedValue, int relationVersionNo, Date forecastEligibleDate, int lastLevelNo) {
		List<String> commaSeperatedList = new ArrayList<>();
		String finalQuery = getQueryForCustomer(selectedHierarchyLevelDto, relationshipSid, groupFilteredCompanies,
				dedLevel, dedValue, relationVersionNo, forecastEligibleDate, levelHierarchyLevelDefinitionList,
				lastLevelNo);
		List<String> resultsDataList = (List<String>) daoImpl.executeSelectQuery(finalQuery, null, null);
		for (String data : resultsDataList) {
			commaSeperatedList.add("'".concat(data).concat("'"));
		}
		return commaSeperatedList;
	}



	private String getQueryForCustomer(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, String dedLevel, String dedValue, int relationVersionNo,
			Date forecastEligibleDate, List<Leveldto> levelHierarchyLevelDefinitionList, int lastLevelNo) {
		Leveldto LastHierarchyLevelDto = Leveldto.getLastLinkedLevel(levelHierarchyLevelDefinitionList);
		String queryString = getQueryForSelectedCustomer(selectedHierarchyLevelDto,
				groupFilteredCompanies, dedLevel, dedValue);
		List<String> inputList = new ArrayList<>();
		StringBuilder query = new StringBuilder(queryString);
		inputList.add(String.valueOf(relationshipSid));
		inputList.add(String.valueOf(LastHierarchyLevelDto.getLevelNo()));
		inputList.add(String.valueOf(relationVersionNo));
		inputList.add(String.valueOf(relationVersionNo));
		inputList.add(String.valueOf(lastLevelNo));
		if (forecastEligibleDate != null) {
			inputList.add(dateFormat.format(forecastEligibleDate));
			inputList.add(dateFormat.format(forecastEligibleDate));
			query.append("AND (CONTRACT_ELIGIBLE_DATE >= '?' OR CONTRACT_ELIGIBLE_DATE IS NULL)");
			query.append("AND (CFP_ELIGIBLE_DATE >= '?' OR CFP_ELIGIBLE_DATE IS NULL)");
		}
		return QueryUtils.getQuery(query.toString(), inputList);
	}

	private String getQueryForSelectedCustomer(Leveldto selectedHierarchyLevelDto,
			List<String> groupFilteredCompanies, String dedLevel, String dedValue) {

		GtnForecastHierarchyInputBean inputBean = createInputForselecteCustomer(selectedHierarchyLevelDto,
				groupFilteredCompanies, dedLevel, dedValue);
		inputBean.setSelectedHierarchyLevelDto(
				LevelDtoToRelationShipBeanConverter.convertLevelDtoToRelationBean(selectedHierarchyLevelDto));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_CUSTOMER,
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	private GtnForecastHierarchyInputBean createInputForselecteCustomer(Leveldto selectedHierarchyLevelDto,
			List<String> groupFilteredCompanies,
			String dedLevel, String dedValue) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedHierarchyLevelDto(
				LevelDtoToRelationShipBeanConverter.convertLevelDtoToRelationBean(selectedHierarchyLevelDto));
		inputBean.setGroupFilterCompenies(groupFilteredCompanies);
		inputBean.setDeductionLevel(dedLevel);
		inputBean.setDeductionValue(dedValue);
		return inputBean;
	}
	public List<Leveldto> loadAvailableProductlevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<Leveldto> selectedCustomerContractList, boolean isNdc,
			String dedLevel, String dedValue, int relationVersionNo, int customerRelationVersionNo,
			Object businessUnitValue, 
			Map<String, String> productDescMap)
			throws CloneNotSupportedException {
		GtnForecastHierarchyInputBean inputBean = createInputBean(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredItems, dedLevel, dedValue, relationVersionNo, null,
				Boolean.FALSE);


		inputBean.setBusinessUnitValue(String.valueOf(businessUnitValue));
		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRelationVersionNo);
		if (selectedCustomerContractList != null && !selectedCustomerContractList.isEmpty()) {
			inputBean.setSelectedCustomerList(
					LevelDtoToRelationShipBeanConverter.convetToRelationBean(selectedCustomerContractList));
			inputBean.setSelectedCustomerHierarcySid(selectedCustomerContractList.get(0).getHierarchyId());
			inputBean
					.setSelectedCustomerHierarchyVersionNo(selectedCustomerContractList.get(0).getHierarchyVersionNo());
		}
		inputBean.setNdc(isNdc);
		String query = getLoadProductDataQuery(inputBean);
		return customizeRelation(query, selectedHierarchyLevelDto, productDescMap, relationVersionNo, isNdc);
	}

	public void ccpHierarchyInsert(GtnSmallHashMap currentTableNames, List<Leveldto> selectedCustomerContractList,
			List<Leveldto> selectedProductList, DataSelectionDTO dto) {
		GtnForecastHierarchyInputBean inputBean = createInputBeanForCCPInsert(currentTableNames,
				selectedCustomerContractList, selectedProductList, dto);
		insertToCCp(inputBean);
	}
	private GtnForecastHierarchyInputBean createInputBeanForCCPInsert(final GtnSmallHashMap tempTableNames,
			final List<Leveldto> selectedCustomerContractList, final List<Leveldto> selectedProductList,
			DataSelectionDTO dto) {

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setTempTableMap(convertToHashMap(tempTableNames));
		inputBean.setSelectedCustomerList(
				LevelDtoToRelationShipBeanConverter.convetToRelationBean(selectedCustomerContractList));
		inputBean.setSelectedProductList(LevelDtoToRelationShipBeanConverter.convetToRelationBean(selectedProductList));
		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(dto.getCustomerRelationShipVersionNo());
		inputBean.setSelectedProductRelationShipBuilderVersionNo(dto.getProductRelationShipVersionNo());
		inputBean.setSelectedCustomerHierarcySid(
				dto.getCustomerHierSid() == null ? 0 : Integer.parseInt(dto.getCustomerHierSid()));
		inputBean.setSelectedProductHierarcySid(
				dto.getProdHierSid() == null ? 0 : Integer.parseInt(dto.getProdHierSid()));
		inputBean.setSelectedProductRelationShipBuilderSid(dto.getProdRelationshipBuilderSid() == null ? 0
				: Integer.parseInt(dto.getProdRelationshipBuilderSid()));
		inputBean.setSelectedCustomerHierarchyVersionNo(dto.getCustomerHierVersionNo());
		inputBean.setSelectedProductHierarchyVersionNo(dto.getProductHierVersionNo());
		inputBean.setProjectionId(dto.getProjectionId());
		return inputBean;
	}

	private Map<String, String> convertToHashMap(GtnSmallHashMap tempTableNames) {
		Map<String, String> tempMap = new HashMap<>();

		for (int i = 0; i < tempTableNames.size(); i++) {
			Pairs pair = tempTableNames.getIndex(i);
			tempMap.put(pair.getKey().toString(), pair.getValue().toString());
		}

		return tempMap;
	}

	public void ccpHierarchyInsertARP(GtnSmallHashMap currentTableNames, List<Leveldto> selectedCustomerContractList,
			List<Leveldto> selectedProductList, DataSelectionDTO dto, String deductionLevel, String dedValue) {
		GtnForecastHierarchyInputBean inputBean = createInputBeanForCCPInsert(currentTableNames,
				selectedCustomerContractList, selectedProductList, dto);
		inputBean.setDeductionLevel(deductionLevel);
		inputBean.setDeductionValue(dedValue);
		insertToCCp(inputBean);
	}


	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo, int relationVersionNo) {
		Map<String, String> relationMap = new HashMap<>();
		String query = getLevelMapValueMapQuery(relationshipBuilderSID, relationVersionNo, hierarchyBuilderSid,
				hierarchyVersionNo);
		List<Object[]> list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);
		for (Object[] leveldto2 : list) {
			if (leveldto2[0] != null && leveldto2[1] != null)
				relationMap.put(leveldto2[0].toString(), leveldto2[1].toString());
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
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	/**
	 * Gets the relation ship values.
	 *
	 * @param projectionId
	 * @param isCustomer
	 * @return the relation ship values
	 */
	@SuppressWarnings("unchecked")
	public List<Leveldto> getRelationShipValues(final int projectionId, final boolean isCustomer, final Object levelNo,
			final Map<String, String> descriptionMap) {

		List<Leveldto> resultList;
		String queryName;
		List<Object> input = new ArrayList<>();
		input.add(projectionId);
		if (levelNo == null) {
			input.add(" ");
		} else {
			input.add(" AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO <= '" + levelNo + "'");
		}
		if (isCustomer) {
			queryName = "selectCustomerQueryForViewCustomization";
		} else {
			queryName = "selectProductQueryForViewCustomization";
		}

		List<Object[]> resultDataList = QueryUtils.getAppData(input, queryName, null);
		resultList = customizeRelationAndHierarchyData(descriptionMap, resultDataList);
		return resultList;
	}

	private List<Leveldto> customizeRelationAndHierarchyData(final Map<String, String> descriptionMap,
			List<Object[]> resultDataList) {
		List<Leveldto> resultList = new ArrayList<>();
		for (Object[] objects : resultDataList) {
			Leveldto levelDto = new Leveldto();
			levelDto.setLevelNo(getIntegerValue(objects, 0));
			levelDto.setLevelValueReference(getStringValue(objects, 1));
			levelDto.setTableName(getStringValue(objects, 2));
			levelDto.setFieldName(getStringValue(objects, 3));
			levelDto.setLevel(getStringValue(objects, 4));
			levelDto.setHierarchyLevelDefnId(getStringValue(objects, 5));
			levelDto.setHierarchyId(getIntegerValue(objects, 6));
			levelDto.setRelationshipLevelValue(String.valueOf(objects[7]));
			levelDto.setParentNode(String.valueOf(objects[NumericConstants.EIGHT]));
			levelDto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.NINE]));
			levelDto.setHierarchyNo(String.valueOf(objects[NumericConstants.TEN]));
			levelDto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.ELEVEN]));
			levelDto.setRelationShipVersionNo(Integer.parseInt(objects[NumericConstants.TWELVE].toString()));
			if (descriptionMap != null) {
				levelDto.setLevel(descriptionMap.get(levelDto.getHierarchyNo()));
				levelDto.setDisplayValue(descriptionMap.get(levelDto.getHierarchyNo()));
			}
			resultList.add(levelDto);

		}
		return resultList;
	}

	private String getStringValue(Object[] objects, int index) {
		return objects[index] == null ? "" : objects[index].toString();
	}

	private Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getVersionNoList(Object selectedProductRelation) {
		int relationShipBuilderSid = Integer.parseInt(selectedProductRelation.toString());
		List<Integer> input = new ArrayList<>();
		input.add(relationShipBuilderSid);
		return (List<Object[]>) QueryUtils.getAppData(input, "getRelationshipVersionNo", null);
	}

	public String getFinalChildLevelQueryForProduct(Leveldto selectedHierarchyLevelDto, int relationVersionNo,
			String businessUnitValue, int lowestLevelNo, int subListIndex) {
		List<Leveldto> hierarchyLevelDefinitionList = getHierarchyLevelDefinition(
				selectedHierarchyLevelDto.getHierarchyId(), selectedHierarchyLevelDto.getHierarchyVersionNo());
		List<String> input = new ArrayList<>();
		input.add(String.valueOf(relationVersionNo));
		input.add("'" + selectedHierarchyLevelDto.getHierarchyNo() + "'");
		input.add(String.valueOf(relationVersionNo));
		input.add(String.valueOf(lowestLevelNo + 1));
		input.add("'" + selectedHierarchyLevelDto.getHierarchyNo() + "'");
		List<String> whereQuerys = getRelationQueries(
				Integer.parseInt(selectedHierarchyLevelDto.getRelationShipBuilderId()), relationVersionNo,
				hierarchyLevelDefinitionList.toArray(new Leveldto[hierarchyLevelDefinitionList.size()]));
		if (!whereQuerys.isEmpty()) {
			input.addAll(whereQuerys.subList(0, subListIndex - 1));
		}
		String finalQuery = getChildLevelQueryForProduct(selectedHierarchyLevelDto, businessUnitValue);
		return QueryUtils.getQuery(finalQuery, input);
	}

	private List<String> getRelationQueries(int relationshipSid, int relationVersionNo,
			Leveldto... levelHierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (Leveldto levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(levelDto.getLevelNo());
				input.add(relationshipSid);
				input.add(relationVersionNo);
				String relationQuery = QueryUtils.getQuery(input, "relationShipSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

	private String getChildLevelQueryForProduct(Leveldto selectedHierarchyLevelDto, String businessUnitValue) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedHierarchyLevelDto(
				LevelDtoToRelationShipBeanConverter.convertLevelDtoToRelationBean(selectedHierarchyLevelDto));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		inputBean.setBusinessUnitValue(businessUnitValue);
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationProductResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_PRODUCT,
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationProductResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
		
	}


	public static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}

	public boolean isRelationUPdated(int projectionIdValue) {
		List<Integer> input = new ArrayList<>();
		input.add(projectionIdValue);
		input.add(projectionIdValue);
		List results = QueryUtils.getAppData(input, "getRelationShiPVersionUpdatedStatus", null);
		return Integer.parseInt(results.get(0).toString()) == 1;
	}

	public void waitForAutomaticRelation() {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		client.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
				+ GtnWebServiceUrlConstants.WAIT_AUTOMATIC_RELATION_UPDATE, request, getGsnWsSecurityToken());
	}

	private void insertToCCp(GtnForecastHierarchyInputBean inputBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE + GtnWebServiceUrlConstants.GTN_CCP_INSERT, request,
				getGsnWsSecurityToken());
	}

}
