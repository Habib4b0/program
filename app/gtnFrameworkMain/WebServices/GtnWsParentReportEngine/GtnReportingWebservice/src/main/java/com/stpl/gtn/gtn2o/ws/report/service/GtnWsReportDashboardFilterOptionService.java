package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Service
public class GtnWsReportDashboardFilterOptionService {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportDashboardFilterOptionService.class);
	@Autowired
	private GtnWsReportSqlService reportSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnWsReportSqlService getReportSqlService() {
		return reportSqlService;
	}

	public void setReportSqlService(GtnWsReportSqlService reportSqlService) {
		this.reportSqlService = reportSqlService;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnWsReportDashboardFilterOptionService() {
		super();
	}

	public List<Object[]> getCustAndProdLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		GtnWsReportDataSelectionBean dataSelectionBean = Optional.ofNullable(gtnWsRequest)
				.map(GtnUIFrameworkWebserviceRequest::getGtnWsReportRequest).map(GtnWsReportRequest::getReportBean)
				.map(GtnWsReportBean::getDataSelectionBean).orElse(new GtnWsReportDataSelectionBean());

		GtnWsReportDashboardBean gtnWsReportDashboardBean = Optional.ofNullable(gtnWsRequest)
				.map(GtnUIFrameworkWebserviceRequest::getGtnWsReportRequest)
				.map(GtnWsReportRequest::getGtnWsReportDashboardBean).orElse(new GtnWsReportDashboardBean());
		String custProdLevelQuery = reportSqlService.getQuery("filterOptionCustLevelProdLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		double hierarchySid = dataSelectionBean.getProductHierarchySid();
		int forecastLevel = dataSelectionBean.getProductHierarchyForecastLevel();
		if (gtnWsReportDashboardBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = dataSelectionBean.getCustomerHierarchySid();
			forecastLevel = dataSelectionBean.getCustomerHierarchyForecastLevel();
		}
		Object[] parameterValues = { hierarchySid, forecastLevel };
		GtnFrameworkDataType[] dataTypes = { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.INTEGER };
		List<Object[]> hierarchyData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(custProdLevelQuery,
				parameterValues, dataTypes);
		return Optional.ofNullable(hierarchyData).get();
	}

}
