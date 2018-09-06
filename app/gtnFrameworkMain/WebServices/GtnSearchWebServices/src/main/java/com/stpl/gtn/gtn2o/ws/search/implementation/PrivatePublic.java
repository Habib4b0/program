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
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
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
public class PrivatePublic extends GtnCommonWebServiceImplClass implements SearchInterface {


    public PrivatePublic()
    {
        super();
        initializeLogger();
    }
    @PostConstruct
    public void initializeLogger() {
        super.logInformation(PrivatePublic.class);
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
        String query = gtnGeneralSearchBean.getQuery();
        List<String> list = gtnGeneralSearchBean.getList();
        String viewType = list.get(1);
        String userId = list.get(2);
        String viewName = list.get(0);
        String forecastType = list.get(3);
        Object[] params = new Object[3];
        params[0] = viewType;
        params[1] = viewName;
        params[2] = forecastType;
        if (("private").equalsIgnoreCase(viewType)) {
                query += "AND FVM.created_By = " + userId;
        }
        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
            GtnFrameworkDataType.STRING};
        logger.debug("Private and public view query" + query);
        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setQueryType("SELECTWITHPARAMS");
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
        response.setGtnGeneralSearchWsList(resultList);
        }
        catch(Exception e)
        {
            logger.error("Exception in loading private and public views"+e);
        }
        return response;
    }

}
