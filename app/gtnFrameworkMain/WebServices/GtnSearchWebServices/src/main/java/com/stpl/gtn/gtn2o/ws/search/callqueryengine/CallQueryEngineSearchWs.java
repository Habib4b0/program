/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.callqueryengine;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

/**
 *
 * @author anandh.karuppusamy
 */
public class CallQueryEngineSearchWs extends GtnCommonWebServiceImplClass {

    public CallQueryEngineSearchWs() {
        super(CallQueryEngineSearchWs.class);
    }

    public GtnQueryEngineWebServiceResponse commonCallWithParams(String query, String type, Object[] params,
            GtnFrameworkDataType[] dataType) {
        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setQueryType(type);
        queryExecutorBean.setParams(params);
        queryExecutorBean.setDataType(dataType);
        GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
        gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
        addSecurityToken(gtnQueryEngineWebServiceRequest);
        logger.info("calling query engine via service registry");
        return callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);  
    }

    @Override
    public GtnUIFrameworkWebserviceRequest registerWs() {
        return null;
    }
    
     @Override
    public void initCallOnFailure() {
        // Default Method
    }

    @Override
    public void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
        // Default Method
    }

}
