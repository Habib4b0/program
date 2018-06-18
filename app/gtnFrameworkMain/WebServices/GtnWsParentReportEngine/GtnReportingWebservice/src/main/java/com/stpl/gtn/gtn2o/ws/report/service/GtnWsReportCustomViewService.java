package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Service
public class GtnWsReportCustomViewService {

	// @Autowired
	// GtnWsMongoDBConnectionService connection;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	GtnWsReportSqlService sqlStringService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportCustomViewService.class);

	public GtnUIFrameworkDataTable loadHierarchy(GtnUIFrameworkWebserviceRequest gtnWsRequestF)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest request = gtnWsRequestF.getGtnWsReportRequest();
		return getHierarachyLevels(gtnWsRequestF.getGtnWsReportRequest().getReportBean().getDataSelectionBean(),
				request.getReportBean().getCustomViewBean());
	}

	private GtnUIFrameworkDataTable getHierarachyLevels(GtnWsReportDataSelectionBean bean,
			GtnWsReportCustomViewBean gtnWsReportCustomViewBean) throws GtnFrameworkGeneralException {
		double hierarchySid;
		if (gtnWsReportCustomViewBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = bean.getCustomerRelationshipBuilderSid();
		} else {
			hierarchySid = bean.getProductRelationshipBuilderSid();
		}
		gtnLogger.debug(sqlStringService.getQuery("getHierarchyLevels"));
		List<?> hierarchyData = gtnSqlQueryEngine.executeSelectQuery(sqlStringService.getQuery("getHierarchyLevels"),
				new Object[] { gtnWsReportCustomViewBean.getHierarchyType().toString(), hierarchySid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.DOUBLE });
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData((List<Object[]>) hierarchyData);
		return gtnUIFrameworkDataTable;
	}

	// private GtnWsReportDataSelectionBean getDataSelectionBean(String
	// reportTempName) {
	// MongoCollection<GtnWsReportDataSelectionBean> collection =
	// connection.getDBInstance()
	// .getCollection(MongoStringConstants.REPORT_COLLECTION,
	// GtnWsReportDataSelectionBean.class);
	// FindIterable<GtnWsReportDataSelectionBean> selectionBean =
	// collection.find(and(eq("name", reportTempName)));
	// return selectionBean.first();
	// }

	// public void saveCustomViewTree(GtnUIFrameworkWebserviceRequest gtnWsRequestF)
	// {
	// MongoCollection<GtnWsReportCustomViewDataBean> collection =
	// connection.getDBInstance()
	// .getCollection(MongoStringConstants.CUSTOM_VIEW_COLLECTION,
	// GtnWsReportCustomViewDataBean.class);
	// GtnWsReportCustomViewDataBean viewDataBean =
	// gtnWsRequestF.getGtnWsReportRequest().getReportBean()
	// .getCustomViewBean().getCustomViewDataBean();
	// if
	// (gtnWsRequestF.getGtnWsReportRequest().getReportBean().getCustomViewBean().isEdit())
	// {
	// collection.replaceOne(eq("customViewName", viewDataBean.getCustomViewName()),
	// viewDataBean);
	// } else {
	// collection.insertOne(viewDataBean);
	// }
	// }

	// public List<String> loadCustomViewString() {
	// MongoCollection<Document> collection = connection.getDBInstance()
	// .getCollection(MongoStringConstants.CUSTOM_VIEW_COLLECTION);
	// FindIterable<Document> foundData =
	// collection.find().projection(include("customViewName"));
	// MongoIterable<String> customViewNameIterable = foundData.map(document ->
	// document.getString("customViewName"));
	// return StreamSupport.stream(customViewNameIterable.spliterator(),
	// false).collect(Collectors.toList());
	//
	// }

	// public GtnWsReportCustomViewDataBean
	// loadCustomView(GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
	// MongoCollection<GtnWsReportCustomViewDataBean> mongoCollection =
	// connection.getDBInstance()
	// .getCollection(MongoStringConstants.CUSTOM_VIEW_COLLECTION,
	// GtnWsReportCustomViewDataBean.class);
	// String customViewName =
	// gtnWsRequestF.getGtnWsReportRequest().getReportBean().getCustomViewBean()
	// .getCustomViewDataBean().getCustomViewName();
	// return mongoCollection.find(eq("customViewName", customViewName)).first();
	//
	// }
}
