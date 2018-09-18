/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ws.search.callqueryengine.CallQueryEngine;
import com.stpl.gtn.gtn2o.ws.search.implementation.ComboBoxSearch;
import com.stpl.gtn.gtn2o.ws.search.implementation.CustomerAndProductGroup;
import com.stpl.gtn.gtn2o.ws.search.implementation.PrivatePublic;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import java.util.ArrayList;

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
    private Map<String, String> queryMap = null;

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

    public Map<String,String> getQueryMap() {
        if (queryMap == null) {
            queryMap = new HashMap<>();
            queryMap.put("Commercial Forecasting_projectionName", " AND PM.projection_Name like ? ");
            queryMap.put("Commercial Forecasting_projectionDescription", " AND PM.projection_description like ? ");
            queryMap.put("Commercial Forecasting_company", " AND PM.COMPANY_MASTER_SID like ? ");
            queryMap.put("Commercial Forecasting_businessUnit", " AND PM.BUSINESS_UNIT like ? ");
            queryMap.put("forecastLandingScreen_customerHierarchy", " AND HDC.HIERARCHY_NAME like ? ");
            queryMap.put("Commercial Forecasting_prodhierarchyName", " AND HDP.HIERARCHY_NAME like ? ");
            queryMap.put("Commercial Forecasting_customerGroup", "CG.COMPANY_GROUP_NAME like ? ");
            queryMap.put("Commercial Forecasting_productGroup"," IG.ITEM_GROUP_NAME like ? ");
        }
        return queryMap;
    }

    public GtnUIFrameworkWebserviceResponse pagedTableSearch(
            GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        int count = 0;
        String query = gtnSearchSqlService.getQuery("projectionSearch");
        List<GtnWebServiceSearchCriteria> webSearchCriteriaList = gtnUiFrameworkWebservicerequest
                .getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append(query);
        List<Object> params = new ArrayList();
        List<GtnFrameworkDataType> data = new ArrayList();
        Map<String,String> map=getQueryMap();
        for (int i = 0; i < webSearchCriteriaList.size(); i++) {
            if (webSearchCriteriaList.get(i).getFilterValue1() != null
                    && !webSearchCriteriaList.get(i).getFilterValue1().equals("0")) {
                params.add(webSearchCriteriaList.get(i).getFilterValue1().replaceAll("\\*", "%"));
                data.add(GtnFrameworkDataType.STRING);
                stringQuery.append(map.get(webSearchCriteriaList.get(i).getFieldId()));
                count++;
            }
        }
        GtnFrameworkDataType[] dataType = new GtnFrameworkDataType[count];
        Object[] param = new Object[count];
        param = params.toArray(param);
        dataType = data.toArray(dataType);
        String finalQuery = stringQuery.toString();
        CallQueryEngine callQueryEngine = new CallQueryEngine();
        GtnQueryEngineWebServiceResponse response1 = callQueryEngine.
                commonCall(finalQuery, "SELECTWITHPARAMS", param, dataType);
        List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
        int countQuery = pagedTableSearchCount(webSearchCriteriaList);
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        dataTable.addData(resultList);
        searchResponse.setResultSet(dataTable);
        searchResponse.setCount(countQuery);
        gtnUIFrameworkWebserviceResponse.setGtnSerachResponse(searchResponse);
        return gtnUIFrameworkWebserviceResponse;
    }

    public int pagedTableSearchCount(List<GtnWebServiceSearchCriteria> webSearchCriteriaListCount) {
        int countQuery = 0;
        String queryCount = gtnSearchSqlService.getQuery("projectionSearchCount");
        StringBuilder stringQueryCount = new StringBuilder();
        stringQueryCount.append(queryCount);
        List<Object> paramsCount = new ArrayList();
        List<GtnFrameworkDataType> dataCount = new ArrayList();
        for (int i = 0; i < webSearchCriteriaListCount.size(); i++) {
            if (webSearchCriteriaListCount.get(i).getFilterValue1() != null
                    && !webSearchCriteriaListCount.get(i).getFilterValue1().equals("0")) {
                paramsCount.add(webSearchCriteriaListCount.get(i).getFilterValue1().replaceAll("\\*", "%"));
                dataCount.add(GtnFrameworkDataType.STRING);
                stringQueryCount.append(queryMap.get(webSearchCriteriaListCount.get(i).getFieldId()));
                countQuery++;
            }
        }
        GtnFrameworkDataType[] dataTypeCount = new GtnFrameworkDataType[countQuery];
        Object[] paramCount = new Object[countQuery];
        paramCount = paramsCount.toArray(paramCount);
        dataTypeCount = dataCount.toArray(dataTypeCount);
        String finalQueryCount = stringQueryCount.toString();
        CallQueryEngine callQueryEngine = new CallQueryEngine();
        GtnQueryEngineWebServiceResponse response1 = callQueryEngine.
                commonCall(finalQueryCount, "COUNTWITHPARAMS", paramCount, dataTypeCount);
        logger.info("calling query engine via service registry");
        return response1.getQueryResponseBean().getResultInteger();
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

}
