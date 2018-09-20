package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.service.GtnForecastJsonService;

public class SaveViewExecution  extends GtnCommonWebServiceImplClass implements SearchInterface {

@Autowired
private GtnForecastJsonService gtnForecastJsonService;
    public SaveViewExecution()
    {
        super(SaveViewExecution.class);
     }
    
    @Override
    public GtnUIFrameworkWebserviceRequest registerWs() {
        return null;
    }

    @Override
    public GtnUIFrameworkWebserviceResponse getSearch(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,String query) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        try
        {

        String viewType = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getViewType();
        String viewName =gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getViewName();
        String userId=gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean().getUserId();
        GtnFrameworkForecastDataSelectionBean dataSelectionBean = gtnUiFrameworkWebservicerequest.getGtnWsForecastNewArchRequest().getDataSelectionBean();
		String viewData = gtnForecastJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'", "\\\\");

        Object[] params = new Object[5];
        params[0] = viewType.replaceAll("\\*", "%");
        params[1] = viewName.replaceAll("\\*", "%");
        params[2] = userId;
        params[3] = userId;
        params[4] = "'"+viewData+"'";

        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
            GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING};
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
           GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(
                getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
                gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
        List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        dataTable.addData(resultList);
        searchResponse.setResultSet(dataTable);
        response.setGtnSerachResponse(searchResponse);
        }
        catch(Exception e)
        {
            logger.error("Exception in save view"+e);
        }
        return response;
    }

}




