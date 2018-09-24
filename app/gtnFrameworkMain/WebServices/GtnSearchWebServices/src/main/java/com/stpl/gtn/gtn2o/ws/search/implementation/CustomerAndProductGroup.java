package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;

import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.callqueryengine.CallQueryEngineSearchWs;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class CustomerAndProductGroup extends GtnCommonWebServiceImplClass implements SearchInterface{
	 public CustomerAndProductGroup()
	    {
	        super(CustomerAndProductGroup.class);
	    }
	   


	    @Override
	    public GtnUIFrameworkWebserviceRequest registerWs() {
	        return null;
	    }


		@Override
		public GtnUIFrameworkWebserviceResponse getSearch(
				GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest, String query) {
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
	        try
	        {
	    	GtnSerachResponse searchResponse = new GtnSerachResponse();
	        List<GtnWebServiceSearchCriteria> list = gtnUiFrameworkWebservicerequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
	     
	        Object[] params = new Object[2];
	        params[0] = "%";
	        params[1] = "%";

	        for(int i=0;i<list.size();i++){
	        	GtnWebServiceSearchCriteria searchCriteria = list.get(i);
	        	if(searchCriteria.getFieldId().contains("No")){
	        		params[0] = searchCriteria.getFilterValue1().replaceAll("\\*", "%");
	        	}
	        	if(searchCriteria.getFieldId().contains("Name")){
	        		params[1] = searchCriteria.getFilterValue1().replaceAll("\\*", "%");

	        	}
	        }
	        GtnFrameworkDataType[] dataType = {GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING};
	        logger.debug("Customer And Product Group query" + query);
                CallQueryEngineSearchWs callQueryEngine =new CallQueryEngineSearchWs();
                GtnQueryEngineWebServiceResponse responseForCustAndProd=callQueryEngine.commonCallWithParams(query, "SELECTWITHPARAMS", params, dataType);
	        List<Object[]> resultList = responseForCustAndProd.getQueryResponseBean().getResultList();
	        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
			dataTable.addData(resultList);
			searchResponse.setResultSet(dataTable);
			response.setGtnSerachResponse(searchResponse);
	        }
	        catch(Exception e)
	        {
	            logger.error("Exception in loading customer and product group"+e);
	        }
	        return response;
		}

    @Override
    public void initCallOnFailure() {
        return;
    }
    
    @Override
    public void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
        // Default Method
    }
	    
	    
}
