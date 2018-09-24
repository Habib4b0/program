/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.implementation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
    public GtnUIFrameworkWebserviceResponse getSearch(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,String query) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        try
        {

        List<GtnWebServiceSearchCriteria> webSearchCriteriaList = gtnUiFrameworkWebservicerequest
                .getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
        String viewType = webSearchCriteriaList.get(1).getFilterValue1();
        String viewName = webSearchCriteriaList.get(0).getFilterValue1();
        
        
       
        String userId=gtnUiFrameworkWebservicerequest.getGtnWsGeneralRequest().getUserId();
        Object[] params = new Object[2];
        params[0] = viewType.replaceAll("\\*", "%");
        params[1] = viewName.replaceAll("\\*", "%");
       
       
        if (("private").equalsIgnoreCase(viewType)) {
                query += "AND FV.created_By = " + userId;
        }
        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING
            };
        logger.debug("Private and public view query" + query);
        GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
        queryExecutorBean.setSqlQuery(query);
        queryExecutorBean.setQueryType("SCALAR");
        queryExecutorBean.setParams(params);
        queryExecutorBean.setDataType(dataType);
		Map<String, GtnFrameworkDataType> inputMap = new LinkedHashMap<>();
		inputMap.put("VIEW_NAME", GtnFrameworkDataType.STRING);
		inputMap.put("VIEW_TYPE", GtnFrameworkDataType.STRING);
		inputMap.put("CREATED_BY", GtnFrameworkDataType.STRING);
		inputMap.put("CREATED_DATE", GtnFrameworkDataType.DATE);
		inputMap.put("MODIFIED_BY", GtnFrameworkDataType.STRING);
		inputMap.put("MODIFIED_DATE", GtnFrameworkDataType.DATE);
		inputMap.put("VIEW_DATA", GtnFrameworkDataType.STRING);
		inputMap.put("VIEW_ID", GtnFrameworkDataType.INTEGER);
		queryExecutorBean.setInputMap(inputMap);
        GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
        gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
        logger.info("calling query engine via service registry");
        GtnQueryEngineWebServiceResponse response1 = callServiceRegistryRedirectForQueryEngine(gtnQueryEngineWebServiceRequest);
        List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
       
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        dataTable.addData(method(resultList));
        searchResponse.setResultSet(dataTable);
        response.setGtnSerachResponse(searchResponse);
        }
        catch(Exception e)
        {
            logger.error("Exception in loading private and public views"+e);
        }
        return response;
    }

	private  List<Object[]> method(List<Object[]> resultList) throws JsonParseException, JsonMappingException, IOException {
		 List<Object[]> list = new ArrayList<>();
		 GtnFrameworkForecastDataSelectionBean bean = new GtnFrameworkForecastDataSelectionBean();
		 Object[] ob = new Object[18];
		 for(int i=0;i<resultList.size();i++)
		 {
			 Object[] obj = resultList.get(i);
			 bean=convertJsonToObject(GtnFrameworkForecastDataSelectionBean.class,
						((String) obj[6]).replaceAll("\\\\", "'"));
			 ob[0]=obj[0];
			 ob[1]=bean.getProjectionDescription();
			 ob[2]=bean.getFromPeriodForecastName();
			 ob[3]=bean.getToPeriodForecastName();
			 ob[4]=bean.getCustomerHierarchyRecordBean().getPropertyValueByIndex(0);
			 ob[5]=bean.getCustomerHierarchyLevel();
			 ob[6]="";
			 ob[7]=bean.getCompanyName();
			 ob[8]="Contracted";
			 ob[9]=bean.getProductHierarchyRecordBean().getPropertyValueByIndex(0);
			 ob[10]=bean.getProductHierarchyLevel();
			 ob[11]="";
			 ob[12]=obj[3];
			 ob[13]=obj[5];
			 ob[14]=obj[4];
			 ob[15]=bean.getBusinessUnitName();
			 ob[16]=obj[7];
			 ob[17]=bean;
			 list.add(ob);
		 }
		 return list;
	}
	private GtnFrameworkForecastDataSelectionBean convertJsonToObject(Class<GtnFrameworkForecastDataSelectionBean> dataSelectionBean,
			String viewData) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(viewData, dataSelectionBean);
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
