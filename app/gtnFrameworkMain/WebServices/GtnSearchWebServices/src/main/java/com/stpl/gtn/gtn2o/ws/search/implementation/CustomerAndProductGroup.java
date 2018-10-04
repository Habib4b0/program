package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;

import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.callqueryengine.CallQueryEngineSearchWs;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerAndProductGroup extends GtnCommonWebServiceImplClass implements SearchInterface {

    private Map<String, String> queryMap = new HashMap();
    private static final String PRODUCT = "Product";

    public CustomerAndProductGroup() {
        super(CustomerAndProductGroup.class);
    }

    @Override
    public GtnUIFrameworkWebserviceRequest registerWs() {
        return null;
    }

    @Override
    public GtnUIFrameworkWebserviceResponse getSearchResults(
            GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest, String query, GtnSearchwebServiceSqlService gtnSearchSqlService) {
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        try {
            GtnSerachResponse searchResponse = new GtnSerachResponse();
            List<GtnWebServiceSearchCriteria> list = gtnUiFrameworkWebservicerequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
            StringBuilder stringQuery = new StringBuilder();
            String cpgQuery;
            getQueryMap(gtnSearchSqlService, list);
            int count = 0;
            List<Object> param = new ArrayList();
            List<GtnFrameworkDataType> data = new ArrayList();
            boolean level = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFilterValue1() != null
                        && !list.get(i).getFilterValue1().equals("0") && !list.get(i).getFilterValue1().equals("*")) {
                    GtnWebServiceSearchCriteria searchCriteria = list.get(i);
                    level = getlevel(searchCriteria, level);
                    if (searchCriteria.getFieldId().contains("No") || searchCriteria.getFieldId().contains("Name")) {
                        param.add(list.get(i).getFilterValue1().replaceAll("\\*", "%"));
                        data.add(GtnFrameworkDataType.STRING);
                        if (!level) {
                            stringQuery.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
                        } else {
                            stringQuery.append(GtnFrameworkWebserviceConstant.WHERE);
                        }
                        stringQuery.append(queryMap.get(list.get(i).getFieldId()));
                        level = false;
                        count++;
                    }
                }
            }
            Object[] params = new Object[count];
            params = param.toArray(params);
            GtnFrameworkDataType[] dataType = new GtnFrameworkDataType[count];
            cpgQuery = query.replace("@Condition", stringQuery);
            logger.debug("Customer And Product Group query" + (cpgQuery));
            CallQueryEngineSearchWs callQueryEngine = new CallQueryEngineSearchWs();
            GtnQueryEngineWebServiceResponse responseForCustAndProd = callQueryEngine.commonCallWithParams(cpgQuery, "SELECTWITHPARAMS", params, data.toArray(dataType));
            List<Object[]> resultList = responseForCustAndProd.getQueryResponseBean().getResultList();
            GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
            dataTable.addData(resultList);
            searchResponse.setResultSet(dataTable);
            response.setGtnSerachResponse(searchResponse);
        } catch (Exception e) {
            logger.error("Exception in loading customer and product group" + e);
        }
        return response;
    }
    
    private boolean getlevel(GtnWebServiceSearchCriteria searchCriteria, boolean level) {
        if (searchCriteria.getFieldId().toString().contains(PRODUCT)) {
            level = false;
        }
        return level;
    }

    private void getQueryMap(GtnSearchwebServiceSqlService gtnSearchSqlService, List<GtnWebServiceSearchCriteria> list) {
        String[] paramsArray = gtnSearchSqlService.getQuery("CustomerGroupParameters").split(",");
        if(PRODUCT.contains(list.get(1).getFieldId())){
        queryMap.put(list.get(0).getFieldId(), paramsArray[2]);
        queryMap.put(list.get(1).getFieldId(), paramsArray[3]);
        }else{
        queryMap.put(list.get(0).getFieldId(), paramsArray[0]);
        queryMap.put(list.get(1).getFieldId(), paramsArray[1]);
        }
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
