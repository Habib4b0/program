/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.implementation.ComboBoxSearch;
import com.stpl.gtn.gtn2o.ws.search.implementation.CustomerAndProductGroup;
import com.stpl.gtn.gtn2o.ws.search.implementation.PrivatePublic;

//import com.stpl.gtn.gtn2o.ws.search.implementation.PrivatePublic;

import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

/**
 *
 * @author anandh.karuppusamy
 */
@Service
public class GtnGeneralSearchService extends GtnCommonWebServiceImplClass {


	private GtnGeneralSearchService() {
		super(GtnGeneralSearchService.class);
	}


	@Autowired
	GtnSearchwebServiceSqlService gtnSearchSqlService;

	@Autowired
	private GtnForecastJsonService gtnForecastJsonService;

	private Map<String, SearchInterface> keyMap = null;
	Map<Integer, String> queryMap = null;

	public void init() {
		logger.info("Entering into init method of searchWebservice");
		GtnUIFrameworkWebserviceRequest request = registerWs();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("search webservices registered");

	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("building request to register searchwebservice");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean gtnServiceRegistryBean = new GtnWsServiceRegistryBean();
		getUrl(gtnServiceRegistryBean);
		logger.info("webservice to be registered" + gtnServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(gtnServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		return request;
	}

	public void getUrl(GtnWsServiceRegistryBean gtnServiceRegistryBean) {
		gtnServiceRegistryBean.setWebserviceEndPointUrl(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.generalSearch.endPointUrl"));
		gtnServiceRegistryBean.setRegisteredWebContext(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.generalSearch.endPointServiceName"));
	}

	public GtnUIFrameworkWebserviceResponse commonMethod(
			GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		String key = gtnUiFrameworkWebservicerequest.getGtnWsSearchRequest().getSearchQueryName();
		String query = gtnSearchSqlService.getQuery(key);
		if (keyMap == null) {
			keyMap = new HashMap<>();
			keyMap.put("privatePublic", new PrivatePublic());
			keyMap.put("BusinessUnitGLcomp", new ComboBoxSearch());
			keyMap.put("CompanyMasterGLcomp", new ComboBoxSearch());
			keyMap.put("frequency", new ComboBoxSearch());
			keyMap.put("dataSelectionDeduction", new ComboBoxSearch());
			keyMap.put("CustomerGroup", new CustomerAndProductGroup());
			keyMap.put("ProductGroup", new CustomerAndProductGroup());
		}
		SearchInterface searchInterface = keyMap.get(key);
		GtnUIFrameworkWebserviceResponse response;
		response = searchInterface.getSearch(gtnUiFrameworkWebservicerequest, query);
		return response;
	}

	public GtnUIFrameworkWebserviceResponse saveView(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {

			String viewType = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean()
					.getViewType();
			String viewName = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean()
					.getViewName();
			String userId = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean()
					.getUserId();
			GtnFrameworkForecastDataSelectionBean dataSelectionBean = gtnUiFrameworkWebservicerequest
					.getGtnWsForecastNewArchRequest().getDataSelectionBean();
			String viewData = gtnForecastJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'",
					"\\\\");

			Object[] params = new Object[5];
			params[0] = viewType.replaceAll("\\*", "%");
			params[1] = viewName.replaceAll("\\*", "%");
			params[2] = userId;
			params[3] = userId;
			params[4] = "'" + viewData + "'";

			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING };
			String query = gtnSearchSqlService.getQuery("saveview");
			logger.debug("query for save view" + query);
			GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
			queryExecutorBean.setSqlQuery(query);
			queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
			queryExecutorBean.setParams(params);
			queryExecutorBean.setDataType(dataType);
			GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
			gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
			RestTemplate restTemplate1 = new RestTemplate();
			logger.info("calling query engine via service registry");
			GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(getWebServiceEndpointBasedOnModule(
					"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
					gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
			List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
			GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
			GtnSerachResponse searchResponse = new GtnSerachResponse();
			dataTable.addData(resultList);
			searchResponse.setResultSet(dataTable);
			response.setGtnSerachResponse(searchResponse);
		} catch (Exception e) {
			logger.error("Exception in save view" + e);
		}
		return response;
	}

	public GtnUIFrameworkWebserviceResponse pagedTableSearch(
			GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		if (queryMap == null) {
			queryMap = new HashMap();
			queryMap.put(0, " AND PM.projection_Name like ? ");
			queryMap.put(1, " AND PM.projection_description like ? ");
		}
		String query = gtnSearchSqlService.getQuery("projectionSearch");
		List<GtnWebServiceSearchCriteria> webSearchCriteriaList = gtnUiFrameworkWebservicerequest
				.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
		String fromDate = webSearchCriteriaList.get(0).getFilterValue1();
		String toDate = webSearchCriteriaList.get(1).getFilterValue1();
		LocalDate from = convertStringDateToLocalDate(fromDate);
		LocalDate to = convertStringDateToLocalDate(toDate);
		query = query + " AND PM.CREATED_DATE BETWEEN " + from + " AND " + to;
		StringBuilder stringQuery = new StringBuilder();
		stringQuery.append(query);
		String projectionName = webSearchCriteriaList.get(2).getFilterValue1();
		String projectionDescription = webSearchCriteriaList.get(3).getFilterValue1();
		Object[] params = new Object[webSearchCriteriaList.size()];
		// GtnFrameworkDataType[] dataType = new
		// GtnFrameworkDataType[webSearchCriteriaList.size()];
		// for (int i = 0; i < webSearchCriteriaList.size(); i++) {
		// if (webSearchCriteriaList.get(i).getFilterValue1() != null) {
		//// params[i] =
		// webSearchCriteriaList.get(i).getFilterValue1().replaceAll("\\*", "%");
		// dataType[i] = GtnFrameworkDataType.STRING;
		//
		// }
		// }
		stringQuery.append(queryMap.get(0));
		stringQuery.append(queryMap.get(2));
		params[0] = projectionName.replaceAll("\\*", "%");
		params[1] = projectionDescription.replaceAll("\\*", "%");
		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		String finalQuery = stringQuery.toString();

		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(finalQuery);
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		RestTemplate restTemplate1 = new RestTemplate();
		logger.info("calling query engine via service registry");
		GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(
				getWebServiceEndpointBasedOnModule(
						"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
				gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
		List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
		// int count = pagedTableSearchCount(webSearchCriteriaList);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		dataTable.addData(resultList);
		searchResponse.setResultSet(dataTable);
		// searchResponse.setCount(count);
		gtnUIFrameworkWebserviceResponse.setGtnSerachResponse(searchResponse);
		return gtnUIFrameworkWebserviceResponse;
	}

	public int pagedTableSearchCount(List<GtnWebServiceSearchCriteria> webSearchCriteriaList) {
		String query = gtnSearchSqlService.getQuery("projectionSearchCount");
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(query);
		queryExecutorBean.setQueryType("COUNTWITHPARAMS");
		String projectionName = webSearchCriteriaList.get(0).getFilterValue1();
		String projectionDescription = webSearchCriteriaList.get(1).getFilterValue1();
		Object[] params = new Object[2];
		params[0] = projectionName.replaceAll("\\*", "%");
		params[1] = projectionDescription.replaceAll("\\*", "%");
		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		RestTemplate restTemplate1 = new RestTemplate();
		logger.info("calling query engine via service registry");
		GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(
				getWebServiceEndpointBasedOnModule(
						"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
				gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
		return response1.getQueryResponseBean().getResultInteger();
	}

	public static LocalDate convertStringDateToLocalDate(String dateInput) {
		Pattern patternOne = Pattern.compile("\\bQ..[0-9]{4}\\b");
		LocalDate localDate = null;

		if (patternOne.matcher(dateInput).find()) {
			int[] arr = { 0, 1, 4, 7, 10 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(dateInput.charAt(1))] + "/" + dateInput.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		}
		return localDate;
	}
}

