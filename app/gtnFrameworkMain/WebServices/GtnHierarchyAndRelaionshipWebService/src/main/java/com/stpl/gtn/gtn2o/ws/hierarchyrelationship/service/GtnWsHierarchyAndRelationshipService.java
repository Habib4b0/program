package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsHierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice.GtnWsHierarchyAndRelationshipSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Service
public class GtnWsHierarchyAndRelationshipService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnWsHierarchyAndRelationshipSqlService gtnWsHierarchyAndRelationshipSqlService;

	GtnFrameworkSingletonObjectBean hierarchyRelationshipSingletonObj = GtnFrameworkSingletonObjectBean.getInstance();

	private GtnWsHierarchyAndRelationshipService() {
		super();
	}

	public void init() {
		initializeLogger();
		GtnUIFrameworkWebserviceRequest request = registerWs();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("Webservice Registered");
		List<Object[]> resultList = loadHierarchyRelationshipResults();
		Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = resultCustomization(resultList);

		hierarchyRelationshipSingletonObj.setHierarchyMap(hierarchyMap);
	}

	public void initializeLogger() {
		super.logInformation(GtnWsHierarchyAndRelationshipService.class);
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("Building request to register Webservice in Service Registry");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		getEndPointServiceURL(webServiceRegistryBean);
		logger.info("Webservice to Register: " + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		return request;
	}

	private void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
		webServiceRegistryBean.setWebserviceEndPointUrl(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.hierarchyRelationship.endPointUrl"));
		webServiceRegistryBean.setRegisteredWebContext("/GtnHierarchyAndRelaionshipWebService");
	}

	public List<Object[]> loadHierarchyRelationshipResults() {
		String hierarchyLoadQuery = gtnWsHierarchyAndRelationshipSqlService
				.getQuery("loadHierarchyAndRelationshipResults");
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(hierarchyLoadQuery);
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsHierarchyAndRelationshipService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				gtnQueryEngineWebServiceRequest);
		List<Object[]> resultList = response.getQueryResponseBean().getResultList();
		return resultList;
	}

	public Map<String, GtnWsHierarchyDefinitionBean> resultCustomization(List<Object[]> resultList) {
		Map<Integer, List<GtnWsRelationshipBuilderBean>> relationMap = new HashMap<>();
		Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = new HashMap<>();
		int hierachySid = 0;
		int relationSid = 0;
		GtnWsHierarchyDefinitionBean bean = null;
		Map<Integer, String> levelValues = null;
		List<GtnWsRelationshipBuilderBean> relationValues = null;
		for (Object[] object : resultList) {
			if (hierachySid != (Integer) object[7]) {
				bean = new GtnWsHierarchyDefinitionBean();
				hierachySid = (Integer) object[7];
				bean.setHierarchyName(object[0].toString());
				bean.setHierarchyDefSid(hierachySid);
				bean.setHighLevel((int) object[1]);
				bean.setLowestLevel((int) object[2]);
				bean.setLevelName((String) object[5]);
				bean.setHierarchyVersion((int) object[6]);
				levelValues = new HashMap<>();
				relationValues = new ArrayList<>();
				bean.setHierarchyLevelValues(levelValues);
				relationMap.put(hierachySid, relationValues);
				bean.setRelationBean(relationMap);
				hierarchyMap.put((String) object[0], bean);
			}
			if (object[13] != null && relationSid != (Integer) object[13]) {
				GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
				relationSid = (Integer) object[13];
				relationBean.setRelationshipBuilderSid(relationSid);
				relationBean.setRelationshipName(object[14].toString());
				relationBean.setVersionNo((int) object[15]);
				relationValues.add(relationBean);
			}
			levelValues.put((Integer) object[12], object[10].toString());
		}
		return hierarchyMap;
	}

	public List<GtnWsHierarchyDefinitionBean> loadHierarchyResults(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		List<GtnWsHierarchyDefinitionBean> hierarchyList = new ArrayList<>();
		Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = hierarchyRelationshipSingletonObj.getHierarchyMap();
		for (Map.Entry<String, GtnWsHierarchyDefinitionBean> mapEntry : hierarchyMap.entrySet()) {
			if (mapEntry.getKey().contains("")) {

			}
		}
		return hierarchyList;
	}
}
