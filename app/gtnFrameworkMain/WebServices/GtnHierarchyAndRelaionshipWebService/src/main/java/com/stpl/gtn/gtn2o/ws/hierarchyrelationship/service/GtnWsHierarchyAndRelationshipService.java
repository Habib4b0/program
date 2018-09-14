package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
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

	private GtnFrameworkSingletonObjectBean hierarchyRelationship = GtnFrameworkSingletonObjectBean.getInstance();

	private GtnWsHierarchyAndRelationshipService() {
		super();
	}

	@PostConstruct
	public void initializeLogger() {
		super.logInformation(GtnWsHierarchyAndRelationshipService.class);
	}

	public void init() {
            try {
		initializeLogger();
		GtnUIFrameworkWebserviceRequest request = registerWs();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("Webservice Registered");
		List<Object[]> resultList = loadHierarchyRelationshipResults();
		Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = resultCustomization(resultList);
		hierarchyRelationship.setHierarchyMap(hierarchyMap);
            }catch (Exception e) {
            logger.error("Exception in HierarchyAndRelationship web service call", e);
        }
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
		return response.getQueryResponseBean().getResultList();
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
				bean.setHierarchyCategory((String) object[8]);
				bean.setHierarchyType((String) object[9]);
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

	public List<Object[]> loadHierarchyResults(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		List<Object[]> hierarchyList = new ArrayList<>();
		try {
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList = gtnUIFrameworkWebserviceRequest
					.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList();
			Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = hierarchyRelationship.getHierarchyMap();
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean = new GtnWsHierarchyDefinitionBean();
			String hierarchyName = webSearchCriteriaList.get(webSearchCriteriaList.size() - 2).getFilterValue1();

			getHierarchyListUsingGenericSearch(hierarchyList, webSearchCriteriaList, hierarchyMap, hierarchyName,
					hierarchyDefinitionBean);

			getHierarchyListUsingStartsAndEndsWithSearch(hierarchyList, webSearchCriteriaList, hierarchyMap,
					hierarchyName, hierarchyDefinitionBean);

			getHierarchyListUsingHierarchyNameSearch(hierarchyList, webSearchCriteriaList, hierarchyMap, hierarchyName,
					hierarchyDefinitionBean);
		} catch (Exception exception) {
			logger.error("Exception in HierarchyList return", exception);
		}

		return hierarchyList;
	}

	private void getHierarchyListUsingHierarchyNameSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap, String hierarchyName,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean) throws IllegalAccessException {
		if (!hierarchyName.contains("*")) {
			TreeMap<String, GtnWsHierarchyDefinitionBean> hierarchyCaseInsensitiveMap = new TreeMap<>(
					String.CASE_INSENSITIVE_ORDER);
			hierarchyCaseInsensitiveMap.putAll(hierarchyMap);

			hierarchyDefinitionBean = hierarchyCaseInsensitiveMap
					.get(webSearchCriteriaList.get(webSearchCriteriaList.size() - 2).getFilterValue1());
			getHierarchyListFromHierarchyMap(hierarchyList, webSearchCriteriaList, hierarchyDefinitionBean);
		}
	}

	private void getHierarchyListUsingStartsAndEndsWithSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap, String hierarchyName,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean) throws IllegalAccessException {
		if (hierarchyName.contains("*") && hierarchyName.length() > 1) {
			for (Map.Entry<String, GtnWsHierarchyDefinitionBean> mapEntry : hierarchyMap.entrySet()) {

				hierarchyDefinitionBean = hierarchyMap.get(mapEntry.getKey());
				int indexOfSymbol = hierarchyName.indexOf('*');
				String[] hierarchyNameSplit = hierarchyName.split("\\*");

				if (hierarchyNameSplit.length > 1) {
					getHierarchyListFromBothStartsAndEndsWithSearch(hierarchyList, webSearchCriteriaList,
							hierarchyDefinitionBean, hierarchyNameSplit);
				} else if (indexOfSymbol == hierarchyName.length() - 1) {
					getHierarchyListFromBothStartsWithSearch(hierarchyList, webSearchCriteriaList,
							hierarchyDefinitionBean, hierarchyNameSplit);
				} else {
					getHierarchyListFromBothEndsWithSearch(hierarchyList, webSearchCriteriaList,
							hierarchyDefinitionBean, hierarchyNameSplit);
				}

			}
		}
	}

	private void getHierarchyListFromBothEndsWithSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean, String[] hierarchyNameSplit)
			throws IllegalAccessException {
		if (hierarchyDefinitionBean.getHierarchyName().toLowerCase(Locale.ENGLISH)
				.endsWith(hierarchyNameSplit[0].toLowerCase(Locale.ENGLISH))) {
			getHierarchyListFromHierarchyMap(hierarchyList, webSearchCriteriaList, hierarchyDefinitionBean);
		}
	}

	private void getHierarchyListFromBothStartsWithSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean, String[] hierarchyNameSplit)
			throws IllegalAccessException {
		if (hierarchyDefinitionBean.getHierarchyName().toLowerCase(Locale.ENGLISH)
				.startsWith(hierarchyNameSplit[0].toLowerCase(Locale.ENGLISH))) {
			getHierarchyListFromHierarchyMap(hierarchyList, webSearchCriteriaList, hierarchyDefinitionBean);

		}
	}

	private void getHierarchyListFromBothStartsAndEndsWithSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean, String[] hierarchyNameSplit)
			throws IllegalAccessException {
		if (hierarchyDefinitionBean.getHierarchyName().toLowerCase(Locale.ENGLISH)
				.startsWith(hierarchyNameSplit[0].toLowerCase(Locale.ENGLISH))
				&& hierarchyDefinitionBean.getHierarchyName().toLowerCase(Locale.ENGLISH)
						.endsWith(hierarchyNameSplit[1].toLowerCase(Locale.ENGLISH))) {
			getHierarchyListFromHierarchyMap(hierarchyList, webSearchCriteriaList, hierarchyDefinitionBean);
		}
	}

	private void getHierarchyListUsingGenericSearch(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap, String hierarchyName,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean) throws IllegalAccessException {
		if (hierarchyName.equals("*") && hierarchyName.length() == 1) {

			for (Map.Entry<String, GtnWsHierarchyDefinitionBean> mapEntry : hierarchyMap.entrySet()) {
				hierarchyDefinitionBean = hierarchyMap.get(mapEntry.getKey());
				getHierarchyListFromHierarchyMap(hierarchyList, webSearchCriteriaList, hierarchyDefinitionBean);
			}
		}
	}

	private void getHierarchyListFromHierarchyMap(List<Object[]> hierarchyList,
			List<GtnWebServiceSearchCriteria> webSearchCriteriaList,
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean) throws IllegalAccessException {

		if (hierarchyDefinitionBean.getHierarchyCategory()
				.equals(webSearchCriteriaList.get(webSearchCriteriaList.size() - 1).getFilterValue1())
				&& hierarchyDefinitionBean.getHierarchyType()
						.equals(webSearchCriteriaList.get(webSearchCriteriaList.size() - 3).getFilterValue1())) {

			Field[] fields = hierarchyDefinitionBean.getClass().getDeclaredFields();
			Object[] hierarchyObject = new Object[fields.length];
			int i = 0;
			for (Field field : fields) {
				field.setAccessible(true);
				hierarchyObject[i] = field.get(hierarchyDefinitionBean);
				i++;
			}
			hierarchyList.add(hierarchyObject);
		}
	}
}
