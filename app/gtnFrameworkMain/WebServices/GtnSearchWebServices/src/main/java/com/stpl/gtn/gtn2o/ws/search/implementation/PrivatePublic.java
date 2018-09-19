/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.implementation;

import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.callqueryengine.CallQueryEngine;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import java.util.List;

/**
 *
 * @author anandh.karuppusamy
 */
public class PrivatePublic extends GtnCommonWebServiceImplClass implements SearchInterface {


    public PrivatePublic()
    {
         super(PrivatePublic.class);
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
        List<GtnWebServiceSearchCriteria> webSearchCriteriaList = gtnUiFrameworkWebservicerequest
                .getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
        String viewType = webSearchCriteriaList.get(1).getFilterValue1();
        String viewName = webSearchCriteriaList.get(0).getFilterValue1();
        String forecastType = webSearchCriteriaList.get(2).getFilterValue1();
        String userId=gtnUiFrameworkWebservicerequest.getGtnWsGeneralRequest().getUserId();
        Object[] params = new Object[3];
        params[0] = viewType.replaceAll("\\*", "%");
        params[1] = viewName.replaceAll("\\*", "%");
        params[2] = forecastType.replaceAll("\\*", "%");
        if (("private").equalsIgnoreCase(viewType)) {
                query += "AND FVM.created_By = " + userId;
        }
        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
            GtnFrameworkDataType.STRING};
        logger.debug("Private and public view query" + query);
        CallQueryEngine callQueryEngine =new CallQueryEngine();
        GtnQueryEngineWebServiceResponse response1=callQueryEngine.commonCallWithParams(query, "SELECTWITHPARAMS", params, dataType);
        List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        dataTable.addData(resultList);
        searchResponse.setResultSet(dataTable);
        response.setGtnSerachResponse(searchResponse);
        }
        catch(Exception e)
        {
            logger.error("Exception in loading private and public views"+e);
        }
        return response;
    }

}
