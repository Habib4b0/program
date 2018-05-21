package com.stpl.gtn.gtn2o.ws.report.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.ReportingMaster;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportWebsevice {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnReportJsonService gtnReportJsonService;

	public GtnWsReportWebsevice() {
		super();
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public List<Object[]> loadHierarchyResults(GtnUIFrameworkWebserviceRequest request, boolean isCustomerHierarchy)
			throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();
		Map<String, String> criteriaMap = new HashMap<>();
		for (GtnWebServiceSearchCriteria searchCriteria : request.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
			}
		}
		if (isCustomerHierarchy) {
			inputList.add("'Customer Hierarchy'");
			String hierarchyName = criteriaMap.get("hierarchyName");
			inputList.add("'" + hierarchyName + "'");
		} else {
			inputList.add("'Product Hierarchy'");
			String hierarchyName = criteriaMap.get("hierarchyName");
			inputList.add("'" + hierarchyName + "'");
		}
		inputList.add("'" + criteriaMap.get("hierarchyType") + "'");
		String hierarchyLoadQuery = sqlService.getQuery(inputList, "getHierarchyResults");
		List<Object[]> hierarchyResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(hierarchyLoadQuery);
		return hierarchyResultList;
	}

	public Date loadForecastEligibleDate() throws GtnFrameworkGeneralException {
		List forecastEligibleDate = gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery("loadForecastEligibleDate"));
		return forecastEligibleDate != null && !forecastEligibleDate.isEmpty() ? (Date) forecastEligibleDate.get(0)
				: null;
	}

	public List<Object[]> loadViewResults(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			boolean viewMode) throws GtnFrameworkGeneralException, IOException {
		List<Object> inputList = new ArrayList<>();
		String userId = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId();
		String viewType = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
		Map<String, String> criteriaMap = new HashMap<>();
		for (GtnWebServiceSearchCriteria searchCriteria : gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
			}
		}
		inputList.add("'" + viewType + "'");
		if (viewMode) {
			String privateViewName = criteriaMap.get("privateViewName");
			inputList.add("'" + privateViewName + "'");
		} else {
			String viewName = criteriaMap.get("publicViewName");
			inputList.add("'" + viewName + "'");
		}
		inputList.add(userId);
		String viewQuery = sqlService.getQuery(inputList, "loadViewResults");
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(viewQuery);
		for (Object[] objects : resultList) {
			System.out.println("objects[0]------->" + String.valueOf(objects[0]));
			System.out.println("objects[1]------->" + String.valueOf(objects[1]));
			System.out.println("objects[2]------->" + String.valueOf(objects[2]));
			System.out.println("objects[3]------->" + String.valueOf(objects[3]));
			System.out.println("objects[4]------->" + String.valueOf(objects[4]));
			System.out.println("objects[5]------->" + String.valueOf(objects[5]));
			Object bean = gtnReportJsonService.convertJsonToObject(GtnWsReportDataSelectionBean.class,
					String.valueOf(objects[5]));
			resultList.remove(5);
			resultList.add((Object[]) bean);
		}
		return resultList;
	}

	private String getCriteria(GtnWebServiceSearchCriteria searchCriteria) {
		switch (searchCriteria.getFieldId()) {
		case "privateViewName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "publicViewName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "hierarchyName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "hierarchyType":
			return searchCriteria.getFilterValue1();
		default:
			return null;
		}

	}

	public int saveReportingMaster(GtnWsReportDataSelectionBean dataSelectionBean, int userId)
			throws GtnFrameworkGeneralException {
		// Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		// ReportingMaster reportMaster = getReportingMaster(dataSelectionBean,
		// userId);
		List<Object> inputList = new ArrayList<>();
		inputList.add("'" + dataSelectionBean.getViewName() + "'");
		inputList.add("'" + dataSelectionBean.getViewType() + "'");
		inputList.add(userId);
		inputList.add(userId);
		inputList.add("'" + gtnReportJsonService.convertObjectToJson(dataSelectionBean) + "'");
		String query = sqlService.getQuery(inputList, "insertView");
		int count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		return count;
		// session.save(reportMaster);
		// transaction.commit();
	}

	private ReportingMaster getReportingMaster(GtnWsReportDataSelectionBean dataSelectionBean, int userId) {
		ReportingMaster reportMaster = new ReportingMaster();
		reportMaster.setCompany(dataSelectionBean.getCompanyReport());
		reportMaster.setBusinessUnit(dataSelectionBean.getBusinessUnitReport());
		GtnWsRecordBean customerHierarchyBean = dataSelectionBean.getCustomerHierarchyRecordBean();
		GtnWsRecordBean productHierachyBean = dataSelectionBean.getProductHierarchyRecordBean();
		reportMaster.setCustomerRelationshipBuilderSid(dataSelectionBean.getCustomerRelationshipBuilderSid());
		reportMaster.setCustomerRelationshipVersionNo(dataSelectionBean.getCustomerRelationshipVersionNo());
		reportMaster.setProductRelationshipBuilderSid(dataSelectionBean.getProductRelationshipBuilderSid());
		reportMaster.setProductRelationshipVersionNo(dataSelectionBean.getProductRelationshipVersionNo());
		reportMaster.setCustomerHierarchyLevel(dataSelectionBean.getCustomerHierarchyForecastLevel());
		reportMaster.setProductHierarchyLevel(dataSelectionBean.getProductHierarchyForecastLevel());
		reportMaster.setCustomerHierarchySid((int) customerHierarchyBean.getPropertyValueByIndex(6));
		reportMaster.setCustomerHierarchyVersionNo((int) customerHierarchyBean.getPropertyValueByIndex(7));
		reportMaster.setProductHierarchySid((int) productHierachyBean.getPropertyValueByIndex(6));
		reportMaster.setProductHierarchyVersionNo((int) productHierachyBean.getPropertyValueByIndex(7));
		reportMaster.setReportingDataSource(dataSelectionBean.getReportDataSource());
		return reportMaster;
	}
}
