package com.stpl.gtn.gtn2o.ws.service;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Service
public class GtnWsReportCustomViewService {
	@Autowired
	@Qualifier("mongoCLientFactory")
	private MongoClient client;

	@Autowired
	GtnCodeRegistery registery;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	GtnWsReportSqlService sqlStringService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportCustomViewService.class);

	public void loadHierarchy(GtnUIFrameworkWebserviceRequest gtnWsRequestF) throws GtnFrameworkGeneralException {
		GtnWsReportRequest request = gtnWsRequestF.getGtnReportRequest();
		String reportTempName = request.getDataSelectionBean().getName();
		GtnWsReportDataSelectionBean bean = getDataSelectionBean(reportTempName);
		getHierarachyLevels(bean, request.getCustomViewBean());
	}

	private GtnUIFrameworkDataTable getHierarachyLevels(GtnWsReportDataSelectionBean bean,
			GtnWsReportCustomViewBean gtnWsReportCustomViewBean) throws GtnFrameworkGeneralException {
		double hierarchySid;
		int forecastLevel;
		if (gtnWsReportCustomViewBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = bean.getCustomerHierarchySid();
			forecastLevel = bean.getCustomerHierarchyForecastLevel();
		} else {
			hierarchySid = bean.getProductHierarchySid();
			forecastLevel = bean.getProductHierarchyForecastLevel();
		}
		gtnLogger.debug(sqlStringService.getQuery("getHierarchyLevels"));
		List<?> hierarchyData = gtnSqlQueryEngine.executeSelectQuery(sqlStringService.getQuery("getHierarchyLevels"),
				new Object[] { hierarchySid, forecastLevel },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.INTEGER });
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData((List<Object[]>) hierarchyData);
		return gtnUIFrameworkDataTable;
	}

	private GtnWsReportDataSelectionBean getDataSelectionBean(String reportTempName) {
		MongoCollection<GtnWsReportDataSelectionBean> collection = client.getDatabase(MongoStringConstants.DATABSE_NAME)
				.withCodecRegistry(registery.getPojoCodecRegistry())
				.getCollection(MongoStringConstants.REPORT_COLLECTION, GtnWsReportDataSelectionBean.class);
		FindIterable<GtnWsReportDataSelectionBean> selectionBean = collection.find(and(eq("name", reportTempName)));
		return selectionBean.first();
	}
}
