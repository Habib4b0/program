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
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnWsAdjustmentDetailsDeductionsLoadService;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnWsAdjustmentDetailsSaveViewService;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnWsAdjustmentTableLoadService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.service.GtnWsTransactionReprocessIOService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER)
public class GtnWsTransactionAdjustmentDetailsController {

    @Autowired
    private GtnWsAdjustmentDetailsDeductionsLoadService gtnFrameworkDeductionsLoadService;

    @Autowired
    private GtnWsAdjustmentTableLoadService gtnWsAdjustmentTableLoadService;

    @Autowired
    private GtnWsAdjustmentDetailsSaveViewService gtnWsAdjustmentDetailsSaveViewService;

    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    @Autowired
    private GtnWsTransactionReprocessIOService gtnWsTransactionReprocessIOService;

    public GtnWsTransactionAdjustmentDetailsController() {
        super();
    }

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_SERVICE, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        String deductionLevel = gtnWsRequest.getGtnWsAdjusmentDetailsRequest().getDeductionLevelCaption();
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
        GtnSerachResponse searchResponse = new GtnSerachResponse();
        if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
            searchResponse.setCount(0);
            searchResponse.setResultSet(null);
        } else {
            String query = gtnWsAdjustmentTableLoadService.loadResults(gtnWsRequest, isCount);
            GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
            if (isCount) {
                List<Object> list = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query);
                searchResponse.setCount(list != null ? (Integer) list.get(0) : 0);
            } else {
                List<Object[]> list = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
                dataTable.addData(list);
                searchResponse.setResultSet(dataTable);
            }
        }
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        response.setGtnSerachResponse(searchResponse);
        return response;
    }

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_SAVE_VIEW_MASTER_SERVICE, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse updateSaveView(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        int armViewMasterSid = gtnWsAdjustmentDetailsSaveViewService.saveCustViewMaster(gtnWsRequest);
        String query = gtnWsAdjustmentDetailsSaveViewService.saveCustViewDetails(gtnWsRequest, armViewMasterSid);
        gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);

        return new GtnUIFrameworkWebserviceResponse();
    }

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_UPDATE_REPROCESS_SERVICE, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse updateReprocessDetails(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        String query = gtnWsAdjustmentDetailsSaveViewService.updateReprocessDetails(gtnWsRequest);
        gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        gtnWsTransactionReprocessIOService.runJobForReprocess("STADJUSTMENTRESERVEDETAIL_INTERFACE");
        return response;
    }
}
