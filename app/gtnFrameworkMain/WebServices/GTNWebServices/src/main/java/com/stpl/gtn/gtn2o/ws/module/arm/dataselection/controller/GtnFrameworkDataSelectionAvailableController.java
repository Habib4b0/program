/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.arm.dataselection.controller;

import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service.GtnFrameworkARMLoadLevelMap;
import com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service.GtnFrameworkArmDataSelectionAvailableProducts;
import com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service.GtnFrameworkDataSelectionAvailableCustomers;
import com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service.GtnFrameworkInsertCcpArm;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.arm.GtnWsArmRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.arm.GtnWsARMResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELCTION_ARM_EDIT_SERVICE)
public class GtnFrameworkDataSelectionAvailableController {

    @Autowired
    GtnFrameworkDataSelectionAvailableCustomers customerLevelService;
   
    @Autowired
    GtnFrameworkArmDataSelectionAvailableProducts productLevelService;
    
    @Autowired
    GtnFrameworkARMLoadLevelMap levelValueMapQueryService;
    
    @Autowired
    GtnFrameworkInsertCcpArm ccpInsertService;

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_ARM_LOAD_CUSTOMER_LEVEL, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        GtnWsArmRequest armRequest = gtnWsRequest.getGtnWsArmRequest();
        GtnARMHierarchyInputBean inputBean = armRequest.getInputBean();
        String finalQuery = customerLevelService.getCustomerLevelQuery(inputBean);
        inputBean.setFramedQuery(finalQuery);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsARMResponse armResponse = new GtnWsARMResponse();
        armResponse.setInputBean(inputBean);
        response.setGtnWsARMResponse(armResponse);
        return response;
    }

    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_ARM_LOAD_LEVEL_VALUE_MAP, method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadLevelValueMapQuery(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
        GtnWsArmRequest forecastRequet = gtnWsRequest.getGtnWsArmRequest();
        GtnARMHierarchyInputBean inputBean = forecastRequet.getInputBean();
        String finalQuery = levelValueMapQueryService.getQueryForLevelValueMap(inputBean);
        inputBean.setFramedQuery(finalQuery);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsARMResponse forecastResponse = new GtnWsARMResponse();
        forecastResponse.setInputBean(inputBean);
        response.setGtnWsARMResponse(forecastResponse);
        return response;
    }
    
    @RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_ARM_LOAD_PRODUCT_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsArmRequest forecastRequet = gtnWsRequest.getGtnWsArmRequest();
		GtnARMHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = productLevelService.getProductLevelQuery(inputBean);
		inputBean.setFramedQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsARMResponse forecastResponse = new GtnWsARMResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsARMResponse(forecastResponse);
		return response;
	}
        
        @RequestMapping(value = GtnWebServiceUrlConstants.GTN_CCP_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse ccpInsertToForecasting(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
            GtnWsArmRequest armRequest = gtnWsRequest.getGtnWsArmRequest();
            GtnARMHierarchyInputBean inputBean = armRequest.getInputBean();
            ccpInsertService.insertToCPPTable(inputBean);
            return new GtnUIFrameworkWebserviceResponse();
	}
}
