package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

@Service
public class GtnWsReportWebsevice {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

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

	@SuppressWarnings("unchecked")
	public List<Object[]> loadHierarchyResults() throws GtnFrameworkGeneralException {

		Object[] params = { "Customer Hierarchy", "%", "Primary" };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = sqlService.getQuery("getHierarchyResults");
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadProductHierarchyResults() throws GtnFrameworkGeneralException {
		Object[] params = { "Product Hierarchy", "%", "Primary" };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = sqlService.getQuery("getHierarchyResults");
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());

		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		return resultList;
	}

	public Date loadForecastEligibleDate() throws GtnFrameworkGeneralException {
		List forecastEligibleDate = gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery("loadForecastEligibleDate"));
		return forecastEligibleDate != null && !forecastEligibleDate.isEmpty() ? (Date) forecastEligibleDate.get(0)
				: null;
	}

}
