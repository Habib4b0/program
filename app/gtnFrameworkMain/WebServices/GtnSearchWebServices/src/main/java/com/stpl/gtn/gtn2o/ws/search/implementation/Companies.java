/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.implementation;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.generalsearch.GtnGeneralSearchBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author anandh.karuppusamy
 */
public class Companies extends GtnCommonWebServiceImplClass implements SearchInterface {
    public Companies()
    {
        super();
        initializeLogger();
    }

    @PostConstruct
    public void initializeLogger() {
        super.logInformation(Companies.class);
    }

    @Override
    public GtnUIFrameworkWebserviceRequest registerWs() {
        return null;
    }

    @Override
    public GtnUIFrameworkWebserviceResponse getSearch(GtnGeneralSearchBean gtnGeneralSearchBean) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        try
        {
        String query=gtnGeneralSearchBean.getQuery();
        logger.debug("Companies query" + query);
        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setQueryType("SELECT");
        GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
        gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
        RestTemplate restTemplate1 = new RestTemplate();
        logger.info("calling query engine via service registry");
        GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(
                getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
                gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
                List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
                response.setGtnGeneralSearchWsList(resultList);
        }
        catch(Exception e)
        {
            logger.error("Exception in loading companies"+e);
        }
        return response;
    }

}
