/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

/**
 *
 * @author anandh.karuppusamy
 */
public class ComboBoxSearch extends GtnCommonWebServiceImplClass implements SearchInterface {

	public ComboBoxSearch() {
		super(ComboBoxSearch.class);
	}

	@Override
	public GtnUIFrameworkWebserviceResponse getSearch(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,
			String query) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			logger.debug("Businnes unit query:" + query);
			GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
			queryExecutorBean.setSqlQuery(query);
			queryExecutorBean.setQueryType("SELECT");
			GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
			gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
			logger.info("calling query engine via service registry");
			GtnQueryEngineWebServiceResponse response1 = callServiceRegistryRedirectForQueryEngine(
					gtnQueryEngineWebServiceRequest);
			List<Object[]> resultList = response1.getQueryResponseBean().getResultList();
			GtnUIFrameworkWebserviceComboBoxResponse comboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
			comboxResponse.setComboBoxList(resultList);
			response.setGtnUIFrameworkWebserviceComboBoxResponse(comboxResponse);
		} catch (Exception e) {
			logger.error("Exception in loading business units" + e);
		}
		return response;
	}

	@Override
	public void initCallOnFailure() {
		return;
	}

	@Override
	public void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
		return;
	}

	@Override
	public GtnUIFrameworkWebserviceResponse getSearchResults(
			GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest, String query,
			GtnSearchwebServiceSqlService gtnSearchSqlService) {
		return null;
	}

}
