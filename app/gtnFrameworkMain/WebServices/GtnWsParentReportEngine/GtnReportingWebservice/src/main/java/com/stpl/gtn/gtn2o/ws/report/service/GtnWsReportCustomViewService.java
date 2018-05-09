package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoDBConnectionService;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Service
public class GtnWsReportCustomViewService {

	@Autowired
	GtnWsMongoDBConnectionService connection;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	GtnWsReportSqlService sqlStringService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportCustomViewService.class);

	public GtnUIFrameworkDataTable loadHierarchy(GtnUIFrameworkWebserviceRequest gtnWsRequestF)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest request = gtnWsRequestF.getGtnReportRequest();
		GtnWsReportDataSelectionBean bean = request.getReportBean().getDataSelectionBean();
		return getHierarachyLevels(bean, request.getReportBean().getCustomViewBean());
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
				new Object[] { gtnWsReportCustomViewBean.getHierarchyType().toString(), hierarchySid, forecastLevel },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.DOUBLE,
						GtnFrameworkDataType.INTEGER });
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData((List<Object[]>) hierarchyData);
		return gtnUIFrameworkDataTable;
	}

	public void saveCustomViewTree(GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		MongoCollection<GtnWsCustomTreeData> collection = connection.getDBInstance()
				.getCollection(MongoStringConstants.CUSTOM_VIEW_COLLECTION, GtnWsCustomTreeData.class);
		collection
				.insertOne(gtnWsRequestF.getGtnReportRequest().getReportBean().getCustomViewBean().getCustomTreeData());
	}
}
