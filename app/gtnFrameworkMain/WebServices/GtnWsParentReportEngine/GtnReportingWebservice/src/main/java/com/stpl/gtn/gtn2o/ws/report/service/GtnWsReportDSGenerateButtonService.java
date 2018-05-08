package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.service.RelationshipLevelValuesMasterBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportDSGenerateButtonService {

	@Autowired
	GtnWsTreeService gtnWsTreeService;

	@Autowired
	GtnWsMongoService gtnWsMongoService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private ApplicationContext applicationContext;

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnWsReportDSGenerateButtonService.class);

	public void generateCCPForReporting(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnReportRequest().getDataSelectionBean();
			callCCPInsertService(gtnWsRequest);
			callDeductionInsertQuery();
			buildCustomerTree(dataSelectionBean);
			buildProductTree(dataSelectionBean);
			createDataSourceData("");
		} catch (GtnFrameworkGeneralException ex) {
			ex.printStackTrace();
		}
	}

	public void callCCPInsertService(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE + GtnWebServiceUrlConstants.GTN_REPORT_CCP_INSERT,
				gtnWsRequest, getGsnWsSecurityToken(gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));
	}

	public void callDeductionInsertQuery() {
		try {
			String tableName = "ST_DEDUCTION_DETAILS_111_111";
			List<String> input = new ArrayList<>();
			input.add(tableName);
			input.add(tableName);
			input.add(tableName);
			input.add("ST_CCPD_HIERARCHY_111_111");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlService.getQuery(input, "deductionInsertQuery"));
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error(e.getMessage());
		}
	}

	public void buildCustomerTree(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		// Selected customer hierarchy all level
		// LevelMap

		List<Object> input = new ArrayList<>();
		input.add(dataSelectionBean.getCustomerRelationshipBuilderSid());
		List tempList = gtnSqlQueryEngine.executeSelectQuery(sqlService.getQuery(input, "getHierarchyTableDetails"));
		RelationshipLevelValuesMasterBean relationshipLevelValueMasterBean = (RelationshipLevelValuesMasterBean) applicationContext
				.getBean(RelationshipLevelValuesMasterBean.class);
		relationshipLevelValueMasterBean.createQuery(tempList, dataSelectionBean, "CUST_HIERARCHY_NO");
		List<Object[]> customerResults = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(relationshipLevelValueMasterBean.getFinalQuery());
		GtnWsReportEngineTreeNode customerNode = gtnWsTreeService.buildTree(customerResults,
				GtnWsHierarchyType.CUSTOMER);
		gtnWsMongoService.writeTreeToMongo("Customer_Tree", customerNode);
	}

	public void buildProductTree(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(dataSelectionBean.getProductRelationshipBuilderSid());
		List tempList = gtnSqlQueryEngine.executeSelectQuery(sqlService.getQuery(input, "getHierarchyTableDetails"));
		RelationshipLevelValuesMasterBean relationshipLevelValueMasterBean = (RelationshipLevelValuesMasterBean) applicationContext
				.getBean(RelationshipLevelValuesMasterBean.class);
		relationshipLevelValueMasterBean.createQuery(tempList, dataSelectionBean, "PROD_HIERARCHY_NO");
		List<Object[]> productResults = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(relationshipLevelValueMasterBean.getFinalQuery());
		GtnWsReportEngineTreeNode productNode = gtnWsTreeService.buildTree(productResults, GtnWsHierarchyType.PRODUCT);
		gtnWsMongoService.writeTreeToMongo("Product_Tree", productNode);
	}

	public void createDataSourceData(String collectionName) {
		// Need to get query from reddy
	}

	public static GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		token.setUserId(userId);
		token.setSessionId(sessionId);
		return token;
	}
}
