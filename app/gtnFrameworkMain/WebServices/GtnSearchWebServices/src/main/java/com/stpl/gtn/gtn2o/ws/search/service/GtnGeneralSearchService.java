
package com.stpl.gtn.gtn2o.ws.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkServiceBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.callqueryengine.CallQueryEngineSearchWs;
import com.stpl.gtn.gtn2o.ws.search.implementation.ComboBoxSearch;
import com.stpl.gtn.gtn2o.ws.search.implementation.CustomViewSearch;
import com.stpl.gtn.gtn2o.ws.search.implementation.CustomerAndProductGroup;
import com.stpl.gtn.gtn2o.ws.search.implementation.PrivatePublic;
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
    private GtnSearchwebServiceSqlService gtnSearchSqlService;

    @Autowired
    private GtnForecastJsonService gtnForecastJsonService;

    private static final Map<String, SearchInterface> keyMap = new HashMap<>();
    private Map<String, String> queryMap = new HashMap<>();

    public void init() {
        try {
            logger.info("Entering into init method of searchWebservice");
            GtnUIFrameworkWebserviceRequest request = registerWs();
            callServiceRegistry(request);
            logger.info("search webservices registered");
        } catch (Exception e) {
            logger.error("Exception in searchWebservice" + e.getMessage());
        }

    }

    static {
        final List<String> keyList = GtnFrameworkServiceBean.KEY_MAP_LIST;
        keyMap.put(keyList.get(0), new PrivatePublic());
        keyMap.put(keyList.get(1), new ComboBoxSearch());
        keyMap.put(keyList.get(2), new ComboBoxSearch());
        keyMap.put(keyList.get(3), new ComboBoxSearch());
        keyMap.put(keyList.get(4), new ComboBoxSearch());
        keyMap.put(keyList.get(5), new CustomerAndProductGroup());
        keyMap.put(keyList.get(6), new CustomerAndProductGroup());
        keyMap.put(keyList.get(7), new CustomViewSearch());
        keyMap.put(keyList.get(8), new CustomViewSearch());
    }

    public GtnUIFrameworkWebserviceResponse commonMethod(
            GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
        String key = gtnUiFrameworkWebservicerequest.getGtnWsSearchRequest().getSearchQueryName();
        String query = gtnSearchSqlService.getQuery(key);
        SearchInterface searchInterface = keyMap.get(key);
        GtnUIFrameworkWebserviceResponse response;
        response = searchInterface.getSearchResults(gtnUiFrameworkWebservicerequest, query, gtnSearchSqlService);
        return response;
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
        List<Object> params = new ArrayList<>();
        List<GtnFrameworkDataType> data = new ArrayList<>();
        Map<String, String> map = getQueryMap();
        for (int i = 0; i < webSearchCriteriaList.size(); i++) {
            if (webSearchCriteriaList.get(i).getFilterValue1() != null
                    && !webSearchCriteriaList.get(i).getFilterValue1().equals("0") && !webSearchCriteriaList.get(i).getFilterValue1().equals("*")) {
                params.add(webSearchCriteriaList.get(i).getFilterValue1().replaceAll("\\*", "%"));
                data.add(GtnFrameworkDataType.STRING);
                stringQuery.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
                stringQuery.append(map.get(webSearchCriteriaList.get(i).getFieldId()));
                count++;
            }
        }
        GtnFrameworkDataType[] dataType = new GtnFrameworkDataType[count];
        Object[] param = new Object[count];
        param = params.toArray(param);
        dataType = data.toArray(dataType);
        String finalQuery = stringQuery.toString();
        CallQueryEngineSearchWs callQueryEngine = new CallQueryEngineSearchWs();
        GtnQueryEngineWebServiceResponse response1 = callQueryEngine.commonCallWithParams(finalQuery, "SELECTWITHPARAMS", param, dataType);
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
        List<Object> paramsCount = new ArrayList<>();
        List<GtnFrameworkDataType> dataCount = new ArrayList<>();
        for (int i = 0; i < webSearchCriteriaListCount.size(); i++) {
            if (webSearchCriteriaListCount.get(i).getFilterValue1() != null
                    && !webSearchCriteriaListCount.get(i).getFilterValue1().equals("0")) {
                paramsCount.add(webSearchCriteriaListCount.get(i).getFilterValue1().replaceAll("\\*", "%"));
                dataCount.add(GtnFrameworkDataType.STRING);
                stringQueryCount.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
                stringQueryCount.append(queryMap.get(webSearchCriteriaListCount.get(i).getFieldId()));
                countQuery++;
            }
        }
        GtnFrameworkDataType[] dataTypeCount = new GtnFrameworkDataType[countQuery];
        Object[] paramCount = new Object[countQuery];
        paramCount = paramsCount.toArray(paramCount);
        dataTypeCount = dataCount.toArray(dataTypeCount);
        String finalQueryCount = stringQueryCount.toString();
        CallQueryEngineSearchWs callQueryEngine = new CallQueryEngineSearchWs();
        GtnQueryEngineWebServiceResponse response1 = callQueryEngine.commonCallWithParams(finalQueryCount, "COUNTWITHPARAMS", paramCount, dataTypeCount);
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
            Integer userId = Integer.valueOf(gtnUiFrameworkWebservicerequest.getGtnWsGeneralRequest().getUserId());

            GtnFrameworkForecastDataSelectionBean dataSelectionBean = gtnUiFrameworkWebservicerequest
                    .getGtnWsForecastNewArchRequest().getDataSelectionBean();
            String viewData = gtnForecastJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'",
                    "\\\\");

            Object[] params = new Object[5];
            params[0] = viewName.replaceAll("\\*", "%");
            params[1] = viewType.replaceAll("\\*", "%");
            params[2] = userId;
            params[3] = userId;
            params[4] = viewData;

            GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
                GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING};
            String query = gtnSearchSqlService.getQuery("saveview");
            logger.debug("query for save view" + query);
            GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
            queryExecutorBean.setSqlQuery(query);
            queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
            queryExecutorBean.setParams(params);
            queryExecutorBean.setDataType(dataType);
            GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
            gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
            logger.info("calling query engine via service registry");
            GtnQueryEngineWebServiceResponse response1 = callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
            GtnWsGeneralRequest generalRequest = gtnUiFrameworkWebservicerequest.getGtnWsGeneralRequest();
            
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
    
    public GtnUIFrameworkWebserviceResponse updateView(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        try {

            String viewType = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean()
                    .getViewType();
            String viewName = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean()
                    .getViewName();
            Integer userId = Integer.valueOf(gtnUiFrameworkWebservicerequest.getGtnWsGeneralRequest().getUserId());

            GtnFrameworkForecastDataSelectionBean dataSelectionBean = gtnUiFrameworkWebservicerequest
                    .getGtnWsForecastNewArchRequest().getDataSelectionBean();
            String viewData = gtnForecastJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'",
                    "\\\\");

            Object[] params = new Object[4];
            params[0] =  viewType.replaceAll("\\*", "%");
            params[1] = userId;
            params[2] = viewData;
            params[3] =viewName.replaceAll("\\*", "%");
            
            GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
                GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING};
            String query = gtnSearchSqlService.getQuery("updateView");
            logger.debug("query for update view" + query);
            GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
            queryExecutorBean.setSqlQuery(query);
            queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
            queryExecutorBean.setParams(params);
            queryExecutorBean.setDataType(dataType);
            GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
            gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
            logger.info("calling query engine via service registry");
            GtnQueryEngineWebServiceResponse response1 = callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
            List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
            GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
            GtnSerachResponse searchResponse = new GtnSerachResponse();
            dataTable.addData(resultList);
            searchResponse.setResultSet(dataTable);
            response.setGtnSerachResponse(searchResponse);
        } catch (Exception e) {
            logger.error("Exception in update view" + e);
        }
        return response;
    }

    public GtnUIFrameworkWebserviceResponse deleteView(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebservicerequest) {

        logger.info("inside delete view method");
        String deleteViewQuery = "deleteView";
        GtnFrameworkForecastDataSelectionBean bean = gtnUIFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean();
        GtnQueryEngineWebServiceResponse response = callQueryEngineforDeleteView(createQuery(readProperty(deleteViewQuery), bean));
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        int count = response.getQueryResponseBean().getResultInteger();
        GtnFrameworkForecastDataSelectionBean bean1 = new GtnFrameworkForecastDataSelectionBean();
        bean1.setResultCount(count);
        gtnUIFrameworkWebserviceResponse.setGtnFrameworkForecastDataSelectionBean(bean1);
        return gtnUIFrameworkWebserviceResponse;
    }

    private GtnQueryEngineWebServiceResponse callQueryEngineforDeleteView(GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest) {
        return callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
    }

    private GtnQueryEngineWebServiceRequest createQuery(String query, GtnFrameworkForecastDataSelectionBean bean) {

        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
        Object[] params = {bean.getViewId(), bean.getUserId()};
        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING};
        queryExecutorBean.setParams(params);
        queryExecutorBean.setDataType(dataType);
        GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
        gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
        return gtnQueryEngineWebServiceRequest;
    }

    private String readProperty(String deleteViewQuery) {
        String query = gtnSearchSqlService.getQuery(deleteViewQuery);
        logger.info("inside delete view method query------->" + query);
        return query;
    }

    @Override
    public void initCallOnFailure() {
        init();
    }

    @Override
    public void getEndPointServiceURL(GtnWsServiceRegistryBean gtnServiceRegistryBean) {
        gtnServiceRegistryBean.setWebserviceEndPointUrl(
                GtnFrameworkPropertyManager.getProperty("gtn.webservices.generalSearch.endPointUrl"));
        gtnServiceRegistryBean.setRegisteredWebContext(
                GtnFrameworkPropertyManager.getProperty("gtn.webservices.generalSearch.endPointServiceName"));
    }
    
    public int checkViewRecordCount(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest)
			throws GtnFrameworkGeneralException {
		int recordCount = 0;
		String query = gtnSearchSqlService.getQuery("getViewCount");
		String viewName = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getViewName();
		String viewType = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getViewType();
		String userId =  gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getUserId();
		Object[] params = {viewName , viewType , userId };

		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		 GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
         queryExecutorBean.setSqlQuery(query);
         queryExecutorBean.setQueryType("COUNTWITHPARAMS");
         queryExecutorBean.setParams(params);
         queryExecutorBean.setDataType(dataType);
         GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
         gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
         logger.info("calling query engine via service registry");
         GtnQueryEngineWebServiceResponse response1 = callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
         recordCount = response1.getQueryResponseBean().getResultInteger();
         logger.info("Check view record count------------>>>>>>>" + recordCount);
		return recordCount;
	}


    public Map<String, String> getQueryMap() {
        List<String> paramList = GtnFrameworkServiceBean.PROJECTION_SEARCH_VALUES;
        String[] paramsArray = gtnSearchSqlService.getQuery("projectionSearchParams").split(",");
        queryMap.put(paramList.get(0), paramsArray[0]);
        queryMap.put(paramList.get(1), paramsArray[1]);
        queryMap.put(paramList.get(2), paramsArray[2]);
        queryMap.put(paramList.get(3), paramsArray[3]);
        queryMap.put(paramList.get(4), paramsArray[4]);
        queryMap.put(paramList.get(5), paramsArray[5]);
        queryMap.put(paramList.get(6), paramsArray[6]);
        queryMap.put(paramList.get(7), paramsArray[7]);
        queryMap.put(paramList.get(8), paramsArray[8]);
        queryMap.put(paramList.get(9), paramsArray[9]);
        queryMap.put(paramList.get(10), paramsArray[10]);
        queryMap.put(paramList.get(11), paramsArray[11]);
        queryMap.put(paramList.get(12), paramsArray[12]);
        queryMap.put(paramList.get(13), paramsArray[13]);
        queryMap.put(paramList.get(14), paramsArray[14]);
        queryMap.put(paramList.get(15), paramsArray[15]);
        queryMap.put(paramList.get(16), paramsArray[16]);
        queryMap.put(paramList.get(17), paramsArray[17]);
        queryMap.put(paramList.get(18), paramsArray[18]);
        queryMap.put(paramList.get(19), paramsArray[19]);
        queryMap.put(paramList.get(20), paramsArray[20]);
        return queryMap;
    }

}
