package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportWebsevice;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceDateResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

@RestController
@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_SERVICE)
public class GtnWsReportController {

	public GtnWsReportController() {
		super();
	}

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportController.class);

	@Autowired
	private GtnWsReportWebsevice gtnWsReportWebsevice;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyResults(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadHierarchyResults(request, true);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadHierarchyResults(request, false);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_COUNT_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPrivateViewResultsCount(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.getLoadViewResultsCount(gtnUIFrameworkWebserviceRequest, true,
				0);
		gtnSearchResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadViewResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadViewResults(gtnUIFrameworkWebserviceRequest, true, 0);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_COUNT_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPublicViewResultsCount(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> publicViewResultList = gtnWsReportWebsevice
				.getLoadViewResultsCount(gtnUIFrameworkWebserviceRequest, false, 0);
		gtnSearchResponse.setCount(Integer.parseInt(String.valueOf(publicViewResultList.get(0))));
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadPublicViewResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> publicViewResultList = gtnWsReportWebsevice.loadViewResults(gtnUIFrameworkWebserviceRequest,
				false, 0);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(publicViewResultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_REPORT_PROFILE_LOOKUP_SERVICE_COUNT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getReportProfileResultsCount(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.getLoadViewResultsCount(gtnUIFrameworkWebserviceRequest, true,
				1);
		gtnSearchResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_REPORT_PROFILE_LOOKUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadReportProfileResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadViewResults(gtnUIFrameworkWebserviceRequest, true, 1);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOADELIGIBLEDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadForecastEligibleDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnUIFrameworkWebserviceDateResponse dateResponse = new GtnUIFrameworkWebserviceDateResponse();
		Date forecastEligibleDate = gtnWsReportWebsevice.loadForecastEligibleDate();
		dateResponse.setResultValue(forecastEligibleDate);
		gtnResponse.setGtnUIFrameworkWebserviceDateResponse(dateResponse);
		return gtnResponse;
	}

	@RequestMapping(value = "/hierarchyDefinition", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getHierarchySidAndLevelDefId(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {

		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnReportHierarchyLookupBean lookupBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		String query = GtnWsQueryConstants.HIERARCHY_SID_AND_LEVEL_DEFINITION_SID;
		query.replace("@HIERARCHY_DEFINITION_SID", String.valueOf(lookupBean.getHierarchyDefSid()))
				.replace("@VERSION_NO", String.valueOf(lookupBean.getVersionNo()));
		List<Object[]> results = executeQuery(query);
		GtnWsReportResponse gtnWsReportResponse = new GtnWsReportResponse();
		gtnWsReportResponse.setResultList(results);
		response.setGtnWsReportResponse(gtnWsReportResponse);
		return response;
	}

	@RequestMapping(value = "/gtnWsReportComboboxLoad", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getComboBoxResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {
			GtnWsReportAllListConfig gtnWsReportAllListConfig = new GtnWsReportAllListConfig();
			Map<String, String> comboBoxTypeResponseMap = gtnWsReportAllListConfig.getComboboxLoadMap();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String comboBoxType = generalWSRequest.getComboBoxType();
			String query = comboBoxTypeResponseMap.get(comboBoxType);
			List<Object[]> resultList = null;
			if (query != null) {
				comboBoxType = getComboboxTypeForReportFromAndToDate(query);
				resultList = executeQuery(comboBoxType);
				GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
				comboBoxResponse.setComboBoxList(resultList);
				gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
			}

		} catch (Exception exception) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	private String getComboboxTypeForReportFromAndToDate(String comboBoxType) {
		List<Object[]> resultList = null;
		List<Boolean> checkList;
		String frequency = "Quarter";
		String subQuery = "";
		String mainQuery = GtnWsQueryConstants.MAIN_QUERY_REPORT_FROM_AND_TO_DATE;
		String checkProcessMode = GtnWsQueryConstants.CHECK_PROCESS_MODE_FOR_REPORT_FROM_AND_TO_DATE;
		try {
			checkList = executeQuery(checkProcessMode);
			boolean processMode = (checkList.get(0));
			if (processMode) {
				resultList = executeQuery(mainQuery);
				frequency = String.valueOf(resultList.get(0));
			}
			subQuery = gtnWsReportWebsevice.getFromAndToDateLoadQuery(comboBoxType, frequency);
		} catch (Exception e) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + e);
		}

		return subQuery;
	}

	@RequestMapping(value = "/gtnWsReportLoadDataAssumptions", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDataAssumptionsResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse wsResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse wsGeneralResponse = new GtnWsGeneralResponse();
		GtnSerachResponse wsSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = null;
		wsGeneralResponse.setSucess(true);

		String filter = gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList() == null
				|| gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty() ? ""
						: gtnWsReportWebsevice.setFilterForDataAssumptions(gtnWsRequest);
		String company = String.valueOf(gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getCompanyReport());
		String businessunit = String
				.valueOf(gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getBusinessUnitReport());

		resultList = executeQuery(
				GtnWsQueryConstants.DATA_ASSUMPTIONS_COUNT_QUERY.replace(GtnWsQueryConstants.FILTER_CONSTANT, filter)
						.replace(GtnWsQueryConstants.FILTER_COMPANY, company)
						.replace(GtnWsQueryConstants.FILTER_BUSINESSUNIT, businessunit));
		wsSearchResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));

		String finalQuery = GtnWsQueryConstants.DATA_ASSUMPTIONS_RESULT_QUERY;

		finalQuery = finalQuery.replace(GtnWsQueryConstants.FILTER_CONSTANT, filter);
		finalQuery = finalQuery.replace(GtnWsQueryConstants.FILTER_COMPANY, company);
		finalQuery = finalQuery.replace(GtnWsQueryConstants.FILTER_BUSINESSUNIT, businessunit);
		resultList = executeQuery(finalQuery);
		resultList = gtnWsReportWebsevice.resultListCustomization(resultList);
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData(resultList);
		wsSearchResponse.setResultSet(gtnUIFrameworkDataTable);

		wsResponse.setGtnSerachResponse(wsSearchResponse);

		return wsResponse;
	}

	@RequestMapping(value = "/gtnWsReportLoadDataAssumptionsMultipleTabs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDataAssumptionsMultipleTabResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse wsResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse wsGeneralResponse = new GtnWsGeneralResponse();
		GtnSerachResponse wsSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = null;
		wsGeneralResponse.setSucess(true);
		String finalQuery = null;
		if (gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getReportDataSource() == 3) {
			finalQuery = GtnWsQueryConstants.DATA_ASSUMPTIONS_NO_SOURCE_MULTIPLE_TABS_RESULTS;
		} else {
			finalQuery = GtnWsQueryConstants.DATA_ASSUMPTIONS_MULTIPLE_TABS_RESULTS;
		}

		finalQuery = finalQuery.replace("@projectionMasterSid",
				String.valueOf(gtnWsRequest.getGtnWsReportRequest().getProjectionMasterSid()));
		resultList = executeQuery(finalQuery);
		resultList = gtnWsReportWebsevice.resultListCustomization(resultList);
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData(resultList);
		wsSearchResponse.setResultSet(gtnUIFrameworkDataTable);

		wsResponse.setGtnSerachResponse(wsSearchResponse);
		return wsResponse;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) {
		try {
			gtnSqlQueryEngine.setSessionFactory(sessionFactory);
			return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + e);
			return Collections.emptyList();
		}
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_SAVEVIEW_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		int userId = Integer.parseInt(generalRequest.getUserId());
		int recordCount = gtnWsReportWebsevice.checkViewRecordCount(dataSelectionBean, userId);
		if (recordCount == 0) {
			gtnWsReportWebsevice.saveReportingMaster(dataSelectionBean, userId);
			generalResponse.setSucess(true);
		} else {
			generalResponse.setSucess(false);
		}
		response.setGtnWsGeneralResponse(generalResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_UPDATEVIEW_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse updateView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		int userId = Integer.parseInt(generalRequest.getUserId());
		int recordCount = gtnWsReportWebsevice.checkUpdateViewRecordCount(dataSelectionBean);
		if (recordCount == 0) {
			gtnWsReportWebsevice.saveReportingMaster(dataSelectionBean, userId);
			generalResponse.setSucess(false);
		} else {
			int count = gtnWsReportWebsevice.updateReportingViewMaster(dataSelectionBean, userId);
			if (count == 0) {
				generalResponse.setSucess(false);
			} else {
				generalResponse.setSucess(true);

			}
		}
		response.setGtnWsGeneralResponse(generalResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PROFILE_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getReportProfileSave(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean = reportRequest
				.getReportingDashboardSaveProfileLookupBean();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		int userId = Integer.parseInt(generalRequest.getUserId());
		int recordCount = gtnWsReportWebsevice
				.checkReportProfileViewRecordCount(reportingDashboardSaveProfileLookupBean, userId);
		if (recordCount == 0) {
			gtnWsReportWebsevice.saveReportProfileMaster(reportingDashboardSaveProfileLookupBean, userId);
			generalResponse.setSucess(true);
		} else {
			generalResponse.setSucess(false);
		}
		response.setGtnWsGeneralResponse(generalResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PROFILE_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getReportProfileUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean = reportRequest
				.getReportingDashboardSaveProfileLookupBean();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		int userId = Integer.parseInt(generalRequest.getUserId());
		int recordCount = gtnWsReportWebsevice
				.checkUpdateViewRecordCountForReportProfile(reportingDashboardSaveProfileLookupBean);
		if (recordCount == 0) {
			gtnWsReportWebsevice.saveReportProfileMaster(reportingDashboardSaveProfileLookupBean, userId);
			generalResponse.setSucess(false);
		} else {
			int count = gtnWsReportWebsevice.updateReportProfileMaster(reportingDashboardSaveProfileLookupBean, userId);
			if (count == 0) {
				generalResponse.setSucess(false);
			} else {
				generalResponse.setSucess(true);
			}
		}
		response.setGtnWsGeneralResponse(generalResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORRT_DELETEVIEW_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		int userId = Integer.parseInt(generalRequest.getUserId());
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		return gtnWsReportWebsevice.deleteView(dataSelectionBean, userId);
	}

	@RequestMapping(value = "/reportDeleteValidation", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		int userId = Integer.parseInt(generalRequest.getUserId());
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		return gtnWsReportWebsevice.deleteValidation(dataSelectionBean, userId);
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_COUNTSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getComparisonAvailableTableCount(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice
				.getComparisonAvailableTableCount(gtnUIFrameworkWebserviceRequest);
		searchResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
		response.setGtnSerachResponse(searchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_LOADSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadComparisonAvailableTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadComparisonAvailableTable(gtnUIFrameworkWebserviceRequest);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		searchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(searchResponse);
		return response;
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_UOM_SERVICE)
	public GtnUIFrameworkWebserviceResponse getUOMValues(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		List<Object[]> resultList = null;
		try {
			resultList = gtnWsReportWebsevice.getUOMDDLBValues(
					gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getReportBean().getDataSelectionBean());
			Optional.ofNullable(resultList).ifPresent(e -> {
				List<String> itemCodeList = new ArrayList<>();
				List<String> itemValueList = new ArrayList<>();
				itemCodeList.add(GtnWsQueryConstants.UOM_DEFAULT);
				itemValueList.add(GtnWsQueryConstants.UOM_DEFAULT);
				for (Object[] object : e) {
					itemCodeList.add(String.valueOf(object[0]));
					itemValueList.add(String.valueOf(object[0]));
				}

				comboBoxResponse.setItemCodeList(itemCodeList);
				comboBoxResponse.setItemValueList(itemValueList);
			});
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(e.getErrorMessage(), e);
		}
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_DASHBOARD_LOAD_FROM_AND_TO_IN_DATA_SELECTION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getComboBoxFromAndToInDataSelectionResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {
			String frequency = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getFrequencyName();
			String query = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getFromOrToForDataSelection();
			List<Object[]> resultList = null;
			if (query != null) {
				String queryToBeExecuted = getComboboxTypeForReportFromAndToDateInDataSelection(query, frequency);
				resultList = executeQuery(queryToBeExecuted);
				GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
				comboBoxResponse.setComboBoxList(resultList);
				gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
			}

		} catch (Exception exception) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	private String getComboboxTypeForReportFromAndToDateInDataSelection(String comboBoxType, String frequency) {
		String query = null;
		try {
			query = gtnWsReportWebsevice.getFromAndToDateLoadQuery(comboBoxType, frequency);
		} catch (Exception e) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + e);
		}
		return query;
	}

}
