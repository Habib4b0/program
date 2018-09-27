package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;


import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class CustomViewSearch extends GtnCommonWebServiceImplClass implements SearchInterface {

    public CustomViewSearch() {
        super(CustomViewSearch.class);
    }

    @Override
    public GtnUIFrameworkWebserviceResponse getSearch(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,
            String query) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnFrameworkForecastInputBean inputBean = gtnUiFrameworkWebservicerequest.getGtnGeneralSearchRequest()
                .getInputBean();
        Object[] params = {inputBean.getCustomerRelationSid(), inputBean.getProductRelationSid(),
            inputBean.getCustomerRelationVersionNo(), inputBean.getProductRelationVersionNo()};
        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
            GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER};
        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setQueryType("SELECTWITHPARAMS");
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setParams(params);
        queryExecutorBean.setDataType(dataType);
        GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
        gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
        GtnQueryEngineWebServiceResponse queryResponse = callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
        List<Object[]> resultList = queryResponse.getQueryResponseBean().getResultList();
        GtnUIFrameworkWebserviceComboBoxResponse comboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
        comboxResponse.setComboBoxList(resultList);
        response.setGtnUIFrameworkWebserviceComboBoxResponse(comboxResponse);
        return response;
    }


    @Override
    public void initCallOnFailure() {
    //default method
    }
    
    @Override
    public void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
        // Default Method
    }

    @Override
    public GtnUIFrameworkWebserviceResponse getSearchResults(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest, String query, GtnSearchwebServiceSqlService gtnSearchSqlService) {
       return null;
    }

}
