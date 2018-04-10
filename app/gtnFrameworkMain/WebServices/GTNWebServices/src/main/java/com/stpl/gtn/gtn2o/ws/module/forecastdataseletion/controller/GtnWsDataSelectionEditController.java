package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkCustomerLevelLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkLevelValueMapQueryService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkProductLevelLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkSelectedTblLoadService;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnWsDataselectionHierarchyUpdateService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE)
public class GtnWsDataSelectionEditController {

	@Autowired
	private GtnFrameworkCustomerLevelLoadService customerLevelService;
	@Autowired
	private GtnFrameworkProductLevelLoadService productLevelService;
	@Autowired
	private GtnFrameworkSelectedTblLoadService productSelectedLoadService;
	@Autowired
	private GtnFrameworkLevelValueMapQueryService levelValueMapQueryService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);

	public GtnWsDataSelectionEditController() {
		super();
	}

	@Autowired
	private GtnWsDataselectionHierarchyUpdateService service;
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_EDIT_CUSTHIERARCHY_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkAndUpdateCustAndProdHierarchy(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		service.checkAndInsertHierarchy(gtnWsRequest.getGtnWshirarchyInsertRequest());

		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_PRODUCT_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = productLevelService.getProductLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = customerLevelService.getCustomerLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_PRODUCT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductSelecedChild(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		try {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
			String finalQuery = productSelectedLoadService.getChildLevelQueryForProduct(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
			return response;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_SELECTED_CUSTOMER, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerSelecedChild(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = productSelectedLoadService.getQueryForSelectedCustomer(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
			return response;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadLevelValueMapQuery(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = levelValueMapQueryService.getQueryForLevelValueMap(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
}
