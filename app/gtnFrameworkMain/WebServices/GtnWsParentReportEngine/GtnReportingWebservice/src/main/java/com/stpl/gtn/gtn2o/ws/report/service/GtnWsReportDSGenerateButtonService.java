package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportDSGenerateButtonService {

	@Autowired
	GtnWsTreeService gtnWsTreeService;

	@Autowired
	GtnWsMongoService gtnWsMongoService;

	public void generateCCPForReporting(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		callCCPInsertService(gtnWsRequest);
		buildCustomerTree();
		buildProductTree();
		createDataSourceData("");
	}

	public void callCCPInsertService(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE + GtnWebServiceUrlConstants.GTN_CCP_INSERT,
				gtnWsRequest, getGsnWsSecurityToken());
	}

	public void buildCustomerTree() {
		// Selected customer hierarchy all level
		// LevelMap
		GtnWsReportEngineTreeNode customerNode = gtnWsTreeService.buildTree(null, null,
				MongoStringConstants.CUSTOMER_INDICATOR);
		// Mongo table name
		gtnWsMongoService.writeTreeToMongo("", customerNode);
	}

	public void buildProductTree() {
		// Selected customer hierarchy all level
		// LevelMap
		GtnWsReportEngineTreeNode productNode = gtnWsTreeService.buildTree(null, null,
				MongoStringConstants.PRODUCT_INDICATOR);
		// Mongo table name
		gtnWsMongoService.writeTreeToMongo("", productNode);
	}

	public void createDataSourceData(String collectionName) {
		// Need to get query from reddy
	}

	public static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = "123";
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}
}
