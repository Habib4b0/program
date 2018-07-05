package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkCustomerLevelLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkLevelValueMapQueryService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkProductLevelLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkSelectedTblLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnWsDataselectionHierarchyUpdateService;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE)
public class GtnWsDataSelectionEditController {

	private static final String FINAL_QUERY = "finalQuery is ------->";
	
	@Autowired
	private GtnFrameworkCustomerLevelLoadService customerLevelService;
	@Autowired
	private GtnFrameworkProductLevelLoadService productLevelService;
	@Autowired
	private GtnFrameworkSelectedTblLoadService productSelectedLoadService;
	@Autowired
	private GtnFrameworkLevelValueMapQueryService levelValueMapQueryService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);

	public GtnWsDataSelectionEditController() {
		super();
	}

	@Autowired
	private GtnWsDataselectionHierarchyUpdateService service;

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_EDIT_CUSTHIERARCHY_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkAndUpdateCustAndProdHierarchy(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		service.checkAndInsertHierarchy(gtnWsRequest.getGtnWshirarchyInsertRequest());

		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_PRODUCT_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = productLevelService.getProductLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = customerLevelService.getCustomerLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_PRODUCT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductSelecedChild(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		try {
			GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
			GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
			String finalQuery = productSelectedLoadService.getChildLevelQueryForProduct(inputBean);
			inputBean.setHieraryQuery(finalQuery);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
			GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
			forecastResponse.setInputBean(inputBean);
			response.setGtnWsForecastResponse(forecastResponse);
			return response;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_CUSTOMER, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerSelecedChild(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
			GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
			String finalQuery = productSelectedLoadService.getQueryForSelectedCustomer(inputBean);
			inputBean.setHieraryQuery(finalQuery);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
			GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
			forecastResponse.setInputBean(inputBean);
			response.setGtnWsForecastResponse(forecastResponse);
			return response;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadLevelValueMapQuery(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = levelValueMapQueryService.getQueryForLevelValueMap(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_REPORTDATASELECTION_LOAD_LEVELVALUE_MAP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadLevelValueMap(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		Map<String, String> relationMap = new HashMap<>();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest forecastRequet = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		List<Object[]> resultList = levelValueMapQueryService.getLevelValueMap(inputBean);
		for (Object[] levelData : resultList) {
			if (levelData[0] != null && levelData[1] != null) {
				relationMap.put(levelData[0].toString(), levelData[1].toString());
			}
		}
		inputBean.setTempTableMap(relationMap);
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_REPORTCUSTOMER_HIERARCHYLEVEL_VALUES, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyLevelValues(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest forecastRequest = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequest.getInputBean();
		List<GtnReportHierarchyLevelBean> levelList = new ArrayList<>();
		List<Object[]> resultList = levelValueMapQueryService.getHierarchyLevelValues(inputBean);
		for (Object[] object : resultList) {
			GtnReportHierarchyLevelBean levelBean = new GtnReportHierarchyLevelBean();
			levelBean.setLevelNo(getIntegerValue(object, 0));
			levelBean.setLevelValueReference(getStringValue(object, 1));
			levelBean.setTableName(object[2] == null ? "" : object[2].toString());
			levelBean.setFieldName(object[3] == null ? "" : object[3].toString());
			levelBean.setLevel(object[4] == null ? "" : object[4].toString());
			levelBean.setHierarchyLevelDefSid(object[5] == null ? "0" : object[5].toString());
			levelBean.setHierarchyDefSid((Integer.valueOf(object[6] == null ? "0" : object[6].toString())));
			levelBean.setHierarchyType(object[7] == null ? "" : object[7].toString());
			levelBean.setHierarchyVersionNo(inputBean.getHierarchyVersionNo());
			levelList.add(levelBean);
		}
		inputBean.setLevelList(levelList);
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	private Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

	private String getStringValue(Object[] objects, int index) {
		return objects[index] == null ? "" : objects[index].toString();
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_LEFT_TABLELOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustHierarchyLeftTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = new ArrayList(
				gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getQueryInputList());
		String query = (String) inputList.get(0);
		List<Object[]> searchResultList = levelValueMapQueryService.loadCustHierarchyAvailableTable(query);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(searchResultList);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(dataTable);
		gtnResponse.setGtnSerachResponse(searchResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_RIGHT_TABLELOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustHierarchyRightTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getQueryInputList();
		List<GtnWsRecordBean> recordBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getRecordBean();
		GtnReportHierarchyLevelBean selectedHierarchyBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getHierarchyInputBean();
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		GtnReportHierarchyLevelBean lastHierarchyLevelBean = GtnReportHierarchyLevelBean
				.getLastLinkedLevel(gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getHierarchyLevelList());
		inputBean.setSelectedHierarchyLevelDto(
				convertParametersToRelation(inputList, recordBean, selectedHierarchyBean));
		String finalQuery = productSelectedLoadService.getQueryForSelectedCustomer(inputBean);
		StringBuilder inputQuery = new StringBuilder(finalQuery);
		LOGGER.info(FINAL_QUERY + finalQuery);

		List<Object> inputValuesList = new ArrayList<>();
		GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto = inputBean.getSelectedHierarchyLevelDto();
		inputValuesList.add(selectedHierarchyLevelDto.getRelationshipBuilderSid());
		inputValuesList.add(String.valueOf(lastHierarchyLevelBean.getLevelNo()));
		inputValuesList.add(String.valueOf(inputList.get(4)));
		inputValuesList.add(selectedHierarchyLevelDto.getHierarchyNo() + "%'");
		inputValuesList.add(String.valueOf(inputList.get(4)));
		inputValuesList.add(String.valueOf(inputList.get(6)));
		inputValuesList.add(selectedHierarchyLevelDto.getHierarchyNo() + "'");
		Date forecastEligibleDate = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getForecastEligibleDate();
		if (forecastEligibleDate != null) {
			inputValuesList.add(dateFormatter.format(forecastEligibleDate));
			inputValuesList.add(dateFormatter.format(forecastEligibleDate));
			inputQuery.append("AND (CONTRACT_ELIGIBLE_DATE >= '?' OR CONTRACT_ELIGIBLE_DATE IS NULL)");
			inputQuery.append("AND (CFP_ELIGIBLE_DATE >= '?' OR CFP_ELIGIBLE_DATE IS NULL)");
		}
		List<Object[]> resultList = productSelectedLoadService.getResultForSelectedCustomer(inputQuery,
				inputValuesList);
		List<Object> inputsForRelationQuery = new ArrayList<>();
		String hierarchyNo = StringUtils.join(resultList, "','");
		hierarchyNo += "'," + selectedHierarchyLevelDto.getHierarchyNo();
		inputsForRelationQuery.add("'" + hierarchyNo + "'");
		inputsForRelationQuery.add(String.valueOf(inputList.get(6)));
		inputsForRelationQuery.add(String.valueOf(inputList.get(4)));
		inputsForRelationQuery.add(String.valueOf(inputList.get(5)));
		List<Object[]> results = productSelectedLoadService.getChildLevelQueryForCustomer(inputsForRelationQuery);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(results);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(dataTable);
		gtnResponse.setGtnSerachResponse(searchResponse);
		return gtnResponse;
	}
	
	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODHIERARCHY_RIGHT_TABLELOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProdHierarchyRightTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getQueryInputList();
		List<GtnWsRecordBean> recordBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getRecordBean();
		GtnReportHierarchyLevelBean selectedHierarchyBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getHierarchyInputBean();
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		GtnReportHierarchyLevelBean lastHierarchyLevelBean = GtnReportHierarchyLevelBean
				.getLastLinkedLevel(gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getHierarchyLevelList());
		inputBean.setSelectedHierarchyLevelDto(
				convertParametersToRelation(inputList, recordBean, selectedHierarchyBean));
		inputBean.setBusinessUnitValue(String.valueOf(inputList.get(7)));
		inputBean.setRelationVersionNo(Integer.parseInt(String.valueOf(inputList.get(4))));
		inputBean.setLowestLevelNo(Integer.parseInt(String.valueOf(inputList.get(6))));
		inputBean.setLevelNo(Integer.parseInt(String.valueOf(inputList.get(6))));
		String finalQuery = productSelectedLoadService.getChildLevelQueryForReportProduct(inputBean);
		StringBuilder inputQuery = new StringBuilder(finalQuery);
		LOGGER.info(FINAL_QUERY + finalQuery);

		GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto = inputBean.getSelectedHierarchyLevelDto();
		
		List<Object> input = new ArrayList<>();
		input.add(selectedHierarchyLevelDto.getRelationshipVersionNo());
		input.add(lastHierarchyLevelBean.getLevelNo());
		input.add( selectedHierarchyLevelDto.getHierarchyNo() + "%'");
		input.add(selectedHierarchyLevelDto.getRelationshipBuilderSid());
		input.add(selectedHierarchyLevelDto.getRelationshipVersionNo());
		input.add(Integer.parseInt(String.valueOf(inputList.get(6))) + 1);
		input.add(selectedHierarchyLevelDto.getHierarchyNo() + "'");

		List<Object[]> resultList = productSelectedLoadService.getResultForSelectedProduct(inputQuery, input);
		List<String> hierarchyNoList = new ArrayList<>();
		for (Object[] object : resultList) {
			hierarchyNoList.add(object[8].toString());
		}
		List<Object> inputsForProductRelationQuery = new ArrayList<>();
		String hierarchyNo = StringUtils.join(hierarchyNoList, "','");
		hierarchyNo += "'," + selectedHierarchyLevelDto.getHierarchyNo();
		inputsForProductRelationQuery.add("'" + hierarchyNo + "'");
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(6)));
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(4)));
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(5)));
		List<Object[]> results = productSelectedLoadService.getChildLevelQueryForCustomer(inputsForProductRelationQuery);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(results);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(dataTable);
		gtnResponse.setGtnSerachResponse(searchResponse);
		return gtnResponse;
	}
	
	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_ALL_DATA_TABLELOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadAllHierarchyRightTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getQueryInputList();
		GtnReportHierarchyLevelBean allDataSelectedHierarchyBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getHierarchyInputBean();
		List<Object[]> searchResultList = levelValueMapQueryService
				.loadCustHierarchyAvailableTable(String.valueOf(inputList.get(0)));
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(searchResultList);
		GtnSerachResponse serachResponse = new GtnSerachResponse();
		serachResponse.setResultSet(dataTable);
		List<GtnWsRecordBean> beanList = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : serachResponse.getResultSet().getDataTable()) {
			GtnWsRecordBean recordBean = new GtnWsRecordBean();
			recordBean.setProperties(record.getColList());
			beanList.add(recordBean);
		}
		GtnReportHierarchyLevelBean lastHierarchyLevelBean = GtnReportHierarchyLevelBean
				.getLastLinkedLevel(gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getHierarchyLevelList());
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedHierarchyLevelDto(
				convertParametersToRelation(inputList, beanList, allDataSelectedHierarchyBean));
		String finalQuery = productSelectedLoadService.getQueryForSelectedCustomer(inputBean);
		StringBuilder inputQuery = new StringBuilder(finalQuery);
		LOGGER.info(FINAL_QUERY + finalQuery);
		GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto = inputBean.getSelectedHierarchyLevelDto();
		List<Object> inputValuesList = new ArrayList<>();
		inputValuesList.add(selectedHierarchyLevelDto.getRelationshipBuilderSid());
		inputValuesList.add(String.valueOf(lastHierarchyLevelBean.getLevelNo()));
		inputValuesList.add(String.valueOf(inputList.get(4)));
		inputValuesList.add(String.valueOf("'%'"));
		inputValuesList.add(String.valueOf(inputList.get(4)));
		inputValuesList.add(String.valueOf(inputList.get(6)));
		inputValuesList.add(selectedHierarchyLevelDto.getHierarchyNo() + "'");
		Date forecastEligibleDate = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getForecastEligibleDate();
		if (forecastEligibleDate != null) {
			inputValuesList.add(dateFormatter.format(forecastEligibleDate));
			inputValuesList.add(dateFormatter.format(forecastEligibleDate));
			inputQuery.append("AND (CONTRACT_ELIGIBLE_DATE >= '?' OR CONTRACT_ELIGIBLE_DATE IS NULL)");
			inputQuery.append("AND (CFP_ELIGIBLE_DATE >= '?' OR CFP_ELIGIBLE_DATE IS NULL)");
		}
		List<Object[]> resultList = productSelectedLoadService.getResultForSelectedCustomer(inputQuery,
				inputValuesList);
		List<Object> inputsForRelationQuery = new ArrayList<>();
		String hierarchyNo = StringUtils.join(resultList, "','");
		hierarchyNo += "'," + selectedHierarchyLevelDto.getHierarchyNo();
		inputsForRelationQuery.add("'" + hierarchyNo + "'");
		inputsForRelationQuery.add(String.valueOf(inputList.get(6)));
		inputsForRelationQuery.add(String.valueOf(inputList.get(4)));
		inputsForRelationQuery.add(String.valueOf(inputList.get(5)));
		List<Object[]> results = productSelectedLoadService.getChildLevelQueryForCustomer(inputsForRelationQuery);
		GtnUIFrameworkDataTable finalDataTable = new GtnUIFrameworkDataTable();
		finalDataTable.addData(results);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(finalDataTable);
		gtnResponse.setGtnSerachResponse(searchResponse);
		return gtnResponse;
	}
	
	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODHIERARCHY_ALL_DATA_TABLELOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadAllProductHierarchyRightTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getQueryInputList();
		GtnReportHierarchyLevelBean allDataSelectedHierarchyBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getHierarchyInputBean();
		List<Object[]> searchResultList = levelValueMapQueryService
				.loadCustHierarchyAvailableTable(String.valueOf(inputList.get(0)));
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(searchResultList);
		GtnSerachResponse serachResponse = new GtnSerachResponse();
		serachResponse.setResultSet(dataTable);
		List<GtnWsRecordBean> beanList = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : serachResponse.getResultSet().getDataTable()) {
			GtnWsRecordBean recordBean = new GtnWsRecordBean();
			recordBean.setProperties(record.getColList());
			beanList.add(recordBean);
		}
		GtnReportHierarchyLevelBean lastHierarchyLevelBean = GtnReportHierarchyLevelBean
				.getLastLinkedLevel(gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getHierarchyLevelList());
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedHierarchyLevelDto(
				convertParametersToRelation(inputList, beanList, allDataSelectedHierarchyBean));
		inputBean.setBusinessUnitValue(String.valueOf(inputList.get(7)));
		inputBean.setRelationVersionNo(Integer.parseInt(String.valueOf(inputList.get(4))));
		inputBean.setLowestLevelNo(Integer.parseInt(String.valueOf(inputList.get(6))));
		inputBean.setLevelNo(Integer.parseInt(String.valueOf(inputList.get(6))));
		String finalQuery = productSelectedLoadService.getChildLevelQueryForReportProduct(inputBean);
		StringBuilder inputQuery = new StringBuilder(finalQuery);
		LOGGER.info(FINAL_QUERY + finalQuery);

		GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto = inputBean.getSelectedHierarchyLevelDto();
		
		List<Object> input = new ArrayList<>();
		input.add(selectedHierarchyLevelDto.getRelationshipVersionNo());
		input.add(lastHierarchyLevelBean.getLevelNo());
		input.add( "'%'");
		input.add(selectedHierarchyLevelDto.getRelationshipBuilderSid());
		input.add(selectedHierarchyLevelDto.getRelationshipVersionNo());
		input.add(Integer.parseInt(String.valueOf(inputList.get(6))) + 1);
		input.add(selectedHierarchyLevelDto.getHierarchyNo() + "'");

		List<Object[]> resultList = productSelectedLoadService.getResultForSelectedProduct(inputQuery, input);
		List<String> hierarchyNoList = new ArrayList<>();
		for (Object[] object : resultList) {
			hierarchyNoList.add(object[8].toString());
		}
		List<Object> inputsForProductRelationQuery = new ArrayList<>();
		String hierarchyNo = StringUtils.join(hierarchyNoList, "','");
		hierarchyNo += "'," + selectedHierarchyLevelDto.getHierarchyNo();
		inputsForProductRelationQuery.add("'" + hierarchyNo + "'");
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(6)));
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(4)));
		inputsForProductRelationQuery.add(String.valueOf(inputList.get(5)));
		List<Object[]> results = productSelectedLoadService.getChildLevelQueryForCustomer(inputsForProductRelationQuery);
		GtnUIFrameworkDataTable productResultsDataTable = new GtnUIFrameworkDataTable();
		productResultsDataTable.addData(results);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(productResultsDataTable);
		gtnResponse.setGtnSerachResponse(searchResponse);
		return gtnResponse;
		
	}
	

	private GtnFrameworkRelationshipLevelDefintionBean convertParametersToRelation(List<Object> inputList,
			List<GtnWsRecordBean> recordBean, GtnReportHierarchyLevelBean selectedHierarchyBean) {
		GtnFrameworkRelationshipLevelDefintionBean relationshipLevelDefBean = new GtnFrameworkRelationshipLevelDefintionBean();
		GtnWsRecordBean finalRecordBean = recordBean.get(0);
		StringBuilder hierarchyNo = new StringBuilder();
		relationshipLevelDefBean.setLevelName(selectedHierarchyBean.getLevel());
		relationshipLevelDefBean.setLevelNo(selectedHierarchyBean.getLevelNo());
		relationshipLevelDefBean.setRelationshipLevelSid(finalRecordBean.getIntegerPropertyByIndex(3));
		relationshipLevelDefBean.setRelationShipLevelValue(finalRecordBean.getIntegerPropertyByIndex(0));
		relationshipLevelDefBean.setTableName(selectedHierarchyBean.getTableName());
		relationshipLevelDefBean.setFieldName(selectedHierarchyBean.getFieldName());
		relationshipLevelDefBean.setHierarchyDefinitionSid(selectedHierarchyBean.getHierarchyDefSid());
		relationshipLevelDefBean
				.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyBean.getHierarchyLevelDefSid()));
		relationshipLevelDefBean.setLevelValueReference(selectedHierarchyBean.getLevelValueReference());
		relationshipLevelDefBean.setRelationshipBuilderSid(finalRecordBean.getIntegerPropertyByIndex(5));
		relationshipLevelDefBean.setRelationshipVersionNo(Integer.parseInt(String.valueOf(inputList.get(4))));
		relationshipLevelDefBean.setHierarchyVersionNo(Integer.parseInt(String.valueOf(inputList.get(5))));
		relationshipLevelDefBean.setHierarchyCategory(selectedHierarchyBean.getHierarchyType());
		for (int i = 0; i < recordBean.size(); i++) {
			GtnWsRecordBean gtnWsRecordBean = recordBean.get(i);
			hierarchyNo.append("'");
			hierarchyNo.append(gtnWsRecordBean.getStringPropertyByIndex(4));
			if (i != recordBean.size() - 1) {
				hierarchyNo.append("'");
				hierarchyNo.append(",");
			}
		}
		relationshipLevelDefBean.setHierarchyNo(hierarchyNo.toString());

		return relationshipLevelDefBean;
	}
}
