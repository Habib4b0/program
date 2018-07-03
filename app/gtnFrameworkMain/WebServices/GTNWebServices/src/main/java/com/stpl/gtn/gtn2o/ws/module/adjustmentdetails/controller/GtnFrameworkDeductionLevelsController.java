/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service.GtnFrameworkDeductionsLoadService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
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
}
