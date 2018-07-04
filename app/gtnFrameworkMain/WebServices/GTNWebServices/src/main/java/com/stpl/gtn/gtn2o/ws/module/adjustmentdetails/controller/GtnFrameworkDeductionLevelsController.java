/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnFrameworkDeductionsLoadService;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnWsAdjustmentTableLoadService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sathya.Seelan
 */
@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER)
public class GtnFrameworkDeductionLevelsController {

    @Autowired
    GtnFrameworkDeductionsLoadService gtnFrameworkDeductionsLoadService;

    @Autowired
    GtnWsAdjustmentTableLoadService gtnWsAdjustmentTableLoadService;

    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_SERVICE, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        String deductionLevel = gtnWsRequest.getGtnWsAdjusmentDetailsRequest().getDeductionLevel();
        String query = gtnFrameworkDeductionsLoadService.getDeductionValue(deductionLevel);
        List<Object[]> list = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
        List<String> itemCodeList = new ArrayList<>();
        List<String> itemValueList = new ArrayList<>();
        for (Object[] object : list) {
            itemCodeList.add(String.valueOf(object[0]));
            itemValueList.add(String.valueOf(object[1]));
        }
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        response.setItemCodeList(itemCodeList);
        response.setItemValueList(itemValueList);
        return response;
    }

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadSearchResults(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        boolean isCount = gtnWsRequest.getGtnWsSearchRequest().isCount();
        String reserveOrGtn = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();
        String query = gtnWsAdjustmentTableLoadService.loadResults(gtnWsRequest, isCount);
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        if (isCount) {
            List<Object> list = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query);
            searchResponse.setCount(list != null ? (Integer) list.get(0) : 0);
        } else {
            List<Object[]> list = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
            dataTable.addData(list);
            searchResponse.setResultSet(dataTable);
        }

//        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
//		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
//		try {
//
//			generalWSResponse.setSucess(true);
//			String queryName = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
//			boolean isCount = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount();
//
//			GtnWsSearchQueryConfigLoaderType searchQueryconfigLoaderType = gtnUIFrameworkWebserviceRequest
//					.getGtnWsSearchRequest().getSearchConfigLodaderType();
//			GtnWsSearchQueryConfigLoader searchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) searchQueryconfigLoaderType
//					.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());
//			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = searchQueryConfigLoader.getSearchQueryConfigMap()
//					.get(queryName);
//
//			GtnWsSearchQueryGenerationLogic searchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
//					gtnWebServiceSearchQueryConfig, gtnUIFrameworkWebserviceRequest);
//
//			String generatedQuery = searchQueryGenerationLogic.generateSearchQuery();
//
//			Connection connection = gtnWebServiceAllListConfig.getSysSessionFactory().getSessionFactoryOptions().
//                                getServiceRegistry().getService(ConnectionProvider.class).getConnection();
//                        String generatedQueryReplaced = generatedQuery.replace("@SYS", connection.getCatalog());
//			List<Object[]> resultList = executeQuery(generatedQueryReplaced); 
//
//			if (!isCount && gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap() != null) {
//				getCustomizedSearchFormFromObject(resultList, gtnWebServiceSearchQueryConfig,
//						gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchColumnNameList());
//
//			}
//			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
//			if (isCount) {
//
//				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
//
//			} else {
//				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
//				gtnUIFrameworkDataTable.addData(resultList);
//				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
//			}
//			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
//        
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        response.setGtnSerachResponse(searchResponse);
        return response;
    }
}
