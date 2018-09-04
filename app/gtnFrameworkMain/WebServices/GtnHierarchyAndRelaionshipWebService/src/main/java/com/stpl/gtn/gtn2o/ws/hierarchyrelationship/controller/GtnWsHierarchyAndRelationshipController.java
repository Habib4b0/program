package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service.GtnWsHierarchyAndRelationshipService;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service.GtnWsRelationshipLevelValueService;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@RestController
public class GtnWsHierarchyAndRelationshipController {

	@Autowired
	private GtnWsHierarchyAndRelationshipService gtnWsHierarchyRelationshipService;
	
	@Autowired
	private GtnWsRelationshipLevelValueService relationshipLevelValueService;

	private GtnWsHierarchyAndRelationshipController() {
		super();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean display() {
		return true;
	}

	@RequestMapping(value = "/loadHierarchyResults", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();

		List<Object[]> hierarchyList = gtnWsHierarchyRelationshipService
				.loadHierarchyResults(gtnUIFrameworkWebserviceRequest);

		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(hierarchyList);
		searchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(searchResponse);

		return response;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadLevelValueMapQuery(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = relationshipLevelValueService.getQueryForLevelValueMap(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
	
	@RequestMapping(value = "/loadLevelValueMapResults", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadLevelValueMap(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		Map<String, String> relationMap = new HashMap<>();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest forecastRequet = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		List<Object[]> resultList = relationshipLevelValueService.getLevelValueMap(inputBean);
		for (Object[] levelData : resultList) {
			if (levelData[0] != null && levelData[1] != null) {
				relationMap.put(levelData[0].toString(), levelData[1].toString());
			}
		}
		inputBean.setTempTableMap(relationMap);
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
	
	@RequestMapping(value = "/getHierarchyLevelValues", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyLevelValues(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest forecastRequest = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequest.getInputBean();
		List<GtnReportHierarchyLevelBean> levelList = new ArrayList<>();
		List<Object[]> resultList = relationshipLevelValueService.getHierarchyLevelValues(inputBean);
		for (Object[] object : resultList) {
			GtnReportHierarchyLevelBean levelBean = new GtnReportHierarchyLevelBean();
			levelBean.setLevelNo(getIntegerValue(object, 0));
			levelBean.setLevelValueReference(getStringValue(object, 1));
			levelBean.setTableName(object[2] == null ? "" : object[2].toString());
			levelBean.setFieldName(object[3] == null ? "" : object[3].toString());
			levelBean.setLevel(object[4] == null ? "" : object[4].toString());
			levelBean.setHierarchyLevelDefSid(object[5] == null ? "0" : object[5].toString());
			levelBean.setHierarchyDefSid((Integer.valueOf(object[6] == null ? "0" : object[6].toString())));
			levelBean.setHierarchyType(object[7] == null ? "" : object[7].toString());
			levelBean.setHierarchyVersionNo(inputBean.getHierarchyVersionNo());
			levelList.add(levelBean);
		}
		inputBean.setLevelList(levelList);
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
	
	private Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

	private String getStringValue(Object[] objects, int index) {
		return objects[index] == null ? "" : objects[index].toString();
	}
	
	
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = relationshipLevelValueService.getCustomerLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
        
        
        @RequestMapping(value = "/loadAvailableTableCustomerLevel", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadAvailableTableCustomerLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastRequest forecastRequest = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
                inputBean.setHieraryQuery(forecastRequest.getInputBean().getHieraryQuery());
		List<Object[]> resultsDataList = relationshipLevelValueService.getAvailableTableCustomerLevelValues(inputBean);
		inputBean.setResultList(resultsDataList);
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
        
	
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_PRODUCT_LEVEL, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarcyLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		String finalQuery = relationshipLevelValueService.getProductLevelQuery(inputBean);
		inputBean.setHieraryQuery(finalQuery);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
	
        @RequestMapping(value = "/childLevelsWithHierarchyNo", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getChildLevelsWithHierarchyNo(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		List<Object[]> resultList = relationshipLevelValueService.getChildLevelsWithHierarchyNoService(inputBean);
		inputBean.setResultList(resultList);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		forecastResponse.setInputBean(inputBean);
		response.setGtnWsForecastResponse(forecastResponse);
		return response;
	}
   
        @RequestMapping(value = "/selectedCustomerLevel", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getSelectedCustomerLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		
			GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
			GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
			List<Object[]> resultList = relationshipLevelValueService.getSelectedCustomerLevelService(inputBean);
			inputBean.setResultList(resultList);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
			GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
			forecastResponse.setInputBean(inputBean);
			response.setGtnWsForecastResponse(forecastResponse);
			return response;
	}
}
